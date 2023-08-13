package com.smartschools.android.ui.basic.homeMenu


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.lukedeighton.wheelview.WheelView.OnWheelItemSelectListener
import com.lukedeighton.wheelview.adapter.WheelAdapter
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.Constants
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.FragmentHomeMenuBinding


//@AndroidEntryPoint
class HomeMenuFragment : Fragment() {

    val binding: FragmentHomeMenuBinding by lazy { FragmentHomeMenuBinding.inflate(layoutInflater) }

    var drawables: Array<Int>? = emptyArray()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        initScreen()
        return binding.root
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()

        drawables = if (SharedPreferencesImpl(requireContext()).getLanguage() == "ar") {
            arrayOf(
                R.drawable.ic_privacy_ar,
                R.drawable.abouts_ar,
                R.drawable.contact_us_ar,
                R.drawable.jobs_ar,
                R.drawable.partners_ar,
                R.drawable.acadmey_ar,
                R.drawable.support_ar
            )
        } else {
            arrayOf(
                R.drawable.privacy,
                R.drawable.about_us,
                R.drawable.contact_us,
                R.drawable.job,
                R.drawable.partners,
                R.drawable.academy,
                R.drawable.support
            )
        }




        binding.wheelview.adapter = object : WheelAdapter {
            override fun getDrawable(position: Int): Drawable {
                val drawable = arrayOf(
                    createOvalDrawable(
                        drawables!![position]
                    )
                )
                return LayerDrawable(drawable)
            }

            override fun getCount(): Int {
                return drawables!!.size
            }
        }
//            //a listener for receiving a callback for when the item closest to the selection angle changes
        //            //a listener for receiving a callback for when the item closest to the selection angle changes
        binding.wheelview.setOnWheelItemSelectedListener(OnWheelItemSelectListener { parent, itemDrawable, position -> })



        binding.linearLogin.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.loginFragment)
            if (SharedPreferencesImpl(requireContext()).getLanguage() == Constants.LANGUAGE_ARABIC)
                binding.root.layoutDirection = View.LAYOUT_DIRECTION_RTL
            else
                binding.root.layoutDirection = View.LAYOUT_DIRECTION_LTR

        }

        binding.tvLang.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.languageFragment)

            if (SharedPreferencesImpl(requireContext()).getLanguage() == Constants.LANGUAGE_ARABIC)
                binding.root.layoutDirection = View.LAYOUT_DIRECTION_RTL
            else
                binding.root.layoutDirection = View.LAYOUT_DIRECTION_LTR

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createOvalDrawable(color: Int): Drawable {
        return getDrawable(requireContext(), color)!!
    }

}