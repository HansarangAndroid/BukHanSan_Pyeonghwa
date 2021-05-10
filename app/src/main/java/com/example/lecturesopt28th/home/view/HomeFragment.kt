package com.example.lecturesopt28th.home.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lecturesopt28th.databinding.FragmentHomeBinding
import com.example.lecturesopt28th.home.viewmodel.HomeViewModel
import com.example.lecturesopt28th.utils.ItemDecoration
import com.example.lecturesopt28th.utils.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("home fragment binding error")
    private lateinit var followersAdapter: FollowersAdapter
    private val args: HomeFragmentArgs by navArgs()
    val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.userId.value = args.id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShowUser()
        searchGitHubUser()
        checkAuthenticatedUser()
        goToRepository()
        upadateFollowers()
        setFollowersAdapter()
    }

    private fun initShowUser() {
        viewModel.getUserAccessed()
        binding.edittextIdGithub.clearFocus()
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
            val action = HomeFragmentDirections.actionHomeFragmentToRepositoryFragment(viewModel.userId.value.toString())
            findNavController().navigate(action)
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
                    Snackbar.make(binding.root, "Invalid Github user",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun upadateFollowers() {
        viewModel.followers.observe(viewLifecycleOwner) {
            when(it.status) {
                UiState.Status.LOADING -> {
                    binding.progressbarFollowers.visibility = View.VISIBLE
                }
                UiState.Status.SUCCESS -> {
                    binding.progressbarFollowers.visibility = View.GONE
                    followersAdapter.submitList(it.data)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}