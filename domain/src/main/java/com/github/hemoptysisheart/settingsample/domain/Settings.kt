package com.github.hemoptysisheart.settingsample.domain

interface Settings {
    val oidc: OidcSetting

    val group1: SettingGroup1
    
    val group2: SettingGroup2
}