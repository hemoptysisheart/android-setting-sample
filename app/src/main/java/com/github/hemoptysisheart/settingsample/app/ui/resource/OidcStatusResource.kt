package com.github.hemoptysisheart.settingsample.app.ui.resource

import androidx.annotation.StringRes
import com.github.hemoptysisheart.settingsample.R.string.*
import com.github.hemoptysisheart.settingsample.domain.OidcStatus
import com.github.hemoptysisheart.settingsample.domain.OidcStatus.*

enum class OidcStatusResource(
    val status: OidcStatus,
    @StringRes val label: Int
) {
    ANONYMOUS_RES(ANONYMOUS, domain_oidc_setting_status_anonymous_label),
    AUTHORIZING_RES(AUTHORIZING, domain_oidc_setting_status_authorizing_label),
    AUTHORIZED_RES(AUTHORIZED, domain_oidc_setting_status_authorized_label),
    REFRESHING_RES(REFRESHING, domain_oidc_setting_status_refreshing_label),
    REFRESHED_RES(REFRESHED, domain_oidc_setting_status_refreshed_label);

    companion object {
        private val STATUS_MAP = values().associateBy { it.status }

        operator fun get(status: OidcStatus) = STATUS_MAP[status]!!

        operator fun get(ordinal: Int) = values()[ordinal]

        init {
            if (values().size != STATUS_MAP.size) {
                throw IllegalStateException("There are duplicated values.")
            }
            values().onEach {
                if (it.ordinal != it.status.ordinal) {
                    throw IllegalStateException("ordinal does not match : $it(${it.ordinal}) != ${it.status}(${it.status.ordinal})")
                }
            }
        }
    }
}