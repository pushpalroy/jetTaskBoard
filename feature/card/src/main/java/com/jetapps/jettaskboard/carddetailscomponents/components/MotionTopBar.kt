package com.jetapps.jettaskboard.carddetailscomponents.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.theme.MotionTopBarColor

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionTopBar(
    scrollState: ScrollState,
    isExpandedScreen: Boolean,
    onCancelClick: () -> Unit,
    coverImageUrl: String?,
    title: String?
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (scrollState.value in 0..4 && coverImageUrl != null && !isExpandedScreen) 0f else 1f,
        tween(300)
    )

    val motionHeight by animateDpAsState(
        targetValue = if (scrollState.value in 0..4 && coverImageUrl != null && !isExpandedScreen) 180.dp else 56.dp,
        tween(300)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("motion_top_bar")
    ) {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(motionHeight)
        ) {
            Box(
                modifier = Modifier
                    .layoutId("box")
                    .background(color = MotionTopBarColor)
            )

            if (coverImageUrl != null) {
                Image(
                    painter = painterResource(id = R.drawable.backlog),
                    contentDescription = "Backlog",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .layoutId("bg_image")
                )

                Box(
                    modifier = Modifier
                        .layoutId("box_shadow")
                        .background(
                            brush = Brush.verticalGradient(
                                startY = 0f,
                                endY = 180f,
                                colors = listOf(
                                    Color.Black.copy(
                                        alpha = .3f
                                    ),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }

            Text(
                text = "Backlog",
                color = Color.White,
                modifier = Modifier.layoutId("item_name"),
                fontSize = 24.sp
            )
            CoverTab(modifier = Modifier.layoutId("cover_tab"))

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close the App Bar",
                modifier = Modifier
                    .layoutId("cross_icon")
                    .clickable {
                        onCancelClick()
                    },
                tint = Color.White
            )

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Open Menu",
                modifier = Modifier
                    .layoutId("more_icon")
                    .clickable {
                    },
                tint = Color.White
            )
        }
        Divider()
    }
}

@Composable
fun CoverTab(modifier: Modifier) {
    Surface(
        color = Color.Transparent,
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xff2c2c2e), shape = RoundedCornerShape(4.dp))
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cover),
                contentDescription = "Cover Box",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Cover", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
