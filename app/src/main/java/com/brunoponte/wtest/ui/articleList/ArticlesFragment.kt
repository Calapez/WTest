package com.brunoponte.wtest.ui.articleList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.databinding.ArticlesFragmentBinding
import com.brunoponte.wtest.domainModels.Article
import com.brunoponte.wtest.ui.articleList.listAdapter.ArticleListAdapter
import com.brunoponte.wtest.ui.articleList.listAdapter.ArticleListInteraction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : Fragment(), ArticleListInteraction {

    private lateinit var binding: ArticlesFragmentBinding
    private val viewModel: ArticlesViewModel by viewModels()

    private val listAdapter = ArticleListAdapter(this).apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
            .PREVENT_WHEN_EMPTY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFirstArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArticlesFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }

            recyclerView.adapter = listAdapter
        }

        setupViewModelObservers()
    }

    override fun onClick(position: Int, article: Article) {
        // Navigate to Details Fragment
        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsFragment(article.id)
        findNavController().navigate(action)
    }

    override fun onIndexReached(index: Int) {
        // Reached a new element in Recycler View, update scroll position in VM
        viewModel.onChangeArticleScrollPosition(index)
    }

    private fun setupViewModelObservers() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            listAdapter.submitList(articles.map { it.copy() })
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progress.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}