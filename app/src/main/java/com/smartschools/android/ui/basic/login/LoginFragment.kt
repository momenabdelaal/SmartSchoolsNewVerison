package com.smartschools.android.ui.basic.login

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
import androidx.navigation.Navigation
import com.smartschools.android.R
import com.smartschools.android.core.appUtils.LoadingScreen.hideProgress
import com.smartschools.android.core.appUtils.LoadingScreen.showProgress
import com.smartschools.android.core.appUtils.snackBarSuccess
import com.smartschools.android.data.persistentStorage.sharedPref.SharedPreferencesImpl
import com.smartschools.android.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class LoginFragment : Fragment() {

    val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }


    private val typeList = mutableListOf<String>()
    private var typeArrayAdapter: ArrayAdapter<String>? = null

    private val viewModel: LoginViewModel by viewModels()

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


    private fun initScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        callBack()
        initType()

        binding.fab.setOnClickListener {
            if (binding.etUsername.text.toString().isEmpty()) {
                binding.etUsername.error = getString(R.string.invalid_email_phone)

            } else if (binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.error = getString(R.string.invalid_password)

            } else {

                binding.etUsername.error = null
                binding.etPassword.error = null

                viewModel.userLogin(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString(),
                    "student"
                )
            }
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


    //endregion
    private fun initType() {

        typeList.add("student")
        typeList.add("teacher")
        typeList.add("parent")
        typeList.add("admin")
        typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.layout_spinner_item, typeList)
        binding.spType.adapter = typeArrayAdapter


    }


    private fun updateUI(uiState: LoginViewModel.UiState) {
        when (uiState) {
            is LoginViewModel.UiState.Loading -> {
                showProgress()
            }

            is LoginViewModel.UiState.Error -> {
                hideProgress()
                Log.d("err", "err")
                viewModel.navigateToConfirmLoginFragment()
            }

            is LoginViewModel.UiState.Success -> {

                hideProgress()
                snackBarSuccess(uiState.data.message)

                val s = SharedPreferencesImpl(requireContext())
                s.setApiKeyToken(uiState.data.result.token)

                Navigation.findNavController(
                    requireActivity(),
                    R.id.navHostFragment
                ).navigate(R.id.homeFragment)


                Log.d("result", "updateUI: " + uiState.data.message)
                viewModel.navigateToConfirmLoginFragment()


            }

            LoginViewModel.UiState.Idle -> {
                hideProgress()
            }
        }
    }


}