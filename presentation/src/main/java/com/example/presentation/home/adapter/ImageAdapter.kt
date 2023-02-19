package com.example.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Photos
import com.example.presentation.utils.loadAsyncImage
import com.example.presentation.databinding.ImageCardItemBinding
import com.example.presentation.utils.DisplayImageSource
import com.example.presentation.utils.hideView
import com.example.presentation.utils.showView


class ImageAdapter(val source: DisplayImageSource, val onClick: (Photos) -> Unit, val onChecked: (Boolean, Photos) -> Unit) : RecyclerView.Adapter<ImageAdapter.DataViewHolder>() {

    private val photosList = arrayListOf<Photos>()

    fun updateImageList(list: List<Photos>){
        photosList.clear()
        photosList.addAll(list)
        notifyDataSetChanged()
    }

    inner class DataViewHolder(val binding: ImageCardItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(photos: Photos) {


                binding.apply {
                    title.text = photos.title
                    imagesCover.loadAsyncImage(photos.imageUrl)

                    when(source){
                        DisplayImageSource.HOME -> {
                            favoriteCheckbox.hideView()
                        }
                        DisplayImageSource.FAVORITES ->{
                            favoriteCheckbox.showView()
                            favoriteCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                                if(buttonView.isPressed)
                                    onChecked(isChecked, photos)
                            }
                        }
                    }
                }


                itemView.setOnClickListener { onClick(photos) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding: ImageCardItemBinding = ImageCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }
    override fun getItemCount(): Int = photosList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

}