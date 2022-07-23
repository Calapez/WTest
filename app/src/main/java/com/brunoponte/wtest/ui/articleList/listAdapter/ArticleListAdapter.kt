package com.brunoponte.wtest.ui.articleList.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.brunoponte.wtest.databinding.ArticleListItemBinding
import com.brunoponte.wtest.domainModels.Article


interface ArticleListInteraction {
    fun onClick(position: Int, article: Article)

    fun onIndexReached(index: Int)
}

class ArticleListAdapter(
    private val interaction: ArticleListInteraction
) : ListAdapter<Article, ArticleViewHolder>(ArticleListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemBinding = ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(itemBinding, interaction)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position), position)
        interaction.onIndexReached(position)
    }

    private companion object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}