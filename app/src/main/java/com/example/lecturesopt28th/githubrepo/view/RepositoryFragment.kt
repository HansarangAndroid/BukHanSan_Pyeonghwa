package com.example.lecturesopt28th.githubrepo.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lecturesopt28th.base.BindingFragment
import com.example.lecturesopt28th.databinding.FragmentRepositoryBinding
import com.example.lecturesopt28th.githubrepo.viewmodel.GithubRepoViewModel
import com.example.lecturesopt28th.utils.ItemDecoration
import com.example.lecturesopt28th.utils.ItemTouchCallback
import com.example.lecturesopt28th.utils.ItemTouchListener
import com.example.lecturesopt28th.utils.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@Deprecated("Use feature/login Module")
@AndroidEntryPoint
class RepositoryFragment : BindingFragment<FragmentRepositoryBinding>() {
    private val viewModel by viewModels<GithubRepoViewModel>()
    private lateinit var githubRepoAdapter: GithubRepoAdapter
    private val args: RepositoryFragmentArgs by navArgs()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepositoryBinding {
        return FragmentRepositoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.changeUserName(args.username)
        viewModel.getGithubRepo()
        initRepoRecyclerView()
        getGithubRepo()
        callbackItemTouch()
    }

    private fun initRepoRecyclerView() {
        githubRepoAdapter = GithubRepoAdapter(object : GithubRepoAdapter.ItemClickListener {
            override fun onItemCLickListener(view: View, position: Int) {
                moveGithubRepo(position)
            }
        })

        binding.recyclerviewRepository.apply {
            adapter = githubRepoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(ItemDecoration(12, 10))
            setLayoutManager()
        }
    }

    private fun getGithubRepo() {
        viewModel.repositories.observe(viewLifecycleOwner) {
            when (it.status) {
                UiState.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                UiState.Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    githubRepoAdapter.submitList(it.data)
                }
                UiState.Status.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    Snackbar.make(binding.root, "failed to get repositories", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun moveGithubRepo(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(viewModel.repositories.value?.data?.get(position)?.url)
        startActivity(intent)
    }

    private fun setLayoutManager() {
        viewModel.switchStatus.observe(viewLifecycleOwner) { isChecked ->
            when (isChecked) {
                true -> binding.recyclerviewRepository.apply {
                        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
                    }
                false -> binding.recyclerviewRepository.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    }
            }
        }
    }

    private fun callbackItemTouch() {
        val itemTouchCallback = ItemTouchCallback(object : ItemTouchListener {
            override fun deleteItem(position: Int) {
                showDeleteDialog(position)
            }
            override fun moveItem(pos1: Int, pos2: Int) {
                viewModel.remapRepositories(pos1, pos2)
                githubRepoAdapter.notifyItemMoved(pos1, pos2)
            }
        })
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewRepository)
    }

    private fun showDeleteDialog(position: Int) {
        val dialog = DeleteDialogFragment(object : DeleteDialogFragment.DeleteCallback {
            override fun delete() {
                viewModel.repositories.value?.data?.get(position)?.let {
                    viewModel.changeBackUpRepo(it)
                }
                viewModel.removeRepository(position)
                githubRepoAdapter.notifyItemRemoved(position)

                val snackbar = Snackbar.make(binding.root, "Repository removed Successfully", Snackbar.LENGTH_SHORT)
                snackbar.setAction("Undo") {
                    recoverRepository(position)
                }.show()
            }
            override fun cancel() {
                githubRepoAdapter.notifyItemChanged(position)
            }
            override fun exit() {
                githubRepoAdapter.notifyItemChanged(position)
            }
        })
        dialog.show(childFragmentManager, "Delete Dialog")
    }

    private fun recoverRepository(position: Int) {
        val repository = viewModel.backUpRepo.value
        viewModel.insertRepository(position,repository)
        githubRepoAdapter.notifyItemInserted(position)
    }
}