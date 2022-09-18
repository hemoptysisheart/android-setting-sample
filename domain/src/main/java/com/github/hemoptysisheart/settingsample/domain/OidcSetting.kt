package com.github.hemoptysisheart.settingsample.domain

/**
 * OIDC 인증 정보.
 */
interface OidcSetting {
    var refreshToken: String

    var accessToken: String?

    var idToken: String?

    suspend fun refresh()
}