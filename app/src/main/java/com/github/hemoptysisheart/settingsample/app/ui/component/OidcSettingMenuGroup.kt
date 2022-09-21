package com.github.hemoptysisheart.settingsample.app.ui.component

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.R.string.*
import com.github.hemoptysisheart.settingsample.app.activity.OidcActivity
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingMenuGroupViewModel
import com.github.hemoptysisheart.settingsample.domain.DummySettings

@Composable
fun OidcSettingMenuGroup(viewModel: OidcSettingMenuGroupViewModel = viewModel()) {
    val context = LocalContext.current
    val status by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth()) {
        Text(stringResource(setting_group_auth_label))
        SettingItemHasDetail(
            stringResource(setting_item_oidc_label),
            if (status) {
                stringResource(setting_item_oidc_status_authorized)
            } else {
                stringResource(setting_item_oidc_status_anonymous)
            }
        ) {
            context.startActivity(Intent(context, OidcActivity::class.java))
        }
    }
}

@Composable
@Preview
fun OidcSettingMenuGroupLayout() {
    val viewModel = OidcSettingMenuGroupViewModel()
    viewModel.settings = DummySettings
    OidcSettingMenuGroup(viewModel)
}