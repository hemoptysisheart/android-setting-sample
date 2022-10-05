package com.github.hemoptysisheart.settingsample.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OidcSettingViewModel @Inject constructor(
    settings: Settings
) : ViewModel() {
    companion object {
        val TAG = OidcSettingViewModel::class.simpleName
    }

    private val oidc: OidcSetting = settings.oidc

    var status = oidc.status
        get() = oidc.status
        private set
    var refreshToken = oidc.refreshToken
        get() = oidc.refreshToken
        private set
    val accessToken = oidc.accessToken
    val idToken = oidc.idToken

    fun authorize() {
        viewModelScope.launch {
            Log.v(TAG, "#authorize start : oidc=$oidc")
            oidc.authorize()
            Log.v(TAG, "#authorize end : oidc=$oidc")
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
