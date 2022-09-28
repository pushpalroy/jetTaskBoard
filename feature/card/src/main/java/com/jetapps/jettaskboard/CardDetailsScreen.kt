package com.jetapps.jettaskboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.carddetailscomponents.CardDetailsContent
import com.jetapps.jettaskboard.carddetailscomponents.MotionTopBar
import com.jetapps.jettaskboard.carddetailscomponents.expanded.ExpandedCardDetailsContent
import com.jetapps.jettaskboard.uimodel.CardDetail

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardDetailsRoute(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    viewModel: CardViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val cardModel by remember { mutableStateOf(CardDetail(coverImageUrl = "fsd")) }

        val scrollState = rememberScrollState()
        val expandedLeftScrollState = rememberScrollState()
        val expandedRightScrollState = rememberScrollState()
        Scaffold(
            topBar = {
                MotionTopBar(
                    scrollState = scrollState,
                    isExpandedScreen,
                    onCancelClick,
                    cardModel.coverImageUrl,
                    cardModel.title
                )

            }
        ) {
            if (!isExpandedScreen)
                CardDetailsContent(scrollState, CardDetail(), viewModel)
            else
                ExpandedCardDetailsContent(expandedLeftScrollState, expandedRightScrollState, CardDetail(), viewModel)
        }
    }
}
