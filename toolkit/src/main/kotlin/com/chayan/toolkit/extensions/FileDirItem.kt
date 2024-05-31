package com.chayan.toolkit.extensions

import android.content.Context
import com.chayan.toolkit.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
