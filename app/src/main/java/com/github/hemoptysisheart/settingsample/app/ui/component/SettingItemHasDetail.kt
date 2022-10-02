package com.github.hemoptysisheart.settingsample.app.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hemoptysisheart.settingsample.app.ui.theme.SettingSampleTheme

private const val TAG = "SettingItemHasDetail"

/**
 * 상세 설정이 가능한 메뉴 아이템.
 */
@Composable
fun SettingItemHasDetail(
    itemName: String,
    summary: String? = null,
    onClick: () -> Unit = { Log.v(TAG, "#onClick") }
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clickable { onClick() }
    ) {
        Text(itemName)
        Spacer(Modifier.weight(1.0F))
        if (null != summary) {
            Text(text = summary, color = Color.Gray)
        }
        Icon(Icons.Default.KeyboardArrowRight, "detail", tint = Color.Gray)
    }
}

@Composable
@Preview
fun SettingItemHasDetailPreview() {
    SettingSampleTheme {
        SettingItemHasDetail("Setting item name", "summary")
    }
}
