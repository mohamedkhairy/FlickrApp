package com.example.presentation.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val layoutInflater : (inflater: LayoutInflater) -> VB
) : Fragment() {


    private val progressUtil by lazy {
        ProgressUtil(requireContext())
    }
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = layoutInflater(inflater)
        return binding.root
    }

    fun showLoading() {
        progressUtil.showLoading()
    }

    fun hideLoading() {
        progressUtil.hideLoading()
    }

    fun showToast(mesg: String){
        Toast.makeText(requireContext(), mesg, Toast.LENGTH_SHORT).show()
    }
}