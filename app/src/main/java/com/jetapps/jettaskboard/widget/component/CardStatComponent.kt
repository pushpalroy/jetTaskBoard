package com.jetapps.jettaskboard.widget.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.jetapps.jettaskboard.R

@Composable
fun CardStatComponent(
    count: Int = 0,
    title: String = " ",
    horizontalParentPadding: Dp = 14.dp,
    verticalParentPadding: Dp = 12.dp,
    backgroundDrawableId : Int = R.drawable.inner_widget_corner_background
) {
    Column(
        modifier = GlanceModifier
            .background(ImageProvider(backgroundDrawableId))
            .wrapContentSize()
            .padding(horizontal = horizontalParentPadding, vertical = verticalParentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            modifier = GlanceModifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = ColorProvider(Color.White)
            )
        )

        Spacer(GlanceModifier.height(4.dp))

        Text(
            text = title,
            modifier = GlanceModifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = ColorProvider(Color.White),
                textAlign = TextAlign.Center
            )
        )
    }
}