package com.jetapps.jettaskboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetapps.jettaskboard.feature.dashboard.R

@Composable
fun DrawerHeader(
    onDrawerHeaderToggled: () -> Unit?,
    modifier: Modifier
) {
    Column {

        Spacer(modifier = modifier.height(8.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://avatars.githubusercontent.com/u/19844292?v=4")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.temp_place_holder),
            contentDescription = stringResource(R.string.content_description_for_place_holder),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )

        Spacer(modifier = modifier.height(8.dp))
        Text(text = "Pushpal Roy")

        Spacer(modifier = modifier.height(8.dp))
        Text(text = "@puspalroy")

        Row(
            modifier = modifier.padding(all = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "puspalroy2007@gmail.com")

            IconButton(onClick = {
                onDrawerHeaderToggled()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down"
                )
            }
        }
    }
}