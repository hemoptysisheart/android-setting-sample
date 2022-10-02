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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
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
    val setting by remember { mutableStateOf(viewModel) }
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
            setting.refreshToken?.also {
                Text(text = it, modifier = Modifier.weight(0.65F))
            } ?: Text(
                text = stringResource(string.label_null),
                modifier = Modifier.weight(0.65F),
                color = LightGray
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(string.label_oidc_access_token), modifier = Modifier.weight(0.35F))
            setting.accessToken?.also {
                Text(text = it, modifier = Modifier.weight(0.65F))
            } ?: Text(text = stringResource(string.label_null), modifier = Modifier.weight(0.65F), color = LightGray)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(string.label_oidc_id_token), modifier = Modifier.weight(0.35F))
            setting.idToken?.also {
                Text(text = it, modifier = Modifier.weight(0.65F))
            } ?: Text(text = stringResource(string.label_null), modifier = Modifier.weight(0.65F), color = LightGray)
        }
        if (setting.status) {
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