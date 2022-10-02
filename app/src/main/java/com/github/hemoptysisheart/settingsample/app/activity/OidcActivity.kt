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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.R.string
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingViewModel
import com.github.hemoptysisheart.settingsample.domain.DummySettings
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
    val refreshToken by produceState(null) { viewModel.refreshToken }
    val accessToken by produceState(null) { viewModel.accessToken }
    val idToken by produceState(null) { viewModel.idToken }
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
            Text(text = stringResource(string.label_oidc_refresh_token), modifier = Modifier.weight(0.35F))
            Text(text = refreshToken ?: "", modifier = Modifier.weight(0.65F))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(string.label_oidc_access_token), modifier = Modifier.weight(0.35F))
            Text(text = accessToken ?: "", modifier = Modifier.weight(0.65F))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(string.label_oidc_id_token), modifier = Modifier.weight(0.35F))
            Text(text = idToken ?: "", modifier = Modifier.weight(0.65F))
        }
        if (viewModel.status) {
            Button(onClick = {
                viewModel.refresh()
                Log.v(TAG, "#refresh.onCLick : viewModel=$viewModel")
            }) {
                Text(stringResource(string.label_oidc_refresh))
            }
        } else {
            Button(onClick = { viewModel.authorize() }) {
                Text(stringResource(string.label_oidc_auth))
            }
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