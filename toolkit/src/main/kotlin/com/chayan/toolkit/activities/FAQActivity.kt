package com.chayan.toolkit.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.chayan.toolkit.compose.extensions.enableEdgeToEdgeSimple
import com.chayan.toolkit.compose.screens.FAQScreen
import com.chayan.toolkit.compose.theme.AppThemeSurface
import com.chayan.toolkit.helpers.APP_FAQ
import com.chayan.toolkit.models.FAQItem
import kotlinx.collections.immutable.toImmutableList

class FAQActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val faqItems = remember { intent.getSerializableExtra(APP_FAQ) as ArrayList<FAQItem> }
                FAQScreen(
                    goBack = ::finish,
                    faqItems = faqItems.toImmutableList()
                )
            }
        }
    }
}
