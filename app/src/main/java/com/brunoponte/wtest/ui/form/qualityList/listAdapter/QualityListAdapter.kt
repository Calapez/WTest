package com.brunoponte.wtest.ui.form.qualityList.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.brunoponte.wtest.databinding.QualityListItemBinding

interface QualityListInteraction {
    fun onClick(position: Int, quality: String)
}

class QualityListAdapter(
    private val interaction: QualityListInteraction
) : ListAdapter<String, QualityViewHolder>(QualityListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QualityViewHolder {
        val itemBinding = QualityListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QualityViewHolder(itemBinding, interaction)
    }

    override fun onBindViewHolder(holder: QualityViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private companion object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}