package com.goodwy.commons.fragments

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.goodwy.commons.R
import com.goodwy.commons.extensions.*
import kotlinx.android.synthetic.main.dialog_bottom_sheet.view.*

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false)
        val context = requireContext()

        view.background = null
        if (requireContext().isBlackAndWhiteTheme()) {
            view.bottom_sheet_holder.background = ResourcesCompat.getDrawable(context.resources, R.drawable.bottom_sheet_bg_black, context.theme)
        } else /*if (!config.isUsingSystemTheme)*/ {
            view.bottom_sheet_holder.background = ResourcesCompat.getDrawable(context.resources, R.drawable.bottom_sheet_bg, context.theme).apply {
                val backgroundColor = if (requireContext().isBlackTheme()) context.getBottomNavigationBackgroundColor() else context.getProperBackgroundColor()
                (this as LayerDrawable).findDrawableByLayerId(R.id.bottom_sheet_background).applyColorFilter(backgroundColor)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getInt(BOTTOM_SHEET_TITLE).takeIf { it != 0 }
        view.apply {
            bottom_sheet_title.setTextColor(context.getProperTextColor())
            bottom_sheet_title.setTextOrBeGone(title)
            setupContentView(bottom_sheet_content_holder)

            bottom_sheet_cancel.applyColorFilter(context.getProperTextColor())
            bottom_sheet_cancel.setOnClickListener { dialog?.dismiss() }
        }
    }

    abstract fun setupContentView(parent: ViewGroup)

    companion object {
        const val BOTTOM_SHEET_TITLE = "title_string"
    }
}
