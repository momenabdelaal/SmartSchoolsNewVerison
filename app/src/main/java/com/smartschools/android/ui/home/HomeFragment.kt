package com.smartschools.android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.smartschools.android.databinding.FragmentHomeBinding

import dagger.hilt.android.AndroidEntryPoint
import java.util.*


//@AndroidEntryPoint
class HomeFragment : Fragment() {

    val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {


        initScreen()
        return binding.root
    }


    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()

    }

    private fun callBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("TAG", "handleOnBackPressed: ")
                    requireActivity().finishAndRemoveTask()
                }
            })
    }




}