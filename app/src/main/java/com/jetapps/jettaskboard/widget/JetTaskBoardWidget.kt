package com.jetapps.jettaskboard.widget

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentSize
import androidx.glance.layout.wrapContentWidth
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R
import com.jetapps.jettaskboard.widget.component.CardStatComponent
import com.jetapps.jettaskboard.widget.composable.HorizontalMediumRectangleComposable
import com.jetapps.jettaskboard.widget.composable.SmallestEventWidget
import com.jetapps.jettaskboard.widget.composable.WidestSmallInfoWidget
import java.util.concurrent.TimeUnit

/**
 * Passed a ERROR_WIDGET
 * In case if encounters any Error
 */
class JetTaskBoardWidget() : GlanceAppWidget(
    errorUiLayout = R.layout.jet_task_board_default_error_layout
) {

    companion object {
        private val SMALLEST_RECTANGLE = DpSize(100.dp, 80.dp) // 2x1
        private val WIDEST_SMALLEST_RECTANGLE = DpSize(250.dp, 80.dp) // 4x1
        private val HORIZONTAL_MEDIUM_RECTANGLE = DpSize(250.dp, 100.dp) // 4x2
        val PREF_KEY_LAST_UPDATE_TIME = stringPreferencesKey(name = "last_update_time")
        val PARAM_KEY_LAST_UPDATE_TIME = ActionParameters.Key<String>("last_update_time")
    }

    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(SMALLEST_RECTANGLE, WIDEST_SMALLEST_RECTANGLE, HORIZONTAL_MEDIUM_RECTANGLE)
    )

    @Composable
    override fun Content() {
        val currentUpdateTime = currentState(PREF_KEY_LAST_UPDATE_TIME) ?: "30 mins ago.."

        when (LocalSize.current) {
            SMALLEST_RECTANGLE -> {
                SmallestEventWidget()
            }
            WIDEST_SMALLEST_RECTANGLE -> {
                WidestSmallInfoWidget()
            }
            HORIZONTAL_MEDIUM_RECTANGLE -> {
                HorizontalMediumRectangleComposable(
                    currentUpdateTime
                )
            }
        }
    }
}


