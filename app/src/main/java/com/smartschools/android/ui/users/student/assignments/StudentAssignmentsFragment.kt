package com.smartschools.android.ui.users.student.assignments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.LoadingScreen.hideProgress
import com.smartschools.android.core.appUtils.LoadingScreen.showProgress
import com.smartschools.android.core.appUtils.util.addBasicItemDecoration
import com.smartschools.android.data.model.student.assignment.AssignmentStudentData
import com.smartschools.android.data.model.student.assignment.subjects.SubjectData
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.FragmentAssignmentStudentBinding
import com.smartschools.android.ui.users.student.assignments.adapter.StudentAssignmentsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
@AndroidEntryPoint
class StudentAssignmentsFragment : Fragment() {
    private val viewModel: StudentAssignmentsViewModel by viewModels()

    val binding: FragmentAssignmentStudentBinding by lazy { FragmentAssignmentStudentBinding.inflate(layoutInflater) }
    private val adapter: StudentAssignmentsAdapter by lazy {
        StudentAssignmentsAdapter {

        }
    }


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

    private fun updateUI(uiState: StudentAssignmentsViewModel.UiState) {
        when (uiState) {
            is StudentAssignmentsViewModel.UiState.Loading -> {
                showProgress()
            }

            is StudentAssignmentsViewModel.UiState.Error -> {
                hideProgress()
                Log.d("err", "err"+uiState.errorMsg)
                viewModel.navigateToFragment()
            }

            is StudentAssignmentsViewModel.UiState.Success -> {

                hideProgress()


                initAdapter(uiState.data.data as MutableList<AssignmentStudentData>)

                viewModel.navigateToFragment()


            }
            is StudentAssignmentsViewModel.UiState.SuccessSubjects -> {

                hideProgress()
                initDropDownItems(uiState.data.data as MutableList<SubjectData>)

//                initAdapter(uiState.data.data as MutableList<AssignmentStudentData>)

                viewModel.navigateToFragment()


            }

            StudentAssignmentsViewModel.UiState.Idle -> {
                hideProgress()
            }
        }
    }


    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
   //     callBack()

        val resultModel = SharedPreferencesImpl(requireContext()).getAllData()

        Log.d("testSave Data", "initScreen: "+ resultModel.type)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAssignmentsStudent(0)

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

    private fun initAdapter(mutableList: MutableList<AssignmentStudentData>) {

        adapter.submitFullList(mutableList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addBasicItemDecoration(R.dimen.item_decoration_extra_large_margin)

    }


    private fun initDropDownItems(mutableList: MutableList<SubjectData>){

        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, mutableList)
        binding.dropdownMenu.setAdapter(adapter)

        binding.dropdownMenu.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position) as SubjectData
            Log.d("test", "initDropDownItems: "+item.id)
            viewModel.getAssignmentsStudent(item.id)
        }


    }

}


