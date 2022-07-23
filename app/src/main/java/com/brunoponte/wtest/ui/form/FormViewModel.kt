package com.brunoponte.wtest.ui.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoponte.wtest.domainModels.Article
import com.brunoponte.wtest.repository.article.IArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FormViewModel: ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val articles: MutableLiveData<List<Article>> = MutableLiveData(listOf())

}