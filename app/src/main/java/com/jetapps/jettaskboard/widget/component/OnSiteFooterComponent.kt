package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R

@Composable
fun OnSiteFooterComponent() {
    Row(
        modifier = GlanceModifier
            .background(ColorProvider(R.color.color_background_dark_secondary))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = GlanceModifier.padding(start = 8.dp),
            text = "On Site : Trello Clone",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start,
                color = ColorProvider(Color.White)
            )
        )
    }
}