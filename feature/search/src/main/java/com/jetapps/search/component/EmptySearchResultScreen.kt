package com.jetapps.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.loadingcomponents.TrelloComponentLoadingAnimation
import com.jetapps.jettaskboard.loadingcomponents.TrelloLoadingAnimation

@Composable
fun EmptySearchResultScreen(
    modifier: Modifier = Modifier,
    showLoader: Boolean = false, // FOR DEMO PURPOSE
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoader) {
            TrelloComponentLoadingAnimation(
                widthOfCanvas = 40.dp
            )
        } else {
            TrelloLoadingAnimation(
                sizeOfCanvas = 40.dp
            )
        }
    }
}
