package com.github.hemoptysisheart.settingsample.app.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.R
import com.github.hemoptysisheart.settingsample.R.string.domain_oidc_setting_status_label
import com.github.hemoptysisheart.settingsample.app.ui.component.rememberOidcSettingUiState
import com.github.hemoptysisheart.settingsample.app.ui.resource.OidcStatusResource
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingViewModel
import com.github.hemoptysisheart.settingsample.domain.DummySettings
import com.github.hemoptysisheart.settingsample.domain.OidcStatus.ANONYMOUS
import com.github.hemoptysisheart.settingsample.domain.OidcStatus.AUTHORIZING
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OidcActivity : ComponentActivity() {
    companion object {
        val TAG = OidcActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    OidcLayout()
                }
            }
        }
    }
}

private const val TAG = "OidcLayout"

@Composable
fun OidcLayout(viewModel: OidcSettingViewModel = viewModel()) {
    Log.v(TAG, "#OidcLayout args : viewModel=$viewModel")

    val state = rememberOidcSettingUiState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(domain_oidc_setting_status_label), modifier = Modifier.weight(0.35F))
            Text(
                text = stringResource(OidcStatusResource[state.status].label),
                modifier = Modifier.weight(0.65F),
                color = Color.LightGray
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.domain_oidc_setting_refresh_token_label),
                modifier = Modifier.weight(0.35F)
            )
            Text(
                text = state.refreshToken ?: stringResource(id = R.string.label_not_set),
                modifier = Modifier.weight(0.65F),
                color = Color.LightGray
            )
        }

        when (state.status) {
            ANONYMOUS, AUTHORIZING -> {
                Button(onClick = { viewModel.authorize() }, enabled = ANONYMOUS == state.status) {
                    Text(text = stringResource(id = R.string.action_oidc_authorize))
                }
            }
            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OidcLayoutPreview() {
    SettingSampleTheme {
        OidcLayout(OidcSettingViewModel(DummySettings))
    }
}