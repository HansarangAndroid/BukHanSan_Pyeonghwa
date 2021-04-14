package com.example.lecturesopt28th.githubrepo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.lecturesopt28th.databinding.FragmentRepositoryBinding
import com.example.lecturesopt28th.githubrepo.viewmodel.GithubRepoViewModel
import com.example.lecturesopt28th.utils.VerticalItemDecoration
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("repository fragment binding error")
    private val viewModel: GithubRepoViewModel by activityViewModels()
    private lateinit var githubAdapter: GithubRepoAdapter
    private val args: RepositoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.changeUserName(args.username)
        viewModel.getGithubRepo()
        initRepoRecyclerView()
        getGithubRepo()
    }

    private fun initRepoRecyclerView() {
        githubAdapter = GithubRepoAdapter()
        binding.recyclerviewRepository.run {
            adapter = githubAdapter
            addItemDecoration(VerticalItemDecoration(12))
        }
    }

    private fun getGithubRepo() {
        viewModel.repositories.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                githubAdapter.submitList(list)
            } else {
                Snackbar.make(binding.root, "failed to get repositories", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}