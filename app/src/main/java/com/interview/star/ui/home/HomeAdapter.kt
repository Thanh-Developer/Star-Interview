package com.interview.star.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.interview.star.data.remote.model.User
import com.interview.star.databinding.ItemUserBinding

class HomeAdapter : ListAdapter<User, HomeAdapter.TaskViewHolder>(DiffUtilTask()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindItem(it) }
    }

    class TaskViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(userItem: User) {
            binding.apply {
                user = userItem
                executePendingBindings()
            }
        }
    }

    fun getUserByPosition(position: Int): User? {
        return getItem(position)
    }

    private class DiffUtilTask : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem == oldItem
        }
    }
}