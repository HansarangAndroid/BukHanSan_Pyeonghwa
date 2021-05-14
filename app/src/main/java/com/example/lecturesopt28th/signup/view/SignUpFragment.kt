package com.example.lecturesopt28th.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.lecturesopt28th.base.BindingFragment
import com.example.lecturesopt28th.databinding.FragmentSignUpBinding
import com.example.lecturesopt28th.signup.viewmodel.SignUpViewModel
import com.example.lecturesopt28th.utils.DatePickerDialog
import com.example.lecturesopt28th.utils.DialogInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BindingFragment<FragmentSignUpBinding>(){
    private val viewModel by viewModels<SignUpViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        selectBirthday()
        getSexChecked()
        getInputStatus()
        signUp()
    }

    private fun selectBirthday() {
        binding.edittextBirth.setOnClickListener {
            val datePicker = DatePickerDialog(object : DialogInterface{
                override fun applyDate(year: String, month: String, day: String) {
                    binding.edittextBirth.setText("$year-$month-$day")
                }
            })
            datePicker.show(requireParentFragment().parentFragmentManager, "picker")
        }
    }

    private fun getSexChecked() {
        binding.groupButtonSex.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                binding.buttonMan.id -> viewModel.changeSex(0)
                binding.buttonWomen.id -> viewModel.changeSex(1)
                else -> throw RuntimeException("error sex checked")
            }
        }
    }

    private fun getInputStatus() {
        binding.buttonSignUp.setOnClickListener {
            viewModel.getBlankValue()
            viewModel.isValueEmpty.observe(viewLifecycleOwner) { isSuccessed ->
                if (isSuccessed) {
                    viewModel.getSignUpResult()
                } else {
                    Toast.makeText(requireContext(), "Input All Information", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun signUp() {
        viewModel.isSuccessed.observe(viewLifecycleOwner){ isSuccessed ->
            if (isSuccessed) {
                goToLoginView()
            } else {
                Toast.makeText(requireContext(), "Sign Up failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToLoginView() {
        val email = binding.edittextEmail.text.toString()
        val password = binding.edittextPassword.text.toString()
        findNavController().previousBackStackEntry?.savedStateHandle?.set("email", email)
        findNavController().previousBackStackEntry?.savedStateHandle?.set("password", password)
        Navigation.findNavController(binding.root).popBackStack()
    }
}