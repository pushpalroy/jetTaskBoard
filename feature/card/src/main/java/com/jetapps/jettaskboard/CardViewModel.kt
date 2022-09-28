package com.jetapps.jettaskboard

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.usecase.AddCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
  private val addCardUseCase: AddCardUseCase
) : ViewModel() {

  val imageAttachmentList = mutableStateListOf<Uri>()

  suspend fun addCard(cardModel: CardModel) {
    addCardUseCase.invoke(cardModel)
  }
}