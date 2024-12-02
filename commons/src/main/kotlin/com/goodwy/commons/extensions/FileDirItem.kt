package com.devgroup.commons.extensions

import android.content.Context
import com.devgroup.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
