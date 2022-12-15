package com.jetapps.jettaskboard.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState

class UpdateTimeStateCallBack : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(
            context = context,
            glanceId
        ) {
            it[JetTaskBoardWidget.PREF_KEY_LAST_UPDATE_TIME] = "Less than a min ago.."
            JetTaskBoardWidget().update(
                context,
                glanceId
            )
        }
    }
}