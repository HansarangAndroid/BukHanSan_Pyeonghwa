package com.example.lecturesopt28th.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.lecturesopt28th.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment(){
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding ?: error("sign up binding error")

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
        completeSingUp()
    }

    private fun completeSingUp() {
        binding.buttonSignUp.setOnClickListener {
            if (checkInputBlank()) {
                Toast.makeText(requireContext(), "You must input all information", Toast.LENGTH_SHORT).show()
            } else {
                val id = binding.edittextId.text.toString()
                val password = binding.edittextPassword.text.toString()
                findNavController().previousBackStackEntry?.savedStateHandle?.set("id", id)
                findNavController().previousBackStackEntry?.savedStateHandle?.set("password", password)
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }

    private fun checkInputBlank(): Boolean {
        return (binding.edittextId.text.isNullOrEmpty()
                || binding.edittextName.text.isNullOrEmpty()
                || binding.edittextPassword.text.isNullOrEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}