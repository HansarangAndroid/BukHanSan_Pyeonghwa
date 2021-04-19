package com.example.lecturesopt28th.githubrepo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lecturesopt28th.BR
import com.example.lecturesopt28th.databinding.ItemRepositoryBinding
import com.example.lecturesopt28th.githubrepo.dto.RepositoryModelItem

class GithubRepoAdapter(private val listener: ItemClickListener): ListAdapter<RepositoryModelItem, GithubRepoAdapter.GithubRepoViewHolder>(
    githubDiffUtil
) {
    interface ItemClickListener{
        fun onItemCLickListener(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubRepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.run {
            setVariable(BR.data, item)
            textviewRepostioryDescription.isSelected = true
            textviewRepositoryName.isSelected = true
            repositoryItem.setOnClickListener {
                listener.onItemCLickListener(holder.binding.root, position)
            }
        }
    }

    companion object {
        val githubDiffUtil = object : DiffUtil.ItemCallback<RepositoryModelItem>(){
            override fun areItemsTheSame(
                oldItem: RepositoryModelItem,
                newItem: RepositoryModelItem
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: RepositoryModelItem,
                newItem: RepositoryModelItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GithubRepoViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root)
}