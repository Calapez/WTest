package com.brunoponte.wtest.ui.postalCodeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.brunoponte.wtest.databinding.PostalCodeListItemBinding
import com.brunoponte.wtest.domainModels.PostalCode

interface PostalCodeListInteraction {
    fun onIndexReached(index: Int)
}

class PostalCodeListAdapter(
    private val interaction: PostalCodeListInteraction
) : ListAdapter<PostalCode, PostalCodeViewHolder>(PostalCodeListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostalCodeViewHolder {
        val itemBinding = PostalCodeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostalCodeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PostalCodeViewHolder, position: Int) {
        holder.bind(getItem(position))
        interaction.onIndexReached(position)
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