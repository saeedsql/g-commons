package com.devgroup.commons.adapters

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devgroup.commons.R
import com.devgroup.commons.activities.BaseSimpleActivity
import com.devgroup.commons.interfaces.RenameTab

class RenameAdapter(
    private val activity: BaseSimpleActivity,
    private val paths: ArrayList<String>
) : RecyclerView.Adapter<RenameAdapter.RenameTabViewHolder>() {

    private val tabs = SparseArray<RenameTab>()

    inner class RenameTabViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenameTabViewHolder {
        val layoutId = layoutSelection(viewType)
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return RenameTabViewHolder(view)
    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: RenameTabViewHolder, position: Int) {
        val renameTab = holder.view as RenameTab
        renameTab.initTab(activity, paths)
        tabs.put(position, renameTab)
    }

    private fun layoutSelection(position: Int): Int = when (position) {
        0 -> R.layout.tab_rename_simple
        1 -> R.layout.tab_rename_pattern
        else -> throw RuntimeException("Only 2 tabs allowed")
    }

    fun dialogConfirmed(useMediaFileExtension: Boolean, position: Int, callback: (Boolean) -> Unit) {
        tabs[position]?.dialogConfirmed(useMediaFileExtension, callback)
    }
}
