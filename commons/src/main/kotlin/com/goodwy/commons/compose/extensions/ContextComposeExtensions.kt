package com.devgroup.commons.compose.extensions

import android.app.Activity
import android.content.Context
import com.devgroup.commons.R
import com.devgroup.commons.extensions.baseConfig
import com.devgroup.commons.extensions.redirectToRateUs
import com.devgroup.commons.extensions.toast
import com.devgroup.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Activity.rateStarsRedirectAndThankYou(stars: Int) {
    if (stars == 5) {
        redirectToRateUs()
    }
    toast(R.string.thank_you)
    baseConfig.wasAppRated = true
}
