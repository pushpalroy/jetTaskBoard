package com.jetapps.jettaskboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.usecase.AddCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  private val addCardUseCase: AddCardUseCase
) : ViewModel() {

  init {
    addNewCard()
  }

  private fun addNewCard() {
    viewModelScope.launch {
      addCardUseCase.invoke(
        CardModel(
          title = "First card"
        )
      )
    }
  }
}