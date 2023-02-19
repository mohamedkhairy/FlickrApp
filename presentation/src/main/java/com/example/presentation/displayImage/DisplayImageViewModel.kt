package com.example.presentation.displayImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.useCase.AllGalleryPhotosUseCase
import com.example.domain.useCase.ToggleFavoritesPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayImageViewModel @Inject constructor (private val toggleFavoritesPhotosUseCase: ToggleFavoritesPhotosUseCase): ViewModel() {


    private val _addState: MutableLiveData<DataState<Unit>> =
        MutableLiveData()

    val addState: LiveData<DataState<Unit>>
        get() = _addState

    private val _removeState: MutableLiveData<DataState<Int?>> =
        MutableLiveData()

    val removeState: LiveData<DataState<Int?>>
        get() = _removeState

    private val _isFavoriteState: MutableLiveData<DataState<Boolean>> =
        MutableLiveData()

    val isFavoriteState: LiveData<DataState<Boolean>>
        get() = _isFavoriteState


    fun addToFavorite(photos: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoritesPhotosUseCase.add(photos)
                .collect {
                    _addState.postValue(it)
                }
        }
    }

    fun removeFavorite(photos: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoritesPhotosUseCase.remove(photos)
                .collect {
                    _removeState.postValue(it)
                }
        }
    }

    fun isFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleFavoritesPhotosUseCase.isFavorite(id)
                .collect {
                    _isFavoriteState.postValue(it)
                }
        }
    }
}