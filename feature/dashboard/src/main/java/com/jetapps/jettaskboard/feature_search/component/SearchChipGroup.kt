package com.jetapps.jettaskboard.feature_search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.feature_search.data.SearchType
import com.jetapps.jettaskboard.feature_search.data.getAllSearchType

@Composable
fun SearchChipGroup(
    allSearchType: List<SearchType> = getAllSearchType(),
    selectedCar: SearchType? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSecondary.copy(alpha = 0.25f))
    ) {
        LazyRow(
            modifier = Modifier
                .padding(all = 8.dp)
        ){
            items(allSearchType) {
                Chip(
                    name = it.type,
                    isSelected = selectedCar == it,
                    onSelectionChanged = { selectedItem ->
                        onSelectedChanged(selectedItem)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchChipPreview() {
    SearchChipGroup()
}