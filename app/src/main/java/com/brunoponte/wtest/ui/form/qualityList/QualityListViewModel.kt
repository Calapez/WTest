package com.brunoponte.wtest.ui.form.qualityList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class QualityListViewModel : ViewModel() {

    val qualityList = listOf("Mau", "Satisfatório", "Bom", "Muito Bom", "Excelente")
}