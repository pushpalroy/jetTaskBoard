package com.jetapps.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.components.WorkshopCard
import com.jetapps.jettaskboard.model.Board

@Composable
fun CardListResult(
    cardList: List<Board>,
    navigateToTaskBoard: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(cardList) {
            WorkshopCard(
                modifier = Modifier.clickable { navigateToTaskBoard("") },
                title = it.title,
                imageUrl = it.imageUrl,
                isWorkshopStarred = it.isStarred
            )
        }
    }
}
