package com.chayan.toolkit.interfaces

interface CopyMoveListener {
    fun copySucceeded(copyOnly: Boolean, copiedAll: Boolean, destinationPath: String, wasCopyingOneFileOnly: Boolean)

    fun copyFailed()
}