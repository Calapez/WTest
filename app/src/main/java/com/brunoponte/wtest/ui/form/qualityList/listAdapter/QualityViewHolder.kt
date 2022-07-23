package com.brunoponte.wtest.ui.form.qualityList.listAdapter

import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.R
import com.brunoponte.wtest.databinding.ArticleListItemBinding
import com.brunoponte.wtest.databinding.QualityListItemBinding
import com.brunoponte.wtest.domainModels.Article

class QualityViewHolder(
    private val binding: QualityListItemBinding,
    private val interaction: QualityListInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(quality: String, position: Int) {

        binding.apply {
            textQuality.text = quality

            root.setOnClickListener {
                interaction.onClick(position, quality)
            }
        }
    }
}