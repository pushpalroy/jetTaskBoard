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

  /**
   * All methods here are temporary for Demonstration
   * Purpose
   */
  val boardList = listOf(
    Board("Praxis", getImageUrls()[0]),
    Board("Discord Clone", getImageUrls()[1]),
    Board("Trello Workspace", getImageUrls()[2]),
    Board("Praxis Flutter", getImageUrls()[3]),
    Board("JetTaskBoard", getImageUrls()[4]),
    Board("Google Play Clone", getImageUrls()[5]),
    Board("JetFlix", getImageUrls()[6])
  )

  private fun getImageUrls() =
    listOf(
      "https://images.unsplash.com/photo-1508264165352-258db2ebd59b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=776&q=80",
      "https://images.unsplash.com/photo-1506792006437-256b665541e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
      "https://images.unsplash.com/photo-1500042600524-37ecb686c775?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
      "https://images.unsplash.com/photo-1514463439976-96c5d33f0d57?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80",
      "https://images.unsplash.com/photo-1503149779833-1de50ebe5f8a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
      "https://images.unsplash.com/photo-1531176175280-33e81422832a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
      "https://images.unsplash.com/photo-1657571484167-9975a442ecbb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1874&q=80",
    )

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

data class Board(
  val title: String,
  val imageUrl: String
)