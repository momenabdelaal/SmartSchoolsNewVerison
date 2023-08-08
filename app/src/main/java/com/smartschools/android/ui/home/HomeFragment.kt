package com.smartschools.android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.LoadingScreen.hideProgress
import com.smartschools.android.core.appUtils.LoadingScreen.showProgress
import com.smartschools.android.core.appUtils.snackBarSuccess
import com.smartschools.android.core.appUtils.util.addBasicItemDecoration
import com.smartschools.android.data.model.dashboard.DashboardItem
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.FragmentHomeBinding
import com.smartschools.android.ui.basic.login.LoginViewModel
import com.smartschools.android.ui.home.adapter.HomeAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter: HomeAdapter by lazy {
        HomeAdapter {
            if (it.name == "assignments") {
                Navigation.findNavController(requireActivity().findViewById(R.id.navHostFragment))
                    .navigate(R.id.action_homeFragment_to_assignmentStudentFragment)

            }
        }
    }
//        ProjectsAdapter({
////            //favourite click
////            if (it.ProjectISFav){
////                sharedViewModel.removeProjectFromFavourite(it,requireView())
////                it.ProjectISFav = false
////                adapter.notifyDataSetChanged()
////            }
////            else{
////                sharedViewModel.addProjectToFavourite(it,requireView())
////                it.ProjectISFav = true
////                adapter.notifyDataSetChanged()
////            }
//
//        }) {
//            val bundle = Bundle()
//            bundle.putInt("project_id", it.ProjectID)
//            bundle.putString("nav_from", "homeCard")
//            Navigation.findNavController(requireActivity().findViewById(R.id.navHostFragment))
//                .navigate(R.id.fragmentProjectDetails, bundle)
//        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        observeUIState()
        initScreen()
        return binding.root
    }

    private fun observeUIState() =
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle).collect(::updateUI)

        }

    private fun updateUI(uiState: HomeViewModel.UiState) {
        when (uiState) {
            is HomeViewModel.UiState.Loading -> {
                showProgress()
            }

            is HomeViewModel.UiState.Error -> {
                hideProgress()
                Log.d("err", "err" + uiState.errorMsg)
                viewModel.navigateToConfirmLoginFragment()
            }

            is HomeViewModel.UiState.Success -> {

                hideProgress()
                snackBarSuccess(uiState.data.message)

                initAdapter(uiState.data.data.dashboard_items as MutableList<DashboardItem>)
//
//                val s = SharedPreferencesImpl(requireContext())
//                s.setApiKeyToken(uiState.data.result.token)
//
//                Navigation.findNavController(
//                    requireActivity(),
//                    R.id.navHostFragment
//                ).navigate(R.id.homeFragment)
//
//
//                Log.d("result", "updateUI: " + uiState.data.message)
                viewModel.navigateToConfirmLoginFragment()


            }

            HomeViewModel.UiState.Idle -> {
                hideProgress()
            }
        }
    }


    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDashboard()
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

    private fun initAdapter(mutableList: MutableList<DashboardItem>) {

        adapter.submitFullList(mutableList)
        binding.rvDashboard.adapter = adapter
        binding.rvDashboard.addBasicItemDecoration(R.dimen.item_decoration_extra_large_margin)

    }


}