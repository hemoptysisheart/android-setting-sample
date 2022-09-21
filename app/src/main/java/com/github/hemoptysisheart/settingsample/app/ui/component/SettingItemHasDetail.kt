package com.github.hemoptysisheart.settingsample.app.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

private const val TAG = "SettingItemHasDetail"

/**
 * 상세 설정이 가능한 메뉴 아이템.
 */
@Composable
fun SettingItemHasDetail(
    itemName: String = "Setting item name",
    summary: String? = null,
    onClick: () -> Unit = { Log.v(TAG, "#onClick") }
) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Text(itemName)
        Spacer(Modifier.weight(1.0F))
        if (null != summary) {
            Text(summary)
        }
        Icon(Icons.Default.Settings, "")
    }
}

@Composable
@Preview
fun SettingItemHasDetailPreview() {
    SettingItemHasDetail()
}
