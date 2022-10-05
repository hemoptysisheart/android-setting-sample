package com.github.hemoptysisheart.settingsample.domain

/**
 * OIDC 인증 정보.
 */
interface OidcSetting {
    val status: OidcStatus
        get() = when {
            null == DummyOidcSetting.refreshToken -> OidcStatus.ANONYMOUS
            null != DummyOidcSetting.refreshToken && null == DummyOidcSetting.accessToken && null == DummyOidcSetting.idToken -> OidcStatus.AUTHORIZED
            null != DummyOidcSetting.refreshToken && null != DummyOidcSetting.accessToken && null != DummyOidcSetting.idToken -> OidcStatus.REFRESHED
            else -> throw IllegalStateException("refreshToken=${DummyOidcSetting.refreshToken}, accessToken=${DummyOidcSetting.accessToken}, idToken=${DummyOidcSetting.idToken}")
        }

    val refreshToken: String?

    val accessToken: String?

    val idToken: String?

    suspend fun authorize()

    suspend fun refresh()
}