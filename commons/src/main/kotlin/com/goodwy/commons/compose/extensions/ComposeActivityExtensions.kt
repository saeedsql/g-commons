package com.devgroup.commons.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.devgroup.commons.R
import com.devgroup.commons.compose.alert_dialog.rememberAlertDialogState
import com.devgroup.commons.dialogs.ConfirmationAlertDialog
import com.devgroup.commons.extensions.launchViewIntent

@Composable
fun FakeVersionCheck() {

}

@Composable
fun CheckAppOnSdCard() {
    val context = LocalContext.current.getComponentActivity()
    val confirmationDialogAlertDialogState = rememberAlertDialogState().apply {
        DialogMember {
            ConfirmationAlertDialog(
                alertDialogState = this,
                messageId = R.string.app_on_sd_card,
                positive = R.string.ok,
                negative = null
            ) {}
        }
    }
    LaunchedEffect(Unit) {
        context.appOnSdCardCheckCompose(confirmationDialogAlertDialogState::show)
    }
}
