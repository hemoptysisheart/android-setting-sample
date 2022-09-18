package com.github.hemoptysisheart.settingsample.domain

interface SettingGroup1 {
    var switch: Boolean

    var dropdown: SettingGroup1Dropdown

    var radio: SettingGroup1Radio?
}