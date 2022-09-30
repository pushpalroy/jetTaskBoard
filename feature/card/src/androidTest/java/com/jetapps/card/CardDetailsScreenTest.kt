package com.jetapps.card

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.CardDetailsRoute
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.theme.JtbTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CardDetailsScreenTest {

    @get:Rule
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var cardViewModel: CardViewModel

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            cardViewModel = hiltViewModel()
            JtbTheme {
                CardDetailsRoute(
                    isExpandedScreen = false,
                    onCancelClick = { /*TODO*/ },
                    viewModel = cardViewModel
                )
            }
        }
    }

    @Test
    fun motionTopBar_displayed() {
        composeTestRule.onNodeWithTag("motion_top_bar").assertIsDisplayed()
    }
}