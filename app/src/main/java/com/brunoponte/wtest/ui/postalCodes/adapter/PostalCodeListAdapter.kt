package com.brunoponte.wtest.ui.postalCodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.brunoponte.wtest.databinding.PostalCodeListItemBinding
import com.brunoponte.wtest.domainModels.PostalCode

class PostalCodeListAdapter : ListAdapter<PostalCode, PostalCodeViewHolder>(PostalCodeListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostalCodeViewHolder {
        val itemBinding = PostalCodeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostalCodeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostalCodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object : DiffUtil.ItemCallback<PostalCode>() {

        override fun areItemsTheSame(oldItem: PostalCode, newItem: PostalCode): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostalCode, newItem: PostalCode): Boolean {
            return oldItem == newItem
        }
    }
}