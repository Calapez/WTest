package com.brunoponte.wtest.ui.articleDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoponte.wtest.domainModels.Article
import com.brunoponte.wtest.repository.article.IArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel
@Inject
constructor(
    private val articleRepo: IArticleRepository
) : ViewModel() {

    val selectedArticle: MutableLiveData<Article?> = MutableLiveData(null)

    fun getArticleFromId(articleId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val article = articleRepo.getArticleById(articleId)
            selectedArticle.postValue(article)
        }
    }
}