package com.brunoponte.wtest.ui.articleList.listAdapter

import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.R
import com.brunoponte.wtest.databinding.ArticleListItemBinding
import com.brunoponte.wtest.domainModels.Article

class ArticleViewHolder(
    private val binding: ArticleListItemBinding,
    private val interaction: ArticleListInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, position: Int) {
        val notApplicableText = itemView.context.getString(R.string.not_applicable)

        binding.apply {
            textTitle.text = article.title ?: notApplicableText

            textAuthor.text = article.author ?: notApplicableText

            textOverview.text = article.summary ?: notApplicableText

            root.setOnClickListener {
                interaction.onClick(position, article)
            }
        }
    }
}