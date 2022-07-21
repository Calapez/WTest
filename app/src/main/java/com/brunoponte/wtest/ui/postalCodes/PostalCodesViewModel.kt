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

    val postalCodes: MutableLiveData<List<PostalCode>?> = MutableLiveData(null)

    init {
        searchPostalCodes("")
    }

    fun searchPostalCodes(query: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = postalCodeRepo.searchPostalCodes(query ?: "")
            postalCodes.postValue(result)
        }
    }
}