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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.lecturesopt28th.R
import com.example.lecturesopt28th.databinding.FragmentHomeBinding
import com.example.lecturesopt28th.home.HomeViewModel
import com.example.lecturesopt28th.home.data.dto.SearchUserModel
import com.example.lecturesopt28th.login.LogInFragmentDirections
import com.thedeanda.lorem.LoremIpsum
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
    }

    private fun initShowUser() {
        viewModel.name.value = args.id
        viewModel.getUserAccessed()
        binding.edittextIdGithub.clearFocus()
    }

    private fun searchGitHubUser() {
        binding.buttonSearch.setOnClickListener {
            if (!binding.edittextIdGithub.text.isNullOrEmpty()) {
                hideKeyboard()
                viewModel.getUserAccessed()
                checkAuthenticatedUser()
            } else {
                Toast.makeText(requireContext(), "Input your github name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAuthenticatedUser() {
        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            if (viewModel.userInfo.value == null) {
                Toast.makeText(requireContext(), "Invalid Github user", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Everthing is Perfect!!!", "---Gang---")
            }
        })
    }
    private fun hideKeyboard() {
        binding.edittextIdGithub.clearFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}