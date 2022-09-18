package com.github.hemoptysisheart.settingsample.app

import android.app.Application
import android.util.Log
import com.github.hemoptysisheart.settingsample.domain.Settings
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SettingSampleApplication : Application() {
    companion object {
        val TAG = SettingSampleApplication::class.simpleName
    }

    @Inject
    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()

        MainScope().launch {
            Log.i(TAG, "#onCreate start bootstrap.")
            settings.oidc.refresh()
        }
    }
}
