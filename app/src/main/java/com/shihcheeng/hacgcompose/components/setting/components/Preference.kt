package com.shihcheeng.hacgcompose.components.setting.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Preference(
    modifier: Modifier = Modifier,
    title: String,
    summary: String,
    iconProvider: @Composable () -> Unit,
    onClick: (() -> Unit)? = null
) {
    ListItem(
        headlineContent = {
            Text(text = title)
        },
        supportingContent = {
            Text(text = summary)
        },
        leadingContent = {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                iconProvider()
            }
        },
        modifier = Modifier
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
            .then(modifier)
    )
}