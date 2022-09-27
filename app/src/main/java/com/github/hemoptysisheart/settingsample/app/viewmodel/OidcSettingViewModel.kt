package com.github.hemoptysisheart.settingsample.app.viewmodel

import androidx.lifecycle.ViewModel
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
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

    override fun toString() = "$TAG(status=$status)"
}
