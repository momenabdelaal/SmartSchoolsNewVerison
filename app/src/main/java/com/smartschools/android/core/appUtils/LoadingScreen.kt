package com.smartschools.android.core.appUtils

import androidx.fragment.app.Fragment
import com.smartschools.android.core.base.BaseActivity
import com.exas.qpmoemp.data.persistentStorage.sharedPref.SharedPreferencesImpl

object LoadingScreen {

    fun Fragment.showProgress() {
        if (activity != null)
            (requireActivity() as BaseActivity).showProgress(true)
    }

    fun Fragment.hideProgress() {
        if (activity != null)
            (requireActivity() as BaseActivity).showProgress(false)
    }

    fun Fragment.logOut() {
        val lang = SharedPreferencesImpl(requireActivity()).getLanguage()
        SharedPreferencesImpl(requireActivity()).clearAll()
        SharedPreferencesImpl(requireActivity()).setLanguage(lang)

//        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
//            .navigate(R.id.loginFragment)
//        (requireActivity() as BaseActivity).sharedViewModel.remoteFilters.value.apply { AppliedFilterData() }
//        if ( !(requireActivity() as BaseActivity).sharedViewModel.isHomeMapFragmentActive )
//            (requireActivity() as BaseActivity).sharedViewModel.isHomeMapFragmentActive = true
    }



}