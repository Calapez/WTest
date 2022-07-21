package com.brunoponte.wtest.ui.postalCodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunoponte.wtest.domainModels.PostalCode
import com.brunoponte.wtest.repository.IPostalCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostalCodesViewModel
@Inject
constructor(
    private val postalCodeRepo: IPostalCodeRepository
) : ViewModel() {

    private var query = ""
    private var scrollPosition = 0
    private var page = 1

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val postalCodes: MutableLiveData<List<PostalCode>> = MutableLiveData(listOf())

    fun getFirstPostalCodes() {
        // Fetches the first page of the repos

        if (postalCodes.value?.isNotEmpty() == true) {
            // Already got repos
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val result = postalCodeRepo.searchPostalCodes(PAGE_SIZE, 1, query)
            page += 1
            isLoading.postValue(false)

            postalCodes.postValue(result)
        }
    }

    fun onChangePostalCodeScrollPosition(position: Int) {
        scrollPosition = position

        if (reachedEndOfList() && !isLoading.value!!) {
            // Reached end of current page and isn't loading repos. Must load next page.
            getNextPage()
        }
    }

    private fun getNextPage() {
        CoroutineScope(Dispatchers.IO).launch {
            if (reachedEndOfList()) {
                isLoading.postValue(true)

                // Prevents this to be called on first page load
                if (page > 1) {
                    val result = postalCodeRepo.searchPostalCodes(PAGE_SIZE, page, query)

                    // Append repos
                    val current = ArrayList(postalCodes.value)
                    current.addAll(result)
                    postalCodes.postValue(current)

                    page += 1
                }
                isLoading.postValue(false)
            }
        }
    }

    fun searchPostalCodes(newQuery: String?) {
        query = newQuery ?: ""
        page = 1

        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val result = postalCodeRepo.searchPostalCodes(PAGE_SIZE, page, query)
            page += 1
            isLoading.postValue(false)

            postalCodes.postValue(result)
        }
    }

    private fun reachedEndOfList() = scrollPosition >= postalCodes.value!!.size - 1

    companion object {
        const val PAGE_SIZE = 100
    }
}