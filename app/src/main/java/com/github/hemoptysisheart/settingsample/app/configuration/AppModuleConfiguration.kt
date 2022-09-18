package com.github.hemoptysisheart.settingsample.app.configuration

import com.github.hemoptysisheart.settingsample.core.configuration.CoreModuleConfiguration
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [CoreModuleConfiguration::class])
@InstallIn(SingletonComponent::class)
class AppModuleConfiguration {
    companion object {
        val TAG = AppModuleConfiguration::class.simpleName
    }
}