package com.brunoponte.wtest.ui.form.qualityList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunoponte.wtest.databinding.QualityListFragmentBinding
import com.brunoponte.wtest.ui.CustomBottomSheetDialogFragment
import com.brunoponte.wtest.ui.form.qualityList.listAdapter.QualityListAdapter
import com.brunoponte.wtest.ui.form.qualityList.listAdapter.QualityListInteraction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QualityListFragment(
    private val qualitySelectAction: (String) -> Unit
) : CustomBottomSheetDialogFragment(), QualityListInteraction {

    private lateinit var binding: QualityListFragmentBinding

    private val viewModel: QualityListViewModel by viewModels()

    private val listAdapter = QualityListAdapter(this).apply {
        stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
            .PREVENT_WHEN_EMPTY
    }

    override var fullscreen = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QualityListFragmentBinding.inflate(inflater, container, false)
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

        listAdapter.submitList(viewModel.qualityList)
    }

    override fun onClick(position: Int, quality: String) {
        qualitySelectAction.invoke(quality)
        dismiss()
    }
}