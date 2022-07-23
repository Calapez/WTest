package com.brunoponte.wtest.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoponte.wtest.repository.postalCode.IPostalCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject
constructor(
    private val postalCodeRepo: IPostalCodeRepository
) : ViewModel() {
    var loadedPostalCodes = false
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            postalCodeRepo.fetchPostalCodes()
            loadedPostalCodes = true
            loading.postValue(false)
        }
    }
}