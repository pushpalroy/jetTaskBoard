package com.jetapps.jettaskboard.zoomable

import androidx.compose.foundation.gestures.awaitFirstDown // ktlint-disable import-ordering
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculateCentroidSize
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateRotation
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.transformable // ktlint-disable no-unused-imports
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

// The original code is here: https://github.com/Mr-Pine/Zoomables
/**
 * Creates a composable that wraps a given [Composable] and adds zoom, pan, rotation, double tap and swipe functionality
 *
 * **NOTE** this Composable's functionality is different from using [Modifier.transformable] in multiple ways:
 * * Nicer zooming behaviour by zooming away from the center of the multitouch (instead of the [Composable] center)
 * * Nicer rotation behaviour by rotating around the center of the multitouch (instead of the [Composable] center)
 * * Ability to use swipe gestures when not zoomed in and panning when zoomed in
 * * Provides simple functions for swipe and tap events
 * * Wrapping most of the boilerplate code and providing even simpler functions for images with [ZoomableImage] and [EasyZoomableImage]
 *
 * @param coroutineScope used for smooth asynchronous zoom/pan/rotation animations
 * @param zoomableState Contains the current transform states - obtained via [rememberZoomableState]
 * @param dragGesturesEnabled A function with a [ZoomableState] scope that returns a boolean value to enable/disable dragging gestures (swiping and panning). Returns `true` by default. *Note*: For some use cases it may be required that only panning is possible. Use `{!notTransformed}` in that case
 * @param onSwipeLeft Optional function to run when user swipes from right to left - does nothing by default
 * @param onSwipeRight Optional function to run when user swipes from left to right - does nothing by default
 * @param minimumSwipeDistance Minimum distance the user has to travel on the screen for it to count as swiping
 * @param onDoubleTap Optional function to run when user double taps. Zooms in by 2x to the touch point when scale is currently 1 and zooms out to scale = 1 when zoomed in when `null` (default)
 */

