package com.github.hemoptysisheart.settingsample.app.ui.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.R.string.setting_group_auth_label
import com.github.hemoptysisheart.settingsample.R.string.setting_item_oidc_label
import com.github.hemoptysisheart.settingsample.app.activity.OidcActivity
import com.github.hemoptysisheart.settingsample.app.ui.resource.OidcStatusResource
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingViewModel
import com.github.hemoptysisheart.settingsample.domain.DummySettings

@Composable
fun OidcSettingMenuGroup(viewModel: OidcSettingViewModel = viewModel()) {
    val context = LocalContext.current
    val state = rememberOidcSettingUiState()

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(setting_group_auth_label),
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(4.dp)
        )
        SettingItemHasDetail(
            stringResource(setting_item_oidc_label),
            stringResource(OidcStatusResource[state.status].label)
        ) {
            context.startActivity(Intent(context, OidcActivity::class.java))
        }
    }
}

@Composable
@Preview
fun OidcSettingMenuGroupLayout() {
    SettingSampleTheme {
        OidcSettingMenuGroup(OidcSettingViewModel(DummySettings))
    }
}