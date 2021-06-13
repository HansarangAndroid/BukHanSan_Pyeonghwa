package com.example.home.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.commons.BindingFragment
import com.example.commons.ItemDecoration
import com.example.core.UiState
import com.example.home.viewmodel.HomeViewModel
import com.example.home.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BindingFragment<FragmentHomeBinding>() {
    private lateinit var followersAdapter: FollowersAdapter
    //    private val args: HomeFragmentArgs by navArgs()
    private val viewModel by viewModels<HomeViewModel>()


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel.userId.value = args.id
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        searchGitHubUser()
        checkAuthenticatedUser()
        goToRepository()
        updateFollowers()
        setFollowersAdapter()
    }

    private fun setFollowersAdapter() {
        binding.recyclerviewFollowers.apply{
            followersAdapter = FollowersAdapter{ item ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.githubUrl)
                startActivity(intent)
            }

            adapter = followersAdapter
            addItemDecoration(ItemDecoration(0,5))
        }
    }

    private fun goToRepository() {
        binding.textviewGotoRepository.setOnClickListener {
            val uri = Uri.parse("com.example.githubrepo/fragmentRepository")
//            val action = HomeFragmentDirections.actionHomeFragmentToRepositoryFragment(viewModel.userId.value.toString())
            findNavController().navigate(uri)
        }
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
        viewModel.userModel.observe(viewLifecycleOwner) {
            when(it.status) {
                UiState.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                UiState.Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                }
                UiState.Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Snackbar.make(binding.root, "Invalid Github user", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateFollowers() {
        viewModel.followers.observe(viewLifecycleOwner) {
            when(it.status) {
                UiState.Status.LOADING -> {
                    binding.progressbarFollowers.visibility = View.VISIBLE
                }
                UiState.Status.SUCCESS -> {
                    binding.progressbarFollowers.visibility = View.GONE
                    followersAdapter.submitList(it.data)
                    binding.appbar.setExpanded(false)
                    binding.scrollView2.smoothScrollTo(0, 0)
                }
                UiState.Status.ERROR -> {
                    binding.progressbarFollowers.visibility = View.GONE
                }
            }
        }
    }

    private fun hideKeyboard() {
        binding.edittextIdGithub.clearFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}