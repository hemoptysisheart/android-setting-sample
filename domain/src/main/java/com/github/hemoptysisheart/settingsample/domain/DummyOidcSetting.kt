package com.github.hemoptysisheart.settingsample.domain

import java.lang.Thread.sleep

object DummyOidcSetting : OidcSetting {
    override var refreshToken: String? = null

    override val accessToken: String? = null

    override val idToken: String? = null

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun refresh() {
        sleep(1_000L)
    }
}