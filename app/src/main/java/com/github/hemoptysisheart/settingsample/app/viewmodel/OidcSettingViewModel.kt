package com.github.hemoptysisheart.settingsample.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OidcSettingViewModel @Inject constructor(
    private val settings: Settings
) : ViewModel() {
    companion object {
        val TAG = OidcSettingViewModel::class.simpleName
    }

    val oidc: OidcSetting
        get() = settings.oidc

    val status: Boolean
        get() = null != oidc.refreshToken

    val refreshToken = oidc.refreshToken
    val accessToken = oidc.accessToken
    val idToken = oidc.idToken

    fun authorize() {
        viewModelScope.launch {
            oidc.authorize()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            oidc.refresh()
        }
    }

    override fun toString() =
        "$TAG(status=$status, refreshToken=$refreshToken, accessToken=$accessToken, idToken=$idToken)"
}
