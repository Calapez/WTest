package com.brunoponte.wtest.ui.postalCodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.databinding.PostalCodesFragmentBinding
import com.brunoponte.wtest.ui.postalCodes.adapter.PostalCodeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.postal_codes_fragment.*

@AndroidEntryPoint
class PostalCodesFragment : Fragment() {

    private lateinit var binding: PostalCodesFragmentBinding

    private val viewModel: PostalCodesViewModel by viewModels()
    private val listAdapter = PostalCodeListAdapter().apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
            .PREVENT_WHEN_EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostalCodesFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }

            recyclerView.adapter = listAdapter
        }

        setupViewModelObservers()

        searchView.doOnTextChanged { text, _, _, _ ->
            viewModel.searchPostalCodes(text.toString())
        }
    }

    private fun setupViewModelObservers() {
        viewModel.postalCodes.observe(viewLifecycleOwner) { postalCodes ->
            val newList = postalCodes?.map { it.copy() }?.toMutableList()
            listAdapter.submitList(newList)
            listAdapter.notifyDataSetChanged()
        }
    }
}