@Composable
fun Zoomable(
    coroutineScope: CoroutineScope,
    zoomableState: ZoomableState,
    dragGesturesEnabled: ZoomableState.() -> Boolean = { false },
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    minimumSwipeDistance: Int = 0,
    onDoubleTap: ((Offset) -> Unit)? = null,
    content: @Composable (BoxScope.() -> Unit)
) {
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var composableCenter by remember { mutableStateOf(Offset.Zero) }
    var transformOffset by remember { mutableStateOf(Offset.Zero) }

    val doubleTapFunction = onDoubleTap ?: {
        if (zoomableState.scale.value != 1f) {
            coroutineScope.launch {
                zoomableState.animateBy(
                    zoomChange = 1 / zoomableState.scale.value,
                    panChange = -zoomableState.offset.value,
                    rotationChange = -zoomableState.rotation.value
                )
            }
            Unit
        } else {
            coroutineScope.launch {
                zoomableState.animateZoomToPosition(
                    zoomChange = 1.2f,
                    position = Offset.Zero,
                    currentComposableCenter = composableCenter
                )
                // zoomableState.animateZoomBy(2f)
            }
            Unit
        }
    }

    fun onTransformGesture(
        centroid: Offset,
        pan: Offset,
        zoom: Float,
        transformRotation: Float
    ) {
        val rotationChange =
            if (zoomableState.rotationBehavior == ZoomableState.Rotation.DISABLED) 0f else transformRotation

        val tempOffset = zoomableState.offset.value + pan

        val x0 = centroid.x - composableCenter.x
        val y0 = centroid.y - composableCenter.y

        val hyp0 = sqrt(x0 * x0 + y0 * y0)
        val hyp1 = zoom * hyp0 * (
            if (x0 > 0) {
                1f
            } else {
                -1f
            }
            )

        val alpha0 = atan(y0 / x0)

        val alpha1 = alpha0 + (rotationChange * ((2 * PI) / 360))

        val x1 = cos(alpha1) * hyp1
        val y1 = sin(alpha1) * hyp1

        transformOffset =
            centroid - (composableCenter - tempOffset) - Offset(x1.toFloat(), y1.toFloat())

        coroutineScope.launch {
            zoomableState.transform {
                transformBy(
                    zoomChange = zoom,
                    panChange = transformOffset - zoomableState.offset.value,
                    rotationChange = rotationChange
                )
            }
        }
    }

    Box(
        Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = doubleTapFunction
                )
            }
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        var transformRotation = 0f
                        var zoom = 1f
                        var pan = Offset.Zero
                        var pastTouchSlop = false
                        val touchSlop = viewConfiguration.touchSlop
                        var lockedToPanZoom = false
                        var drag: PointerInputChange?
                        var overSlop = Offset.Zero

                        val down = awaitFirstDown(requireUnconsumed = false)

                        var transformEventCounter = 0
                        do {
                            val event = awaitPointerEvent()
                            val canceled = event.changes.fastAny { it.isConsumed }
                            var relevant = true
                            if (event.changes.size > 1) {
                                if (!canceled) {
                                    val zoomChange = event.calculateZoom()
                                    val rotationChange = event.calculateRotation()
                                    val panChange = event.calculatePan()

                                    if (!pastTouchSlop) {
                                        zoom *= zoomChange
                                        transformRotation += rotationChange
                                        pan += panChange

                                        val centroidSize =
                                            event.calculateCentroidSize(useCurrent = false)
                                        val zoomMotion = abs(1 - zoom) * centroidSize
                                        val rotationMotion =
                                            abs(transformRotation * PI.toFloat() * centroidSize / 180f)
                                        val panMotion = pan.getDistance()

                                        if (zoomMotion > touchSlop ||
                                            rotationMotion > touchSlop ||
                                            panMotion > touchSlop
                                        ) {
                                            pastTouchSlop = true
                                            lockedToPanZoom =
                                                zoomableState.rotationBehavior == ZoomableState.Rotation.LOCK_ROTATION_ON_ZOOM_PAN && rotationMotion < touchSlop
                                        }
                                    }

                                    if (pastTouchSlop) {
                                        val eventCentroid =
                                            event.calculateCentroid(useCurrent = false)
                                        val effectiveRotation =
                                            if (lockedToPanZoom) 0f else rotationChange
                                        if (effectiveRotation != 0f ||
                                            zoomChange != 1f ||
                                            panChange != Offset.Zero
                                        ) {
                                            onTransformGesture(
                                                eventCentroid,
                                                panChange,
                                                zoomChange,
                                                effectiveRotation
                                            )
                                        }
                                        event.changes.fastForEach {
                                            if (it.positionChanged()) {
                                                it.consume()
                                            }
                                        }
                                    }
                                }
                            } else if (transformEventCounter > 3) relevant = false
                            transformEventCounter++
                        } while (!canceled && event.changes.fastAny { it.pressed } && relevant)

                        if (zoomableState.dragGesturesEnabled()) {
                            do {
                                awaitPointerEvent()
                                drag = awaitTouchSlopOrCancellation(down.id) { change, over ->
                                    if (change.positionChange() != Offset.Zero) change.consume()
                                    overSlop = over
                                }
                            } while (drag != null && !drag.isConsumed)
                            if (drag != null) {
                                dragOffset = Offset.Zero
                                if (zoomableState.scale.value !in 0.92f..1.08f) {
                                    coroutineScope.launch {
                                        zoomableState.transform {
                                            transformBy(1f, overSlop, 0f)
                                        }
                                    }
                                } else {
                                    dragOffset += overSlop
                                }
                                if (drag(drag.id) {
                                    if (zoomableState.scale.value !in 0.92f..1.08f) {
                                        zoomableState.offset.value += it.positionChange()
                                    } else {
                                        dragOffset += it.positionChange()
                                    }
                                    if (it.positionChange() != Offset.Zero) it.consume()
                                }
                                ) {
                                    if (zoomableState.scale.value in 0.92f..1.08f) {
                                        val offsetX = dragOffset.x
                                        if (offsetX > minimumSwipeDistance) {
                                            onSwipeRight()
                                        } else if (offsetX < -minimumSwipeDistance) {
                                            onSwipeLeft()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .offset {
                    IntOffset(
                        zoomableState.offset.value.x.roundToInt(),
                        zoomableState.offset.value.y.roundToInt()
                    )
                }
                .graphicsLayer(
                    scaleX = zoomableState.scale.value,
                    scaleY = zoomableState.scale.value,
                    rotationZ = zoomableState.rotation.value
                )
                .onGloballyPositioned { coordinates ->
                    val localOffset =
                        Offset(
                            coordinates.size.width.toFloat() / 2,
                            coordinates.size.height.toFloat() / 2
                        )
                    val windowOffset = coordinates.localToWindow(localOffset)
                    composableCenter =
                        coordinates.parentLayoutCoordinates?.windowToLocal(windowOffset)
                            ?: Offset.Zero
                }
        ) {
            content()
        }
    }
}
