package com.goodwy.commons.dialogs

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.goodwy.commons.R
import com.goodwy.commons.databinding.DialogIconListBinding
import com.goodwy.commons.extensions.*

@SuppressLint("UseCompatLoadingForDrawables")
class IconListDialog(
    val activity: Activity,
    val items: ArrayList<Int>,
    val checkedItemId: Int = -1,
    val defaultItemId: Int? = null,
    val titleId: Int = 0,
    val descriptionId: Int? = null,
    val size: Int? = null,
    val callback: (wasPositivePressed: Boolean, newValue: Int) -> Unit
) {

    private var dialog: AlertDialog? = null
    private var wasInit = false

    init {
        val view = DialogIconListBinding.inflate(activity.layoutInflater, null, false).apply {
            when (items.size) {
                3 -> {
                    arrayOf(icon4Holder, icon5Holder, icon6Holder, icon7Holder, icon8Holder).forEach {
                        it.beGone()
                    }
                    arrayOf(icon1, icon2, icon3).forEachIndexed { index, imageView ->
                        imageView.setImageDrawable(activity.resources.getDrawable(items[index]))
                        if (size != null) {
                            imageView.setHeightAndWidth(size)
                        }
                    }
                }
                4 -> {
                    arrayOf(icon5Holder, icon6Holder, icon7Holder, icon8Holder).forEach {
                        it.beGone()
                    }
                    arrayOf(icon1, icon2, icon3, icon4).forEachIndexed { index, imageView ->
                        imageView.setImageDrawable(activity.resources.getDrawable(items[index]))
                        if (size != null) {
                            imageView.setHeightAndWidth(size)
                        }
                    }
                }
                8 -> {
                    arrayOf(icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8).forEachIndexed { index, imageView ->
                        imageView.setImageDrawable(activity.resources.getDrawable(items[index]))
                        if (size != null) {
                            imageView.setHeightAndWidth(size)
                        }
                    }
                }
            }

            arrayOf(icon1Check, icon2Check, icon3Check, icon4Check,
                icon5Check, icon6Check, icon7Check, icon8Check
            ).forEach {
                it.applyColorFilter(activity.getProperPrimaryColor())
            }

            when (checkedItemId) {
                1 -> icon1Check.beVisible()
                2 -> icon2Check.beVisible()
                3 -> icon3Check.beVisible()
                4 -> icon4Check.beVisible()
                5 -> icon5Check.beVisible()
                6 -> icon6Check.beVisible()
                7 -> icon7Check.beVisible()
                8 -> icon8Check.beVisible()
            }

            icon1.setOnClickListener { itemSelected(1) }
            icon2.setOnClickListener { itemSelected(2) }
            icon3.setOnClickListener { itemSelected(3) }
            icon4.setOnClickListener { itemSelected(4) }
            icon5.setOnClickListener { itemSelected(5) }
            icon6.setOnClickListener { itemSelected(6) }
            icon7.setOnClickListener { itemSelected(7) }
            icon8.setOnClickListener { itemSelected(8) }

            if (descriptionId != null) {
                description.beVisible()
                description.setText(descriptionId)
            }
        }

        val builder = activity.getAlertDialogBuilder()
            .setPositiveButton(R.string.dismiss, null)

        builder.apply {
            if (defaultItemId != null) setNeutralButton(R.string.set_as_default) { _, _ -> itemSelected(defaultItemId) }
            activity.setupDialogStuff(view.root, this, titleId, cancelOnTouchOutside = true) { alertDialog ->
                dialog = alertDialog
            }
        }

        wasInit = true
    }

    private fun itemSelected(checkedId: Int) {
        if (wasInit) {
            callback(true, checkedId)
            dialog?.dismiss()
        }
    }
}
