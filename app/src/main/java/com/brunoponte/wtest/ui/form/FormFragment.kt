package com.brunoponte.wtest.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.brunoponte.wtest.R
import com.brunoponte.wtest.databinding.FormFragmentBinding
import com.brunoponte.wtest.ui.form.qualityList.QualityListFragment
import com.brunoponte.wtest.ui.postalCodeList.PostalCodesFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FormFragment : Fragment() {

    private lateinit var binding: FormFragmentBinding
    private val viewModel: FormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()

        binding.editQuality.setOnClickListener {
            activity?.let {
                QualityListFragment { quality ->
                    binding.editQuality.setText(quality)
                }.show(it.supportFragmentManager, QualityListFragment::class.simpleName)
            }
        }

        binding.editPostalCode.setOnClickListener {
            activity?.let {
                PostalCodesFragment { postalCode ->
                    val newText = "${postalCode.code}, ${postalCode.designation}"
                    binding.editPostalCode.setText(newText)
                }.show(it.supportFragmentManager, PostalCodesFragment::class.simpleName)
            }
        }

        binding.buttonSubmit.setOnClickListener {

        }
    }

    private fun setupViewModelObservers() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
        }
    }
}