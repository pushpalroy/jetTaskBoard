package com.jetapps.jettaskboard.widget

import android.content.Context
import android.widget.Toast
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.jetapps.jettaskboard.navigation.DashboardDestination

class DashboardDestinationActionCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        /**
         * Doubt : How to proceed with the navigation here?
         * Should Do things directly here or
         * pass the data as bundle to MainActivity extract it and then navigate the app?
         */
        Toast.makeText(context, "Home Widget got clicked", Toast.LENGTH_SHORT).show()
    }
}

object ActionCallBackKeys {
    val homeDestinationKey = ActionParameters.Key<String>(DashboardDestination.route)
}