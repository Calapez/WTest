package com.brunoponte.wtest.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        binding.editFreeText.doOnTextChanged { text, _, _, _ ->
            viewModel.freeText = text.toString()
        }

        binding.editEmail.doOnTextChanged { text, _, _, _ ->
            viewModel.email = text.toString()
        }

        binding.editNumbers.doOnTextChanged { text, _, _, _ ->
            viewModel.numbers = text.toString()
        }

        binding.editCaps.doOnTextChanged { text, _, _, _ ->
            viewModel.capsOnly = text.toString()
        }

        binding.editDate.doOnTextChanged { text, _, _, _ ->
            viewModel.date = text.toString()
        }

        binding.editQuality.doOnTextChanged { text, _, _, _ ->
            viewModel.quality = text.toString()
        }

        binding.editQuality.setOnClickListener {
            activity?.let {
                QualityListFragment { quality ->
                    binding.editQuality.setText(quality)
                }.show(it.supportFragmentManager, QualityListFragment::class.simpleName)
            }
        }

        binding.editPostalCode.doOnTextChanged { text, _, _, _ ->
            viewModel.postalCode = text.toString()
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
            viewModel.onSubmit()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.freeTextValid.observe(viewLifecycleOwner) { valid ->
            binding.textFreeText.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.emailValid.observe(viewLifecycleOwner) { valid ->
            binding.textEmail.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.numbersValid.observe(viewLifecycleOwner) { valid ->
            binding.textNumbers.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.capsOnlyValid.observe(viewLifecycleOwner) { valid ->
            binding.textCaps.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.dateValid.observe(viewLifecycleOwner) { valid ->
            binding.textDate.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.qualityValid.observe(viewLifecycleOwner) { valid ->
            binding.textQuality.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.postalCodeValid.observe(viewLifecycleOwner) { valid ->
            binding.textPostalCode.visibility = if (valid) View.GONE else View.VISIBLE
        }

        viewModel.submitted.observe(viewLifecycleOwner) { submitted ->
            binding.buttonSubmit.isEnabled = !submitted
        }
    }
}