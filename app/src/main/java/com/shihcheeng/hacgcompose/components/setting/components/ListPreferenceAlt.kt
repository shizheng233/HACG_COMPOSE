package com.shihcheeng.hacgcompose.components.setting.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.ui.icons.Iconify
import com.shihcheeng.hacgcompose.ui.icons.LineiconsDialogflow

@Composable
fun <T> IntListPreference(
    modifier: Modifier = Modifier,
    selectItem: T,
    list: Map<Int, T>,
    title: String,
    summaryText: String,
    dialogSummary: String = summaryText,
    dialogTitle: String = title,
    leadingIcon: @Composable () -> Unit,
    onSelected: (T) -> Unit
) {

    var isShowDialog by rememberSaveable { mutableStateOf(false) }
    ListItem(
        headlineContent = {
            Text(text = title)
        },
        supportingContent = {
            Text(text = summaryText)
        },
        leadingContent = leadingIcon,
        modifier = modifier.clickable {
            isShowDialog = true
        }
    )
    if (isShowDialog) {
        IntListItemDialog(
            list = list,
            selected = selectItem,
            onSelected = onSelected,
            dismissRequest = {
                isShowDialog = false
            },
            dialogSummary = dialogSummary,
            title = dialogTitle
        )
    }
}

@Composable
private fun <T> IntListItemDialog(
    modifier: Modifier = Modifier,
    list: Map<Int, T>,
    selected: T,
    onSelected: (T) -> Unit,
    dismissRequest: () -> Unit,
    dialogSummary: String,
    title: String
) {
    Dialog(
        onDismissRequest = dismissRequest,
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(24.dp)
                    .then(modifier),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Iconify.LineiconsDialogflow,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = dialogSummary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                HorizontalDivider(Modifier.padding(top = 8.dp))
                list.forEach { (index, s) ->
                    val interactionSource = remember { MutableInteractionSource() }
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = s == selected,
                                interactionSource = interactionSource,
                                indication = LocalIndication.current,
                                onClick = {
                                    onSelected(s)
                                }
                            )
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = s == selected,
                            onClick = {
                                onSelected(s)
                            },
                            interactionSource = interactionSource,
                        )
                        Text(
                            text = stringResource(index),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentSize provides Dp.Unspecified
                    ) {
                        TextButton(
                            onClick = dismissRequest
                        ) {
                            Text(
                                text = stringResource(R.string.open_magnet),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

            }
        }
    }
}