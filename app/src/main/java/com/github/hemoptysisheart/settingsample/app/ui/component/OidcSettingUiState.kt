package com.github.hemoptysisheart.settingsample.app.ui.component

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hemoptysisheart.settingsample.app.viewmodel.OidcSettingViewModel
import com.github.hemoptysisheart.settingsample.domain.OidcStatus

class OidcSettingUiState(
    val status: OidcStatus,
    val refreshToken: String?
)

@Composable
fun rememberOidcSettingUiState(viewModel: OidcSettingViewModel = viewModel()): OidcSettingUiState {
    return OidcSettingUiState(viewModel.status, viewModel.refreshToken)
}