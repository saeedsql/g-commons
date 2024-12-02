package com.devgroup.commons.samples.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devgroup.commons.activities.BaseSimpleActivity
import com.devgroup.commons.activities.CustomizationActivity
import com.devgroup.commons.activities.ManageBlockedNumbersActivity
import com.devgroup.commons.compose.alert_dialog.AlertDialogState
import com.devgroup.commons.compose.alert_dialog.rememberAlertDialogState
import com.devgroup.commons.compose.extensions.*
import com.devgroup.commons.compose.theme.AppThemeSurface
import com.devgroup.commons.dialogs.ConfirmationDialog
import com.devgroup.commons.dialogs.RateStarsAlertDialog
import com.devgroup.commons.dialogs.SecurityDialog
import com.devgroup.commons.extensions.*
import com.devgroup.commons.helpers.*
import com.devgroup.commons.models.FAQItem
import com.devgroup.commons.samples.BuildConfig
import com.devgroup.commons.samples.R
import com.devgroup.commons.samples.screens.MainScreen

class MainActivity : BaseSimpleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        isMaterialActivity = true
        super.onCreate(savedInstanceState)
        appLaunched(BuildConfig.APPLICATION_ID)
        enableEdgeToEdgeSimple()
        setContent {
            val isTopAppBarColorIcon by config.isTopAppBarColorIcon.collectAsStateWithLifecycle(initialValue = config.topAppBarColorIcon)
            AppThemeSurface {
                val showMoreApps = onEventValue { !resources.getBoolean(com.devgroup.commons.R.bool.hide_google_relations) }

                MainScreen(
                    openColorCustomization = ::startCustomizationActivity,
                    manageBlockedNumbers = {
                        startActivity(Intent(this@MainActivity, ManageBlockedNumbersActivity::class.java))
                    },
                    showComposeDialogs = {
                        startActivity(Intent(this@MainActivity, TestDialogActivity::class.java))
                    },
                    openTestButton = ::securityDialog,
                    showMoreApps = showMoreApps,
                    openAbout = ::launchAbout,
                    moreAppsFromUs = ::launchMoreAppsFromUsIntent,
                    startPurchaseActivity = ::launchPurchase,
                    isTopAppBarColorIcon = isTopAppBarColorIcon,
                )
                AppLaunched()
            }
        }
    }

    @Composable
    private fun AppLaunched(
        rateStarsAlertDialogState: AlertDialogState = getRateStarsAlertDialogState(),
    ) {
        LaunchedEffect(Unit) {
            appLaunchedCompose(
                appId = BuildConfig.APPLICATION_ID,
                showRateUsDialog = rateStarsAlertDialogState::show,
            )
        }
    }

    @Composable
    private fun getRateStarsAlertDialogState() = rememberAlertDialogState().apply {
        DialogMember {
            RateStarsAlertDialog(alertDialogState = this, onRating = ::rateStarsRedirectAndThankYou)
        }
    }

    private fun startCustomizationActivity() {
        startCustomizationActivity(
            showAccentColor = true,
            isCollection = false,
            productIdList = arrayListOf("", "", ""),
            productIdListRu = arrayListOf("", "", ""),
            subscriptionIdList = arrayListOf("", "", ""),
            subscriptionIdListRu = arrayListOf("", "", ""),
            subscriptionYearIdList = arrayListOf("", "", ""),
            subscriptionYearIdListRu = arrayListOf("", "", ""),
            playStoreInstalled = isPlayStoreInstalled(),
            ruStoreInstalled = isRuStoreInstalled(),
            showAppIconColor = true
        )
    }

    private fun launchPurchase() {
        startPurchaseActivity(
            R.string.app_name_g,
            productIdList = arrayListOf("", "", ""),
            productIdListRu = arrayListOf("", "", ""),
            subscriptionIdList = arrayListOf("", "", ""),
            subscriptionIdListRu = arrayListOf("", "", ""),
            subscriptionYearIdList = arrayListOf("", "", ""),
            subscriptionYearIdListRu = arrayListOf("", "", ""),
            showLifebuoy = false,
            playStoreInstalled = isPlayStoreInstalled(),
            ruStoreInstalled = isRuStoreInstalled(),
            showCollection = true
        )
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(com.devgroup.commons.R.string.faq_1_title_commons, com.devgroup.commons.R.string.faq_1_text_commons),
            FAQItem(com.devgroup.commons.R.string.faq_1_title_commons, com.devgroup.commons.R.string.faq_1_text_commons),
            FAQItem(com.devgroup.commons.R.string.faq_4_title_commons, com.devgroup.commons.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(com.devgroup.commons.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(com.devgroup.commons.R.string.faq_2_title_commons, com.devgroup.commons.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(com.devgroup.commons.R.string.faq_6_title_commons, com.devgroup.commons.R.string.faq_6_text_commons))
        }

        startAboutActivity(
            R.string.app_name_g,
            licenses,
            BuildConfig.VERSION_NAME,
            faqItems,
            true,
            arrayListOf("", "", ""), arrayListOf("", "", ""),
            arrayListOf("", "", ""), arrayListOf("", "", ""),
            arrayListOf("", "", ""), arrayListOf("", "", ""),
            playStoreInstalled = isPlayStoreInstalled(),
            ruStoreInstalled = isRuStoreInstalled())
    }

    private fun securityDialog() {
        val tabToShow = if (config.isAppPasswordProtectionOn) config.appProtectionType else SHOW_ALL_TABS
        SecurityDialog(this@MainActivity, config.appPasswordHash, tabToShow) { hash, type, success ->
            if (success) {
                val hasPasswordProtection = config.isAppPasswordProtectionOn
                config.isAppPasswordProtectionOn = !hasPasswordProtection
                config.appPasswordHash = if (hasPasswordProtection) "" else hash
                config.appProtectionType = type
            }
        }
    }

    override fun getAppLauncherName() = getString(R.string.commons_app_name)

    override fun getAppIconIDs() = arrayListOf(
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher_one,
        R.mipmap.ic_launcher_two,
        R.mipmap.ic_launcher_three,
        R.mipmap.ic_launcher_four,
        R.mipmap.ic_launcher_five,
        R.mipmap.ic_launcher_six,
        R.mipmap.ic_launcher_seven,
        R.mipmap.ic_launcher_eight,
        R.mipmap.ic_launcher_nine,
        R.mipmap.ic_launcher_ten,
        R.mipmap.ic_launcher_eleven
    )
}
