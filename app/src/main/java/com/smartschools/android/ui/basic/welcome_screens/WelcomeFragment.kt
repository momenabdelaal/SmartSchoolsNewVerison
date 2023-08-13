package com.smartschools.android.ui.basic.welcome_screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.smartschools.android.R
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.FragmentWelcomeBinding
import com.smartschools.android.ui.basic.welcome_screens.pager.SliderModel
import com.smartschools.android.ui.basic.welcome_screens.pager.ViewPagerAdapter
import java.util.*


//@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    val binding: FragmentWelcomeBinding by lazy { FragmentWelcomeBinding.inflate(layoutInflater) }

    lateinit var viewPagerAdapter: ViewPagerAdapter

    private val fileList: MutableList<SliderModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {


        initScreen()
        return binding.root
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initSlider(fileList: MutableList<SliderModel>) {
        fileList.add(
            SliderModel(
                requireContext().resources.getDrawable(R.drawable.into_guide1),
                requireContext().getString(R.string.slide_1_title),
                requireContext().getString(R.string.slide_1_desc)
            )
        )
        fileList.add(
            SliderModel(
                requireContext().resources.getDrawable(R.drawable.into_guide2),
                requireContext().getString(R.string.slide_2_title),
                requireContext().getString(R.string.slide_2_desc)
            )
        )
        fileList.add(
            SliderModel(
                requireContext().resources.getDrawable(R.drawable.into_guide3),
                requireContext().getString(R.string.slide_3_title),
                requireContext().getString(R.string.slide_3_desc)
            )
        )
        fileList.add(
            SliderModel(
                requireContext().resources.getDrawable(R.drawable.into_guide4),
                requireContext().getString(R.string.slide_4_title),
                requireContext().getString(R.string.slide_4_desc)
            )
        )


        viewPagerAdapter = ViewPagerAdapter(requireActivity(), fileList)

//        binding.viewPager.pageMargin = -420
        binding.viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.notifyDataSetChanged()
        //binding.viewPager.currentItem = 1

        addBottomDots(binding.viewPager.currentItem)

        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                // your code here
                addBottomDots(position)

                if (binding.viewPager.currentItem == 3)
                    binding.btnSkip.text = getString(R.string.start)
                else
                    binding.btnSkip.text = getString(R.string.skip)


            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.btnSkip.setOnClickListener {
            if (binding.btnSkip.text.toString() == getString(R.string.start)) {
                SharedPreferencesImpl(requireContext()).isFirstLaunch("false")

                Navigation.findNavController(
                    requireActivity(),
                    R.id.navHostFragment
                ).navigate(R.id.homeMenuFragment)
            }else
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)

        }

        binding.ivClose.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.navHostFragment
            ).navigate(R.id.homeMenuFragment)
        }

    }

    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()
        initSlider(fileList)


    }

    private fun addBottomDots(currentPage: Int) {
        val dots = arrayOfNulls<ImageView>(fileList.size)
        binding.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(requireContext())
            val parms = LinearLayout.LayoutParams(50, 65)
            dots[i]?.layoutParams = parms
            parms.setMargins(8, 0, 8, 0)
            dots[i]?.setImageResource(R.drawable.nonactive_dot)
            binding.layoutDots.addView(dots[i])
        }
        if (dots.size > 0) {
            val parms = LinearLayout.LayoutParams(50, 50)
            dots[currentPage]?.layoutParams = parms
            parms.setMargins(8, 0, 8, 0)
            dots[currentPage]?.setImageResource(R.drawable.active_dot)
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