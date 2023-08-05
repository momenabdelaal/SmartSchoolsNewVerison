package com.smartschools.android.ui.basic.language

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.Constants
import com.smartschools.android.core.appUtils.localization.LocaleHelper
import com.smartschools.android.core.appUtils.snackBarFailure
import com.smartschools.android.databinding.FragmentLanguageBinding


//@AndroidEntryPoint
class LanguageFragment : Fragment() {

    val binding: FragmentLanguageBinding by lazy { FragmentLanguageBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        initScreen()
        return binding.root
    }


    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()

        binding.lnArabic.setOnClickListener {
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_ARABIC)
            LocaleHelper.initLanguage(requireActivity(), "ar")
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.welcomeFragment)


        }
        binding.lnEnglish.setOnClickListener {
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_ENGLISH)
            LocaleHelper.initLanguage(requireActivity(), "en")
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.welcomeFragment)


        }
        binding.lnFrench.setOnClickListener {
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_FRENCH)
            LocaleHelper.initLanguage(requireActivity(), "fr")
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.welcomeFragment)
        }
        binding.lnTurkey.setOnClickListener {
            LocaleHelper.initLanguage(requireActivity(), "tr")
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_TURKEY)
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.welcomeFragment)
        }
        binding.lnGermany.setOnClickListener {
            snackBarFailure("under working")
        }


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