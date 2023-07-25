package com.smartschools.android.ui.splash

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.exas.qpmoemp.data.persistentStorage.sharedPref.SharedPreferencesImpl


import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.Constants
import com.smartschools.android.core.appUtils.Constants.SPLASH_DISPLAY_TIME
import com.smartschools.android.core.appUtils.localization.LocaleHelper
import com.smartschools.android.core.appUtils.localization.LocalizationUtils
import com.smartschools.android.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

//@AndroidEntryPoint
class SplashFragment : Fragment() {

    var activity: Activity? = null
    var binding: FragmentSplashBinding? = null
    var rootView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        observeUIState()
        rootView = binding!!.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity = getActivity()

//requestExternalStoragePermission()
        //    LocalizationUtils.setDefaultFontConfiguration(requireContext())


        if (SharedPreferencesImpl(requireContext()).getLanguage().isEmpty() ||
            SharedPreferencesImpl(requireContext()).getLanguage() == Constants.LANGUAGE_ARABIC){
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_ARABIC)
            LocaleHelper.setLocale(requireContext(), "ar")
            binding!!.root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }else{
            LocaleHelper.setLocale(requireContext(), "en")
            SharedPreferencesImpl(requireContext()).setLanguage(Constants.LANGUAGE_ENGLISH)
            binding!!.root.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }
        initScreen()
//         LocaleHelper.setLocale(requireActivity(), "ar")
    }
    private fun initScreen() {


//        Log.d("updateUIsucces: ",   SharedPreferencesImpl(requireContext()).getUserId().toString())
//        if (SharedPreferencesImpl(requireContext()).getUserId() != "")
//            viewModel.getPasswordChangedStatus(SharedPreferencesImpl(requireContext()).getUserId().toInt())
//        else
//        {
            setupBoundsImage(true)
//        }
    }
    private fun observeUIState() {
//        lifecycleScope.launch {
//            viewModel.uiState.flowWithLifecycle(lifecycle)
//                .collect(::updateUI)
//
//        }
    }


    private fun startSplashTimer(status: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {

            findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.homeFragment)


            delay(SPLASH_DISPLAY_TIME)

        }

    }


    private fun setupBoundsImage(status: Boolean) {
        requireActivity().window.setFlags(

            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //set animation for logo
        lifecycleScope.launchWhenCreated {
            val bounce: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.animation_fade_in)

            binding!!.logoBounds.visibility = View.VISIBLE
            bounce.also { binding!!.logoBounds.animation = it }
            delay(SPLASH_DISPLAY_TIME)
            startSplashTimer(status)

        }
    }

    override fun onStart() {
        LocalizationUtils.setDefaultFontConfiguration(requireActivity())
        super.onStart()

    }



    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
    private fun requestExternalStoragePermission() {
        val permissions = arrayListOf<String>()

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_NETWORK_STATE)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
        else permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)

        Dexter.withContext(context).withPermissions(permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // permission is denied permenantly, navigate user to app settings
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", context!!.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest?>?, permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).onSameThread().check()
    }


}