package com.github.hemoptysisheart.settingsample.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.OidcStatus
import com.github.hemoptysisheart.settingsample.domain.OidcStatus.ANONYMOUS
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    val status = MutableStateFlow(ANONYMOUS)

    val refreshToken = MutableStateFlow(oidc.refreshToken)
    val accessToken = MutableStateFlow(oidc.accessToken)
    val idToken = MutableStateFlow(oidc.idToken)

    private suspend fun updateState() {
        refreshToken.emit(oidc.refreshToken)
        accessToken.emit(oidc.accessToken)
        idToken.emit(oidc.idToken)
        status.emit(
            when {
                null == oidc.refreshToken -> ANONYMOUS
                null != oidc.refreshToken && null == oidc.accessToken && null == oidc.idToken -> OidcStatus.AUTHORIZED
                null != oidc.refreshToken && null != oidc.accessToken && null != oidc.idToken -> OidcStatus.REFRESHED
                else -> throw IllegalStateException("oidc=$oidc")
            }
        )
    }

    fun authorize() {
        viewModelScope.launch {
            status.emit(OidcStatus.AUTHORIZING)
            Log.v(TAG, "#authorize start : oidc=$oidc")

            oidc.authorize()
            updateState()

            Log.v(TAG, "#authorize end : oidc=$oidc")
            status.emit(OidcStatus.REFRESHED)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            status.emit(OidcStatus.REFRESHING)
            Log.v(TAG, "#refresh start : oidc=$oidc")

            oidc.refresh()
            updateState()

            Log.v(TAG, "#refresh end : oidc=$oidc")
            status.emit(OidcStatus.REFRESHED)
        }
    }

    fun clear() {
        viewModelScope.launch {
            oidc.clear()
            updateState()
        }
    }

    override fun toString() =
        "$TAG(status=$status, refreshToken=$refreshToken, accessToken=$accessToken, idToken=$idToken)"
}
