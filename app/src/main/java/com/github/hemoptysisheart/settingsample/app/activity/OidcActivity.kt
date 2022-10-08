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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.R
import com.github.hemoptysisheart.settingsample.R.string.domain_oidc_setting_status_label
import com.github.hemoptysisheart.settingsample.app.ui.resource.OidcStatusResource
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingViewModel
import com.github.hemoptysisheart.settingsample.domain.DummySettings
import com.github.hemoptysisheart.settingsample.domain.OidcStatus.*
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

    val status by viewModel.status.collectAsState()
    val refreshToken by viewModel.refreshToken.collectAsState()
    val accessToken by viewModel.accessToken.collectAsState()
    val idToken by viewModel.idToken.collectAsState()

    val headerWeight = 0.35F
    val valueWEight = 0.65F

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5F))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(domain_oidc_setting_status_label),
                modifier = Modifier.weight(headerWeight),
                fontSize = 20.sp
            )
            Text(
                text = stringResource(OidcStatusResource[status].label),
                modifier = Modifier.weight(valueWEight),
                color = Color.LightGray,
                fontSize = 20.sp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.domain_oidc_setting_refresh_token_label),
                modifier = Modifier.weight(headerWeight),
                fontSize = 20.sp
            )
            Text(
                text = refreshToken ?: stringResource(R.string.label_not_set),
                modifier = Modifier.weight(valueWEight),
                color = Color.LightGray,
                fontSize = 20.sp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.domain_oidc_setting_access_token_label),
                modifier = Modifier.weight(headerWeight),
                fontSize = 20.sp
            )
            Text(
                text = accessToken ?: stringResource(R.string.label_not_set),
                modifier = Modifier.weight(valueWEight),
                fontSize = 20.sp,
                color = Color.LightGray
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.domain_oidc_setting_id_token_label),
                modifier = Modifier.weight(headerWeight),
                fontSize = 20.sp
            )
            Text(
                text = idToken ?: stringResource(R.string.label_not_set),
                modifier = Modifier.weight(valueWEight),
                fontSize = 20.sp,
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        val tokenBtLabel: String
        val tokenBtOnClick: () -> Unit
        val tokenBtEnabled: Boolean
        when (status) {
            ANONYMOUS -> {
                tokenBtLabel = stringResource(R.string.action_oidc_authorize)
                tokenBtOnClick = { viewModel.authorize() }
                tokenBtEnabled = true
            }
            AUTHORIZING -> {
                tokenBtLabel = stringResource(R.string.domain_oidc_setting_status_authorizing_label)
                tokenBtOnClick = {}
                tokenBtEnabled = false
            }
            AUTHORIZED -> {
                tokenBtLabel = stringResource(R.string.action_oidc_refresh)
                tokenBtOnClick = { viewModel.refresh() }
                tokenBtEnabled = true
            }
            REFRESHING -> {
                tokenBtLabel = stringResource(R.string.domain_oidc_setting_status_refreshing_label)
                tokenBtOnClick = {}
                tokenBtEnabled = false
            }
            REFRESHED -> {
                tokenBtLabel = stringResource(R.string.action_oidc_manual_refresh)
                tokenBtOnClick = { viewModel.refresh() }
                tokenBtEnabled = true
            }
        }
        Button(
            onClick = tokenBtOnClick,
            enabled = tokenBtEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = tokenBtLabel,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { viewModel.clear() }, enabled = status.authorized, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(R.string.action_oidc_clear),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.weight(1.0F))
    }
}

@Preview(showBackground = true)
@Composable
fun OidcLayoutPreview() {
    SettingSampleTheme {
        OidcLayout(OidcSettingViewModel(DummySettings))
    }
}