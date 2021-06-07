package com.example.lecturesopt28th.githubrepo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lecturesopt28th.BR
import com.example.lecturesopt28th.databinding.ItemRepositoryBinding
import com.example.model.github.entity.GithubRepositoryModel

class GithubRepoAdapter(private val listener: ItemClickListener): ListAdapter<GithubRepositoryModel, GithubRepoAdapter.GithubRepoViewHolder> (
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
            root.setOnClickListener {
                listener.onItemCLickListener(this.root, position)
            }
        }
    }

    companion object {
        val githubDiffUtil = object : DiffUtil.ItemCallback<GithubRepositoryModel>(){
            override fun areItemsTheSame(
                oldItem: GithubRepositoryModel,
                newItem: GithubRepositoryModel
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: GithubRepositoryModel,
                newItem: GithubRepositoryModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GithubRepoViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root)
}