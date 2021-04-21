package com.example.lecturesopt28th.home.view

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lecturesopt28th.BR
import com.example.lecturesopt28th.databinding.ItemFollowersBinding
import com.example.lecturesopt28th.home.data.dto.FollowersEntityItem

class FollowersAdapter(private val listener: (FollowersEntityItem)-> Unit): ListAdapter<FollowersEntityItem, FollowersAdapter.FollowersViewHolder>(
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
        private val diffCallback = object : DiffUtil.ItemCallback<FollowersEntityItem>(){
            override fun areItemsTheSame(
                oldItem: FollowersEntityItem,
                newItem: FollowersEntityItem
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: FollowersEntityItem,
                newItem: FollowersEntityItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FollowersViewHolder(val binding: ItemFollowersBinding): RecyclerView.ViewHolder(binding.root)

}