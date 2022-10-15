package com.jetapps.jettaskboard

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.jetapps.jettaskboard.theme.LabelBlue
import com.jetapps.jettaskboard.theme.LabelGreen
import com.jetapps.jettaskboard.theme.LabelOrange
import com.jetapps.jettaskboard.theme.LabelPeach
import com.jetapps.jettaskboard.theme.LabelViolet
import com.jetapps.jettaskboard.theme.LabelYellow
import com.jetapps.jettaskboard.uimodel.CardDetail
import com.jetapps.jettaskboard.uimodel.LabelColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor() : ViewModel() {

    val cardModel = mutableStateOf(CardDetail(coverImageUrl = "fsd"))

    val imageAttachmentList = mutableStateListOf<Uri>()

    val labels = mutableStateListOf(
        LabelColor(LabelGreen),
        LabelColor(LabelYellow),
        LabelColor(LabelPeach),
        LabelColor(LabelOrange),
        LabelColor(LabelViolet),
        LabelColor(LabelBlue)
    )

    val selectedColors = mutableStateListOf<Color>()

    var isLabelRowClicked = mutableStateOf(false)

    var inputValue = mutableStateOf(TextFieldValue(cardModel.value.description ?: ""))

    val isTopText = mutableStateOf(false)

    val isBottomText = mutableStateOf(false)

    val startDateText = mutableStateOf(cardModel.value.startDate ?: "Start Date...")

    val dueDateText = mutableStateOf(cardModel.value.dueDate ?: "Due Date...")
}
