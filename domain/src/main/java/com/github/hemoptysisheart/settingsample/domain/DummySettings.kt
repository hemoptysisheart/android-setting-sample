package com.github.hemoptysisheart.settingsample.domain

object DummySettings : Settings {
    override val oidc: OidcSetting = DummyOidcSetting

    override val group1: SettingGroup1
        get() = TODO("Not yet implemented")
    override val group2: SettingGroup2
        get() = TODO("Not yet implemented")
}