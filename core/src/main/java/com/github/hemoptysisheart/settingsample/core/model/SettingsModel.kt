package com.github.hemoptysisheart.settingsample.core.model

import android.content.SharedPreferences
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import com.github.hemoptysisheart.settingsample.domain.SettingGroup1
import com.github.hemoptysisheart.settingsample.domain.SettingGroup2
import com.github.hemoptysisheart.settingsample.domain.Settings

class SettingsModel(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
) : Settings {
    override val oidc: OidcSetting?
        get() = TODO("Not yet implemented")
    override val group1: SettingGroup1
        get() = TODO("Not yet implemented")
    override val group2: SettingGroup2
        get() = TODO("Not yet implemented")
}