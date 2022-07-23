package com.brunoponte.wtest.ui.form

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
import com.brunoponte.wtest.databinding.FormFragmentBinding
import com.brunoponte.wtest.domainModels.Article
import com.brunoponte.wtest.ui.articleList.listAdapter.ArticleListAdapter
import com.brunoponte.wtest.ui.articleList.listAdapter.ArticleListInteraction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment() {

    private lateinit var binding: FormFragmentBinding
    private val viewModel: FormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
        }
    }
}