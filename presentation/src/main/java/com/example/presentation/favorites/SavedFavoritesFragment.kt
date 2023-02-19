package com.example.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.presentation.databinding.FavoritesFragmentLayoutBinding
import com.example.presentation.displayImage.DisplayImageViewModel
import com.example.presentation.home.adapter.ImageAdapter
import com.example.presentation.utils.BaseFragment
import com.example.presentation.utils.DisplayImageSource
import dagger.hilt.android.AndroidEntryPoint


/**
 * this screen logic can be implemented into the home screen
 */

@AndroidEntryPoint
class SavedFavoritesFragment :
    BaseFragment<FavoritesFragmentLayoutBinding>(FavoritesFragmentLayoutBinding::inflate) {


    private val displayImageViewModel: DisplayImageViewModel by viewModels()
    private val savedFavoritesViewModel: SavedFavoritesViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        allFavorite()
        observerFavoriteState()
        observerRemovedState()
    }


    private fun initAdapter() {
        with(binding.imageRv) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            imageAdapter = ImageAdapter(DisplayImageSource.FAVORITES, ::goToDisplayImage) {isChecked, photos ->
                removeFavorite(photos)
            }
            this.adapter = imageAdapter
        }
    }


    private fun removeFavorite(photos: Photos) {
        displayImageViewModel.removeFavorite(photos)
    }

    private fun observerRemovedState() {
        displayImageViewModel.removeState.observe(viewLifecycleOwner) { item ->
            when (item) {
                is DataState.Loading -> {
                    showLoading()
                }

                is DataState.Success -> {
                    showToast("Removed from favorite")
                    allFavorite()
//                    hideLoading()
                }

                is DataState.Error -> {
                    hideLoading()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }

    private fun allFavorite() {
        savedFavoritesViewModel.allFavorite()
    }

    private fun observerFavoriteState() {
        savedFavoritesViewModel.favoriteState.observe(viewLifecycleOwner) { item ->
            when (item) {
                is DataState.Loading -> {
                    showLoading()
                }

                is DataState.Success -> {
                    item.data?.let(imageAdapter::updateImageList)
                        ?: showToast("Favorite is empty")
                    hideLoading()
                }

                is DataState.Error -> {
                    hideLoading()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }

    private fun goToDisplayImage(photos: Photos) {
        val action = SavedFavoritesFragmentDirections.actionSavedFavoritesFragmentToDisplayImageFragment(photos)
        findNavController().navigate(action)
    }

}