package com.example.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.core.DataState
import com.example.domain.entity.Photos
import com.example.presentation.R
import com.example.presentation.databinding.HomeFragmentLayoutBinding
import com.example.presentation.favorites.SavedFavoritesFragmentDirections
import com.example.presentation.home.adapter.ImageAdapter
import com.example.presentation.utils.BaseFragment
import com.example.presentation.utils.DisplayImageSource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentLayoutBinding>(HomeFragmentLayoutBinding::inflate) {


    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getPhotosList()
        observerPhotoListState()
        setRefresh()
        binding.showFavorites.setOnClickListener {
            findNavController().navigate(R.id.savedFavoritesFragment)
        }
    }

    private fun setRefresh(){
        binding.srl.setOnRefreshListener {
            getPhotosList()
        }
    }

    private fun initAdapter() {
        with(binding.homeImageRv){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            imageAdapter = ImageAdapter (DisplayImageSource.HOME, ::goToDisplayImage) { isChecked, photos -> }
            this.adapter = imageAdapter
        }
    }

    private fun getPhotosList() {
        homeViewModel.getPhotosList()
    }

    private fun observerPhotoListState() {
        homeViewModel.photosListState.observe(viewLifecycleOwner) { item ->
            when (item) {
                is DataState.Loading -> {
                    showSwipeProgress()
                }

                is DataState.Success -> {
                    item.data?.let (imageAdapter::updateImageList)
                    hideSwipeProgress()
                }

                is DataState.Error -> {
                    hideSwipeProgress()
                    showToast(item.error?.message ?: "unknown Error")

                }
            }
        }
    }


    private fun showSwipeProgress(){
        binding.srl.isRefreshing = true
    }
    private fun hideSwipeProgress(){
        binding.srl.isRefreshing = false
    }

    private fun goToDisplayImage(photos: Photos){
        val action = HomeFragmentDirections.actionHomeFragmentToDisplayImageFragment(photos)
        findNavController().navigate(action)
    }

}