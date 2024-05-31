package com.chayan.toolkit.samples.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.chayan.toolkit.activities.AboutActivity
import com.chayan.toolkit.activities.CustomizationActivity
import com.chayan.toolkit.activities.ManageBlockedNumbersActivity
import com.chayan.toolkit.compose.extensions.appLaunchedCompose
import com.chayan.toolkit.compose.extensions.enableEdgeToEdgeSimple
import com.chayan.toolkit.compose.extensions.onEventValue
import com.chayan.toolkit.compose.theme.AppThemeSurface
import com.chayan.toolkit.dialogs.ConfirmationDialog
import com.chayan.toolkit.extensions.hideKeyboard
import com.chayan.toolkit.extensions.launchMoreAppsFromUsIntent
import com.chayan.toolkit.helpers.*
import com.chayan.toolkit.models.FAQItem
import com.chayan.toolkit.samples.BuildConfig
import com.chayan.toolkit.samples.R
import com.chayan.toolkit.samples.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val showMoreApps = onEventValue { !resources.getBoolean(com.chayan.toolkit.R.bool.hide_google_relations) }

                MainScreen(
                    openColorCustomization = ::startCustomizationActivity,
                    manageBlockedNumbers = {
                        startActivity(Intent(this@MainActivity, ManageBlockedNumbersActivity::class.java))
                    },
                    showComposeDialogs = {
                        startActivity(Intent(this@MainActivity, TestDialogActivity::class.java))
                    },
                    openTestButton = {
                        ConfirmationDialog(
                            this@MainActivity,
                            "Thank you for using this toolkit! We hope you’re enjoying the experience and finding it valuable.",
                            positive = com.chayan.toolkit.R.string.ok,
                            negative = 0
                        ) {
                        }
                    },
                    showMoreApps = showMoreApps,
                    openAbout = ::launchAbout,
                    moreAppsFromUs = ::launchMoreAppsFromUsIntent
                )
                AppLaunched()
            }
        }
    }

    @Composable
    private fun AppLaunched() {
        LaunchedEffect(Unit) {
            appLaunchedCompose(appId = BuildConfig.APPLICATION_ID)
        }
    }

    private fun startCustomizationActivity() {
        Intent(applicationContext, CustomizationActivity::class.java).apply {
            putExtra(APP_ICON_IDS, getAppIconIDs())
            putExtra(APP_LAUNCHER_NAME, getAppLauncherName())
            startActivity(this)
        }
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(com.chayan.toolkit.R.string.faq_1_title_commons, com.chayan.toolkit.R.string.faq_1_text_commons),
            FAQItem(com.chayan.toolkit.R.string.faq_1_title_commons, com.chayan.toolkit.R.string.faq_1_text_commons),
            FAQItem(com.chayan.toolkit.R.string.faq_4_title_commons, com.chayan.toolkit.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(com.chayan.toolkit.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(com.chayan.toolkit.R.string.faq_2_title_commons, com.chayan.toolkit.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(com.chayan.toolkit.R.string.faq_6_title_commons, com.chayan.toolkit.R.string.faq_6_text_commons))
        }

        startAboutActivity(R.string.app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }

    private fun startAboutActivity(
        appNameId: Int, licenseMask: Long, versionName: String, faqItems: ArrayList<FAQItem>, showFAQBeforeMail: Boolean,
        getAppIconIDs: ArrayList<Int> = getAppIconIDs(),
        getAppLauncherName: String = getAppLauncherName()
    ) {
        hideKeyboard()
        Intent(applicationContext, AboutActivity::class.java).apply {
            putExtra(APP_ICON_IDS, getAppIconIDs)
            putExtra(APP_LAUNCHER_NAME, getAppLauncherName)
            putExtra(APP_NAME, getString(appNameId))
            putExtra(APP_LICENSES, licenseMask)
            putExtra(APP_VERSION_NAME, versionName)
            putExtra(APP_FAQ, faqItems)
            putExtra(SHOW_FAQ_BEFORE_MAIL, showFAQBeforeMail)
            startActivity(this)
        }
    }

    private fun getAppLauncherName() = getString(R.string.app_name)

    private fun getAppIconIDs(): ArrayList<Int> {
        val ids = ArrayList<Int>()
        ids.add(R.mipmap.ic_launcher)
        return ids
    }
}
