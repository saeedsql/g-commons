package com.devgroup.commons.helpers.rustore.model

import androidx.annotation.StringRes

data class InfoDialogState(
    @StringRes val titleRes: Int,
    val message: String
)
