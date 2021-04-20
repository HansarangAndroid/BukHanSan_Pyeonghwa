package com.example.lecturesopt28th.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lecturesopt28th.R
import com.example.lecturesopt28th.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint

class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding ?: error("login fragment binding error")
    private val viewModel by viewModels<LogInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
        goToSignUp()
        getSafeArgs()
    }

    private fun getSafeArgs() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("id")
            ?.observe(viewLifecycleOwner) {
                viewModel.id.value = it
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("password")
            ?.observe(viewLifecycleOwner){
                viewModel.password.value = it
            }
    }

    private fun goToSignUp() {
        binding.textviewGotoSignup.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_logInFragment_to_signUpFragment)
        }
    }

    private fun login() {
        binding.buttonLogin.setOnClickListener {
            if(checkInputText()) {
                Toast.makeText(requireContext(), "Please input email or password", Toast.LENGTH_SHORT).show()
            } else {
                val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(viewModel.id.value!!)
                Navigation.findNavController(binding.root).navigate(action)
                Log.e("id", viewModel.id.toString())
                Log.e("id", viewModel.password.toString())
            }
        }
    }

    private fun checkInputText(): Boolean {
        return (binding.edittextId.text.isNullOrEmpty()
                || binding.edittextPassword.text.isNullOrEmpty())
    }
}