package com.chayan.toolkit.compose.extensions

import android.app.Activity
import android.content.Context
import com.chayan.toolkit.R
import com.chayan.toolkit.extensions.toast
import com.chayan.toolkit.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)
