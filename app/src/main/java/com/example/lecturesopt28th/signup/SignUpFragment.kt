package com.example.lecturesopt28th.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.lecturesopt28th.databinding.FragmentSignUpBinding
import com.example.lecturesopt28th.utils.DatePickerDialog
import com.example.lecturesopt28th.utils.DialogInterface
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException

@AndroidEntryPoint
class SignUpFragment : Fragment(){
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding ?: error("sign up binding error")
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        selectBirthday()
        getSexChecked()
        signUp()
    }

    private fun selectBirthday() {
        binding.edittextBirth.setOnClickListener {
            val datePicker = DatePickerDialog(object : DialogInterface{
                override fun applyDate(year: String, month: String, day: String) {
                    binding.edittextBirth.setText("$year - $month - $day")
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

    //Todo: 성공여부 메세지 세부사항 처리
    private fun signUp() {
        binding.buttonSignUp.setOnClickListener {
            viewModel.signUp()
            viewModel.isSuccessed.observe(viewLifecycleOwner){ isSuccessed ->
                if (isSuccessed) {
                    Toast.makeText(requireContext(), "Success Sign Up", Toast.LENGTH_SHORT).show()
                    goToLoginView()
                } else {
                    Toast.makeText(requireContext(), "Sign Up failed", Toast.LENGTH_SHORT).show()
                }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}