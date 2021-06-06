package com.example.lecturesopt28th.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lecturesopt28th.BR
import com.example.lecturesopt28th.databinding.ItemFollowersBinding
import com.example.model.home.entity.FollowerModel

class FollowersAdapter(private val listener: (FollowerModel)-> Unit): ListAdapter<FollowerModel, FollowersAdapter.FollowersViewHolder>(
    diffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = ItemFollowersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            setVariable(BR.data, item)
            root.setOnClickListener {
                listener(item)
            }
        }
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<FollowerModel>(){
            override fun areItemsTheSame(
                oldItemResponse: FollowerModel,
                newItemResponse: FollowerModel
            ): Boolean {
                return oldItemResponse.hashCode() == newItemResponse.hashCode()
            }

            override fun areContentsTheSame(
                oldItemResponse: FollowerModel,
                newItemResponse: FollowerModel
            ): Boolean {
                return oldItemResponse == newItemResponse
            }
        }
    }

    inner class FollowersViewHolder(val binding: ItemFollowersBinding): RecyclerView.ViewHolder(binding.root)

}