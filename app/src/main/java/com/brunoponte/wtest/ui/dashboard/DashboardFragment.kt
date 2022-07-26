package com.brunoponte.wtest.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.brunoponte.wtest.R
import com.brunoponte.wtest.databinding.DashboardFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: DashboardFragmentBinding

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonPostalCode.isEnabled = viewModel.loadedPostalCodes
        setupViewModelObservers()

        binding.buttonPostalCode.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_postalCodesFragment)
        }

        binding.buttonArticles.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_articlesFragment)
        }

        binding.buttonForm.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_formFragment)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.textTransferringData.visibility = if (loading) View.VISIBLE else View.INVISIBLE
            binding.buttonPostalCode.isEnabled = viewModel.loadedPostalCodes
        }
    }
}