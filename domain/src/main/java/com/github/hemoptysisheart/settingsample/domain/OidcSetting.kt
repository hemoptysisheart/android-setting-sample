package com.github.hemoptysisheart.settingsample.domain

/**
 * OIDC 인증 정보.
 */
interface OidcSetting {
    val refreshToken: String?

    val accessToken: String?

    val idToken: String?

    suspend fun refresh()
}