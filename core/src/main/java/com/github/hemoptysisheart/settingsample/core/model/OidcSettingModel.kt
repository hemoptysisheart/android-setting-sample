package com.github.hemoptysisheart.settingsample.core.model

import android.content.SharedPreferences
import android.util.Log
import com.github.hemoptysisheart.settingsample.domain.OidcSetting
import kotlinx.coroutines.delay
import java.util.UUID.randomUUID

class OidcSettingModel(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
) : OidcSetting {
    companion object {
        val TAG = OidcSettingModel::class.simpleName

        private const val KEY_PREFIX = "OidcSettingModel"

        const val KEY_REFRESH_TOKEN = "$KEY_PREFIX#refreshToken"
    }

    override var refreshToken: String?
        get() {
            val token = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
            Log.v(TAG, "#refreshToken return : $token")
            return token
        }
        private set(value) {
            editor.putString(KEY_REFRESH_TOKEN, value)
            editor.apply()
        }

    override var accessToken: String? = null
        private set

    override var idToken: String? = null
        private set

    override suspend fun authorize() {
        refreshToken = "refresh-${randomUUID()}"
        accessToken = "access-${randomUUID()}"
        idToken = "id-${randomUUID()}"
        Log.v(TAG, "#authorize : refreshToken=$refreshToken, accessToken=$accessToken, idToken=$idToken")
    }

    override suspend fun refresh() {
        if (null == refreshToken) {
            Log.i(TAG, "#refresh no oidc authorization.")
            return
        }

        Log.d(TAG, "#refresh dummy refresh delay start.")
        delay(3_000L)
        accessToken = "${randomUUID()}"
        idToken = "${randomUUID()}"
        Log.v(TAG, "#refresh : refreshToken=$refreshToken, accessToken=$accessToken, idToken=$idToken")
    }

    override fun toString() =
        "$TAG(sharedPreferences=$sharedPreferences, editor=$editor, refreshToken=$refreshToken, accessToken=$accessToken, idToken=$idToken)"
}