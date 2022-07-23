package com.brunoponte.wtest.ui.postalCodeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.databinding.PostalCodesFragmentBinding
import com.brunoponte.wtest.domainModels.PostalCode
import com.brunoponte.wtest.ui.CustomBottomSheetDialogFragment
import com.brunoponte.wtest.ui.postalCodeList.adapter.PostalCodeListAdapter
import com.brunoponte.wtest.ui.postalCodeList.adapter.PostalCodeListInteraction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.postal_codes_fragment.*

@AndroidEntryPoint
class PostalCodesFragment(
    private val postalCodeSelectAction: ((PostalCode) -> Unit)? = null
) : CustomBottomSheetDialogFragment(), PostalCodeListInteraction {

    private lateinit var binding: PostalCodesFragmentBinding

    private val viewModel: PostalCodesViewModel by viewModels()
    private val listAdapter = PostalCodeListAdapter(this).apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
            .PREVENT_WHEN_EMPTY
    }

    override var fullscreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFirstPostalCodes()
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

    override fun onClick(postalCode: PostalCode) {
        postalCodeSelectAction?.let {
            it.invoke(postalCode)
            dismiss()
        }
    }

    override fun onIndexReached(index: Int) {
        // Reached a new element in Recycler View, update scroll position in VM
        viewModel.onChangePostalCodeScrollPosition(index)
    }

    private fun setupViewModelObservers() {
        viewModel.postalCodes.observe(viewLifecycleOwner) { postalCodes ->
            listAdapter.submitList(postalCodes?.map { it.copy() })
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progress.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}