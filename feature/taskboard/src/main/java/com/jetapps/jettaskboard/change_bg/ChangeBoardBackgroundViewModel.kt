package com.jetapps.jettaskboard.change_bg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.usecase.board.UpdateTaskBoardBackgroundImgUriUseCase
import com.jetapps.jettaskboard.usecase.network.GetRandomPhotoListUseCase
import com.jetapps.jettaskboard.usecase.network.SearchQueryForImageListUseCase
import com.jetapps.jettaskboard.util.Result
import com.jetapps.jettaskboard.util.UIState
import com.jetapps.jettaskboard.util.UnsplashCollection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ChangeBoardBackgroundViewModel @Inject constructor(
    private val getRandomPhotoListUseCase: GetRandomPhotoListUseCase,
    private val updateTaskBoardBackgroundImgUriUseCase: UpdateTaskBoardBackgroundImgUriUseCase,
    private val getSearchPhotoListUseCase: SearchQueryForImageListUseCase
) : ViewModel() {

    private var _screenState = mutableStateOf(ChangeBackgroundScreenState.STATIC_SCREEN)
    val state: State<ChangeBackgroundScreenState> = _screenState

    private var _randomPhotoList =
        mutableStateOf<UIState<List<ChangeBackgroundPhotoModel>>>(UIState.Loading)
    var randomPhotoList = _randomPhotoList

    private val _textSearch = MutableStateFlow<String?>(null)
    val textSearch: StateFlow<String?> = _textSearch.asStateFlow()

    init {
        viewModelScope.launch {
            _textSearch.debounce(1600).collect { searchQuery ->
                searchQuery?.let { safeQueryString ->
                    _randomPhotoList.value = UIState.Loading
                    when (val result = getSearchPhotoListUseCase.invoke(query = safeQueryString)) {
                        is Result.Failure -> {
                            _randomPhotoList.value = UIState.Failure(Throwable(result.message))
                        }
                        is Result.Success -> {
                            result.data?.let { modelList ->
                                _randomPhotoList.value = UIState.Success(modelList)
                            }
                        }
                        else -> {
                            _randomPhotoList.value = UIState.Empty
                        }
                    }
                }
            }
        }
    }

    fun changeScreenState(newState: ChangeBackgroundScreenState) {
        _screenState.value = newState
    }

    fun generateRandomPhotoList(collectionId: Int) = viewModelScope.launch {
        _randomPhotoList.value = UIState.Loading
        when (val result =
            getRandomPhotoListUseCase.invoke(collectionId)) {
            is Result.Failure -> {
                _randomPhotoList.value = UIState.Failure(Throwable(result.message))
            }
            is Result.Success -> {
                result.data?.let { modelList ->
                    _randomPhotoList.value = UIState.Success(modelList)
                }
            }
        }
    }

    fun updateLatestBoardBgImgUri(imageUri: String) = viewModelScope.launch {
        updateTaskBoardBackgroundImgUriUseCase.invoke(imageUri)
    }

    fun setSearchText(updatedQuery: String) {
        _textSearch.value = updatedQuery
    }
}