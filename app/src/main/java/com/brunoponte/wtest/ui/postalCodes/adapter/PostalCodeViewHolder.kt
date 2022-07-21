package com.brunoponte.wtest.ui.postalCodes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.databinding.PostalCodeListItemBinding
import com.brunoponte.wtest.domainModels.PostalCode

class PostalCodeViewHolder(
    private val binding: PostalCodeListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(postalCode: PostalCode) {
        binding.textCode.text = postalCode.code
        binding.textName.text = postalCode.designation
    }
}