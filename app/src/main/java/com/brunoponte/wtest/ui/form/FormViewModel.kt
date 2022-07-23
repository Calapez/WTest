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

    var freeText = ""
    var email = ""
    var numbers = ""
    var capsOnly = ""
    var date = ""
    var quality = ""
    var postalCode = ""

    val freeTextValid = MutableLiveData(true)
    val emailValid = MutableLiveData(true)
    val numbersValid = MutableLiveData(true)
    val capsOnlyValid = MutableLiveData(true)
    val dateValid = MutableLiveData(true)
    val qualityValid = MutableLiveData(true)
    val postalCodeValid = MutableLiveData(true)

    val submitted = MutableLiveData(false)

    fun onSubmit() {
        freeTextValid.value = freeText.isNotEmpty()

        emailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

        val NUMBERS_REGEX = "[0-9]+"
        numbersValid.value = NUMBERS_REGEX.toRegex().matches(numbers)

        val NO_DIATRICS = "^[.\\p{L}]*$"
        capsOnlyValid.value = capsOnly.length in 3..7 && NO_DIATRICS.toRegex().matches(capsOnly)

        dateValid.value = date.isNotEmpty()

        qualityValid.value = quality.isNotEmpty()

        postalCodeValid.value = postalCode.isNotEmpty()

        submitted.value = freeTextValid.value == true
                && emailValid.value == true
                && numbersValid.value == true
                && capsOnlyValid.value == true
                && dateValid.value == true
                && qualityValid.value == true
                && postalCodeValid.value == true
    }

}