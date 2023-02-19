package com.example.presentation.displayImage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.presentation.databinding.DisplayImageFragmentLayoutBinding
import com.example.presentation.utils.BaseFragment
import com.example.presentation.utils.DownloadManager.requestStoragePermissionLauncher
import com.example.presentation.utils.DownloadManager.startDownload
import com.example.presentation.utils.loadAsyncImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayImageFragment : BaseFragment<DisplayImageFragmentLayoutBinding>(DisplayImageFragmentLayoutBinding::inflate) {


    private val displayImageViewModel: DisplayImageViewModel by viewModels()
    private val args: DisplayImageFragmentArgs by navArgs()
    private lateinit var photos: Photos
    private var downloadStarted = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photos = args.selectedPhoto
        isFavorite()
        initView()
        observerAddState()
        observerRemovedState()
        observerIsFavoriteState()
    }

    private fun initView(){
        with(binding){
            fullImage.loadAsyncImage(photos.imageUrl, false)

            download.setOnClickListener {
                requestStoragePermissionLauncher {
                        download.isEnabled = false
                        startDownload(requireContext(), photos.imageUrl, "${photos.title}.jpg") {
                            download.isEnabled = true

                    }
                }
            }

            favoriteCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(buttonView.isPressed)
                    if (isChecked){
                        addToFavorite()
                    }else{
                        removeFavorite()
                    }
            }
        }
    }



    private fun addToFavorite() {
        displayImageViewModel.addToFavorite(photos)
    }

    private fun observerAddState() {
        displayImageViewModel.addState.observe(viewLifecycleOwner) { item ->
            when (item) {
                is DataState.Loading -> {
                    showLoading()
                }

                is DataState.Success -> {
                    showToast("Added to favorite")
                    hideLoading()
                }

                is DataState.Error -> {
                    hideLoading()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }


    private fun removeFavorite() {
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
                    hideLoading()
                }

                is DataState.Error -> {
                    hideLoading()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }



    private fun isFavorite() {
        displayImageViewModel.isFavorite(photos.id)
    }

    private fun observerIsFavoriteState() {
        displayImageViewModel.isFavoriteState.observe(viewLifecycleOwner) { item ->
            when (item) {
                is DataState.Loading -> {
                    showLoading()
                }

                is DataState.Success -> {
                    item.data?.let {
                        if (it)
                            binding.favoriteCheckbox.isChecked = true
                    }
                    hideLoading()
                }

                is DataState.Error -> {
                    hideLoading()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }

}