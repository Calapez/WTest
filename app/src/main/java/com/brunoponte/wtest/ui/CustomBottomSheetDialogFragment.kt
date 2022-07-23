package com.brunoponte.wtest.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.brunoponte.wtest.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class CustomBottomSheetDialogFragment : BottomSheetDialogFragment()
{
    private var _bottomSheet: View? = null
    private var _behavior: BottomSheetBehavior<View>? = null
    private var _dialog: Dialog? = null

    abstract var fullscreen: Boolean

    override fun onStart() {
        super.onStart()

        _dialog = dialog?.let { dialog ->
            _bottomSheet = dialog.findViewById<View?>(com.google.android.material.R.id.design_bottom_sheet).also { bottomSheet ->
                bottomSheet.layoutParams.height = setupBottomSheetHeight()
                _behavior = BottomSheetBehavior.from(bottomSheet).also { behavior ->
                    behavior.skipCollapsed = true
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    private fun setupBottomSheetHeight() : Int {
        //Here we define view to be Fullscreen or not depending on the property
        return if (fullscreen) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
    }
}