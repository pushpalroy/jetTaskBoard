package com.jetapps.jettaskboard.widget.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentSize
import androidx.glance.layout.wrapContentWidth
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R

@Composable
fun SmallestEventWidget() {
    Row(
        modifier = GlanceModifier
            .appWidgetBackground()
            .background(ImageProvider(R.drawable.outer_widget_corner_background))
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Todo : Add a ActionCallBack
        Image(
            provider = ImageProvider(R.drawable.ic_widget_add_vector),
            contentDescription = "Create card Icon",
            modifier = GlanceModifier
                .wrapContentSize()
                .background(ImageProvider(R.drawable.inner_widget_corner_background))
                .padding(all = 12.dp)
        )

        Spacer(modifier = GlanceModifier.width(8.dp))

        Image(
            provider = ImageProvider(R.drawable.ic_widget_board_vector),
            contentDescription = "Create Board icon",
            modifier = GlanceModifier
                .wrapContentSize()
                .background(ImageProvider(R.drawable.inner_widget_corner_background))
                .padding(all = 12.dp)
        )
    }
}