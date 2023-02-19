package com.example.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.domain.useCase.AllGalleryPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val allGalleryPhotosUseCase: AllGalleryPhotosUseCase): ViewModel() {


    private val _photosListState: MutableLiveData<DataState<MutableList<Photos>?>> =
        MutableLiveData()

    val photosListState: LiveData<DataState<MutableList<Photos>?>>
        get() = _photosListState


    fun getPhotosList() {
        viewModelScope.launch(Dispatchers.IO) {
            allGalleryPhotosUseCase.invoke()
                .collect {
                    _photosListState.postValue(it)
                }
        }
    }

}