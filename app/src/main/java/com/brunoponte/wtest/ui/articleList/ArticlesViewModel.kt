package com.brunoponte.wtest.ui.articleList

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
class ArticlesViewModel
@Inject
constructor(
    private val articleRepo: IArticleRepository,
) : ViewModel() {

    private var scrollPosition = 0
    private var page = 1

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val articles: MutableLiveData<List<Article>> = MutableLiveData(listOf())

    fun getFirstArticles() {
        // Fetches the first page of the repos

        if (articles.value?.isNotEmpty() == true) {
            // Already got repos
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val result = articleRepo.getArticles(PAGE_SIZE, 1)
            page += 1
            isLoading.postValue(false)

            articles.postValue(result)
        }
    }

    fun onChangeArticleScrollPosition(position: Int) {
        scrollPosition = position

        if (reachedEndOfList() && !isLoading.value!!) {
            // Reached end of current page and isn't loading articles. Must load next page.
            getNextPage()
        }
    }

    private fun getNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            if (reachedEndOfList()) {
                isLoading.postValue(true)

                // Prevents this to be called on first page load
                if (page > 1) {
                    val result = articleRepo.getArticles(PAGE_SIZE, page)

                    // Append articles
                    val current = ArrayList(articles.value)
                    current.addAll(result)
                    articles.postValue(current)

                    page += 1
                }
                isLoading.postValue(false)
            }
        }
    }

    private fun reachedEndOfList() = scrollPosition >= articles.value!!.size - 1

    companion object {
        const val PAGE_SIZE = 10
    }
}