package com.jetapps.card

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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

    @Test
    fun quickActions_displayed() {
        composeTestRule.onNodeWithTag("quick_action_card").assertIsDisplayed()
    }

    @Test
    fun quickActionsBar_onClick_showActionChips() {
        composeTestRule.onNodeWithTag("quick_action_card").performClick()
        composeTestRule.onNodeWithTag("quick_action_chip").assertIsDisplayed()
    }

    @Test
    fun labels_displayed() {
        composeTestRule.onNodeWithTag("label").assertIsDisplayed()
        composeTestRule.onNodeWithTag("label_text", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun label_onClick_showMultiColorLabelCheckBox() {
        composeTestRule.onAllNodesWithTag("label_check_box", useUnmergedTree = true)[0].assertDoesNotExist()

        composeTestRule.onNodeWithTag("label").performClick()

        composeTestRule.onNodeWithTag("label_text", useUnmergedTree = true).assertIsDisplayed()

        composeTestRule.onAllNodesWithTag("label_check_box", useUnmergedTree = true)[0].assertIsDisplayed()
    }

    @Test
    fun labelCheckBoxClick_showIcon() {
        composeTestRule.onNodeWithTag("label").performClick()

        composeTestRule.onAllNodesWithTag("label_check_box", useUnmergedTree = true)[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("label_check_icon", useUnmergedTree = true)[0].assertDoesNotExist()

        composeTestRule.onAllNodesWithTag("label_check_box", useUnmergedTree = true)[0].performClick()

        composeTestRule.onAllNodesWithTag("label_check_icon", useUnmergedTree = true)[0].assertIsDisplayed()
    }

    @Test
    fun labelCheckBoxClick_showLabelColor_inLabelRow() {
        composeTestRule.onNodeWithTag("label").performClick()
        composeTestRule.onAllNodesWithTag("label_check_box", useUnmergedTree = true)[0].performClick()

        composeTestRule.onNodeWithTag("labels_row", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("label_card", useUnmergedTree = true)[0].assertIsDisplayed()
        composeTestRule.onNodeWithTag("label_text", useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun startDateClick_showsCalendar() {
        composeTestRule.onNodeWithTag("time_item_row").assertIsDisplayed()

        composeTestRule.onNodeWithTag("start_date", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("start_date", useUnmergedTree = true).performClick()
        composeTestRule.onNode(isDialog(), useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun dueDateClick_showsCalendar() {
        composeTestRule.onNodeWithTag("time_item_row").assertIsDisplayed()

        composeTestRule.onNodeWithTag("due_date", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("due_date", useUnmergedTree = true).performClick()
        composeTestRule.onNode(isDialog(), useUnmergedTree = true).assertIsDisplayed()
    }
}
