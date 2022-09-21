package com.github.hemoptysisheart.settingsample.app.viewmodel

import androidx.lifecycle.ViewModel
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OidcSettingMenuGroupViewModel @Inject constructor(
) : ViewModel() {
    companion object {
        val TAG = OidcSettingMenuGroupViewModel::class.simpleName
    }

    /**
     * DI only.
     */
    lateinit var settings: Settings
    val oidc: OidcSetting
        get() = settings.oidc

    override fun toString() = "$TAG(oidc=$oidc)"
}
