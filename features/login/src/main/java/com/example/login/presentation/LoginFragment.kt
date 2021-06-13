package com.example.login.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.commons.BindingFragment
import com.example.core.SharedPrefUtil
import com.example.login.viewmodel.LoginViewModel
import com.example.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>() {
    @Inject
    lateinit var encryptedPrefs: SharedPrefUtil
    private val viewModel by viewModels<LoginViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        goToSignUp()
        getSafeArgs()
        getInputStatus()
        checkAuthenticated()
    }

    private fun getSafeArgs() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("email")
            ?.observe(viewLifecycleOwner) {
                viewModel.email.value = it
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("password")
            ?.observe(viewLifecycleOwner) {
                viewModel.password.value = it
            }
    }

    private fun goToSignUp() {
        binding.textviewGotoSignup.setOnClickListener {
//            Navigation.findNavController(binding.root)
//                .navigate(R.id.action_logInFragment_to_signUpFragment)
        }
    }

    private fun getInputStatus() {
        binding.buttonLogin.setOnClickListener {
            viewModel.getLoginResult()
        }
    }

    private fun login() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success == true) {
                encryptedPrefs.updatePrefs("authenticated", true)
                moveToHome()
            } else {
                Toast.makeText(requireContext(), "Please check email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAuthenticated() {
        if (encryptedPrefs.getPrefs("authenticated", false)) {
            moveToHome()
        } else {
            login()
        }
    }

    private fun moveToHome() {
        val uri = Uri.parse("com.example.home/fragmentHome")
        findNavController().navigate(uri)
    }
}