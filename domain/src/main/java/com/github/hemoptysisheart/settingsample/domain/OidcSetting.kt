package com.github.hemoptysisheart.settingsample.domain

/**
 * OIDC 인증 정보.
 */
interface OidcSetting {
    val refreshToken: String?

    val accessToken: String?

    val idToken: String?

    /**
     * OIDC 인증하기.
     */
    suspend fun authorize()

    /**
     * OIDC 토큰 재발급.
     */
    suspend fun refresh()

    /**
     * OIDC 인증 제거.
     */
    suspend fun clear()
}