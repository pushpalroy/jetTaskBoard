package com.jetapps.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetapps.search.data.SearchType
import com.jetapps.search.data.getAllSearchType

@Composable
fun SearchExpandedChipGroup(
    allSearchType: List<SearchType> = getAllSearchType(),
    selectedCar: SearchType? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(Color.Transparent)
    ) {
        LazyColumn {
            items(allSearchType) {
                ExpandedScreenChip(
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
fun SearchExpandedChipGroupPreview() {
    SearchExpandedChipGroup()
}