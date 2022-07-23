package com.brunoponte.wtest.ui.articleDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.brunoponte.wtest.R
import com.brunoponte.wtest.databinding.ArticleDetailsFragmentBinding
import com.brunoponte.wtest.domainModels.Article
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private lateinit var binding: ArticleDetailsFragmentBinding

    private val viewModel: ArticleDetailsViewModel by viewModels()
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ArticleDetailsFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleId = args.articleId
        viewModel.getArticleFromId(articleId)

        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            /*
            val height = resources.getDimensionPixelSize(R.dimen.avatar_height)

            val scrollY = Math.min(Math.max(binding.scrollView.scrollY, 0), height)
            binding.imageHero.translationY = scrollY / 2f

            val alpha = scrollY / height.toFloat()
            binding.imageHero.alpha = 1 - alpha
             */


            /*
            val top: Int = binding.scrollView.scrollY // Increases when scrolling up ^

            if (top != 0) {
                val newTop = (top * .5f).toInt()
                binding.imageHero.top = if (newTop < 0) 0 else newTop
            }
             */
        }

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.selectedArticle.observe(viewLifecycleOwner) { repo ->
            setupView(repo)
        }
    }

    private fun setupView(article: Article?) {
        article?.let {
            val notApplicableText = getString(R.string.not_applicable)

            Glide.with(this)
                .load(article.avatarUrl)
                .error(R.drawable.avatar_default)
                .into(binding.imageHero)

            binding.textTitle.text = article.title ?: notApplicableText

            binding.textAuthor.text = article.author ?: notApplicableText

            binding.textPublishDate.text = article.publishDate ?: notApplicableText

            binding.textBody.text = article.body ?: notApplicableText
        }

    }
}