package com.jetapps.jettaskboard.widget

import android.content.Context
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import com.jetapps.jettaskboard.R

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

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        TODO("Not yet implemented")
    }

//    @Composable
//    override fun Content() {
//        val currentUpdateTime = currentState(PREF_KEY_LAST_UPDATE_TIME) ?: "30 mins ago.."
//
//        when (LocalSize.current) {
//            SMALLEST_RECTANGLE -> {
//                SmallestEventWidget()
//            }
//            WIDEST_SMALLEST_RECTANGLE -> {
//                WidestSmallInfoWidget()
//            }
//            HORIZONTAL_MEDIUM_RECTANGLE -> {
//                HorizontalMediumRectangleComposable(
//                    currentUpdateTime
//                )
//            }
//        }
//    }
}


