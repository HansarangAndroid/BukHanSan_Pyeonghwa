package com.example.lecturesopt28th.home.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.lecturesopt28th.databinding.FragmentHomeBinding
import com.example.lecturesopt28th.home.viewmodel.HomeViewModel
import com.example.lecturesopt28th.utils.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val args: HomeFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShowUser()
        searchGitHubUser()
        checkAuthenticatedUser()
    }

    private fun initShowUser() {
        viewModel.userId.value = args.id
        viewModel.getUserAccessed()
        binding.edittextIdGithub.clearFocus()
    }

    private fun searchGitHubUser() {
        binding.buttonSearch.setOnClickListener {
            if (!binding.edittextIdGithub.text.isNullOrEmpty()) {
                hideKeyboard()
                viewModel.getUserAccessed()
            } else {
                Toast.makeText(requireContext(), "Input your github name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAuthenticatedUser() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is UiState.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    binding.progressbar.visibility = View.GONE
                }
                is UiState.Error -> {
                    binding.progressbar.visibility = View.GONE
                    Snackbar.make(binding.root, "Invalid Github user",Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun hideKeyboard() {
        binding.edittextIdGithub.clearFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}