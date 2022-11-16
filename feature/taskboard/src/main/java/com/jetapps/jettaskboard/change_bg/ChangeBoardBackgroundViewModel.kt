package com.jetapps.jettaskboard.change_bg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.usecase.network.GetRandomPhotoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeBoardBackgroundViewModel @Inject constructor(
    private val getRandomPhotoListUseCase: GetRandomPhotoListUseCase
) : ViewModel() {

    private var _screenState = mutableStateOf(ChangeBackgroundScreenState.STATIC_SCREEN)
    val state: State<ChangeBackgroundScreenState> = _screenState

    private val _randomPhotoList = mutableStateOf<List<ChangeBackgroundPhotoModel>?>(listOf())
    var randomPhotoList = _randomPhotoList

    fun changeScreenState(newState: ChangeBackgroundScreenState) {
        _screenState.value = newState
    }

    fun generateRandomPhotoList() = viewModelScope.launch {
       getRandomPhotoListUseCase.invoke().let {
           _randomPhotoList.value = it
       }
    }
}