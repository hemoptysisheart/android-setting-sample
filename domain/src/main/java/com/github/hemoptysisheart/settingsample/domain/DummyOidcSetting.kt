package com.github.hemoptysisheart.settingsample.domain

import kotlinx.coroutines.delay
import java.util.UUID.randomUUID
import java.util.concurrent.ThreadLocalRandom

object DummyOidcSetting : OidcSetting {
    /**
     * See [Online JWT Builder - Jamie Kurtz](http://jwtbuilder.jamiekurtz.com)
     */
    private val DUMMY_ID_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJpc3MiOiJkdW1teSB0b2tlbiIsImlhdCI6MTY2NDU5NzM1MSwiZXhwIjo0MDk0NTEwOTUxLCJhdWQiOiJodHRwczovL2hlbW9wdHlzaXNoZWFydC5naXRodWIuaW8iLCJzdWIiOiJhYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5eiJ9." +
            "Iosns1WHwAy0BUMSDzEbG0iiWo2us5tfmY1scnPbxPw"

    override var refreshToken: String? = null
    override var accessToken: String? = null
        private set

    override var idToken: String? = null
        private set

    override suspend fun authorize() {
        delay(ThreadLocalRandom.current().nextLong(500L, 2000L))
        refreshToken = "refresh-${randomUUID()}"
        accessToken = "access-${randomUUID()}"
        idToken = DUMMY_ID_TOKEN
    }

    override suspend fun refresh() {
        if (null == refreshToken) {
            throw IllegalStateException("refresh token does not exist.")
        } else {
            delay(ThreadLocalRandom.current().nextLong(500L, 2000L))
            accessToken = "access-${randomUUID()}"
            idToken = DUMMY_ID_TOKEN
        }
    }

    override suspend fun clear() {
        delay(ThreadLocalRandom.current().nextLong(500L, 2000L))
        accessToken = null
        idToken = null
        refreshToken = null
    }
}