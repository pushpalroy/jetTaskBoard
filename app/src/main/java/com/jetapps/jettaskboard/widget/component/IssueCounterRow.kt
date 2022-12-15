package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R
import com.jetapps.jettaskboard.widget.OpenCardScreenRouteCallBack
import com.jetapps.jettaskboard.widget.OpenSettingScreenRouteCallBack
import com.jetapps.jettaskboard.widget.UpdateTimeStateCallBack

@Composable
fun IssueCounterRow() {
    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(ColorProvider(R.color.color_background_dark_secondary))
            .padding(vertical = 14.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = GlanceModifier
                .padding(start = 8.dp)
                .defaultWeight(),
            text = "Issue Counter",
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 16.sp
            )
        )
        Row(
            modifier = GlanceModifier.defaultWeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_widget_add_vector),
                contentDescription = "Create new card, add icon btn",
                modifier = GlanceModifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = actionRunCallback<OpenCardScreenRouteCallBack>())
            )

            Image(
                provider = ImageProvider(R.drawable.ic_widget_refresh_vector),
                contentDescription = "Refresh data icon",
                modifier = GlanceModifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = actionRunCallback<UpdateTimeStateCallBack>())

            )

            Image(
                provider = ImageProvider(R.drawable.ic_widget_setting_vector),
                contentDescription = "Open setting screen, setting icon",
                modifier = GlanceModifier
                    .padding(horizontal = 8.dp)
                    .clickable(onClick = actionRunCallback<OpenSettingScreenRouteCallBack>())
            )
        }
    }
}