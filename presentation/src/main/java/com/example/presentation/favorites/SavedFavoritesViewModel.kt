package com.example.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.useCase.FavoritesPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedFavoritesViewModel @Inject constructor (private val favoritesPhotosUseCase: FavoritesPhotosUseCase): ViewModel() {


    private val _favoriteState: MutableLiveData<DataState<List<Photos>?>> =
        MutableLiveData()

    val favoriteState: LiveData<DataState<List<Photos>?>>
        get() = _favoriteState



    fun allFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesPhotosUseCase.invoke()
                .collect {
                    _favoriteState.postValue(it)
                }
        }
    }


}