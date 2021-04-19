package com.example.lecturesopt28th.githubrepo.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lecturesopt28th.databinding.FragmentRepositoryBinding
import com.example.lecturesopt28th.githubrepo.viewmodel.GithubRepoViewModel
import com.example.lecturesopt28th.utils.*
import com.example.lecturesopt28th.utils.ItemDecorationRemover.removeItemDecorations
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

        binding.recyclerviewRepository.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(ItemDecoration(12,0))
            setLayoutManager()
            setItemTouchHelper()
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
                        addItemDecoration(ItemDecoration(12,10))
                    }
                false ->{
                    binding.recyclerviewRepository.run {
                        layoutManager = LinearLayoutManager(requireContext())
                        removeItemDecorations()
                        addItemDecoration(ItemDecoration(12,0))
                    }
                }
            }
            setItemTouchHelper()
        }
    }

    private fun setItemTouchHelper() {
        val swipeHelperCallback = SwipeHelperCallback().apply {
            setClamp(180f)
        }

        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewRepository)

        binding.recyclerviewRepository.run {
            setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(this)
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}