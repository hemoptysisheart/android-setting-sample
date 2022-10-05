package com.github.hemoptysisheart.settingsample.domain

/**
 * OIDC 인증 상태.
 */
enum class OidcStatus(
    val authorized: Boolean
) {
    /**
     * 리프레시 토큰을 발급받지 않은 상태.
     */
    ANONYMOUS(false),

    /**
     * 리프레시 토큰을 발급받는 중.
     */
    AUTHORIZING(false),

    /**
     * 리프레시 토큰은 있지만 ID 토큰과 액세스 토큰은 없는 상태.
     */
    AUTHORIZED(true),

    /**
     * 리프레시 토큰이 있고, 리프레시 토큰을 ID 토큰과 액세스 토큰으로 교환중인 임시 상태.
     */
    REFRESHING(true),

    /**
     * 유효한 ID 토큰과 액세스 토큰을 발급받은 상태.
     */
    REFRESHED(true);

    companion object {
        operator fun get(ordinal: Int) = values()[ordinal]
    }
}