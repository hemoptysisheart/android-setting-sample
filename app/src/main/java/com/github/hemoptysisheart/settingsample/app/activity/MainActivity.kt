package com.github.hemoptysisheart.settingsample.app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.hemoptysisheart.settingsample.app.ui.component.OidcSettingMenuGroup
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainActivityLayout()
                }
            }
        }
    }
}

@Composable
fun MainActivityLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        OidcSettingMenuGroup()
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityLayoutPreview() {
    SettingSampleTheme {
        MainActivityLayout()
    }
}