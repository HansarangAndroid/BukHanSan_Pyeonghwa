package com.example.lecturesopt28th.githubrepo.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lecturesopt28th.databinding.FragmentRepositoryBinding
import com.example.lecturesopt28th.githubrepo.viewmodel.GithubRepoViewModel
import com.example.lecturesopt28th.utils.HorizontalItemDecoration
import com.example.lecturesopt28th.utils.ItemDecorationRemover
import com.example.lecturesopt28th.utils.ItemDecorationRemover.removeItemDecorations
import com.example.lecturesopt28th.utils.UiState
import com.example.lecturesopt28th.utils.VerticalItemDecoration
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("repository fragment binding error")
    private val viewModel by viewModels<GithubRepoViewModel>()
    private lateinit var githubAdapter: GithubRepoAdapter
    private val args: RepositoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
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
        githubAdapter = GithubRepoAdapter(object : GithubRepoAdapter.ItemClickListener {
            override fun onItemCLickListener(view: View, position: Int) {
                moveGithubRepo(position)
            }
        })

        binding.recyclerviewRepository.run {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalItemDecoration(12))
            setLayoutManager()
        }
    }

    private fun getGithubRepo() {
        viewModel.repositories.observe(viewLifecycleOwner){
            when(it.status) {
                UiState.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                UiState.Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    githubAdapter.submitList(it.data)
                }

                UiState.Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Snackbar.make(binding.root, "failed to get repositories", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun moveGithubRepo(position:Int){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(viewModel.repositories.value?.data?.get(position)?.clone_url)
        startActivity(intent)
    }

    private fun setLayoutManager() {

        viewModel.switchChecked.observe(viewLifecycleOwner){ isChecked ->
            when(isChecked) {
                true ->
                    binding.recyclerviewRepository.run {
                        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL,false)
                        removeItemDecorations()
                        addItemDecoration(HorizontalItemDecoration(10))
                        addItemDecoration(VerticalItemDecoration(12))
                    }
                false ->{
                    binding.recyclerviewRepository.run {
                        layoutManager = LinearLayoutManager(requireContext())
                        removeItemDecorations()
                        addItemDecoration(VerticalItemDecoration(12))
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}