package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.ui.icons.Iconify
import com.shihcheeng.hacgcompose.ui.icons.MdiMagnet

@Composable
fun MagnetDialog(
    modifier: Modifier = Modifier,
    list: List<String>,
    dismissRequest: () -> Unit,
    onOpen: (String) -> Unit,
    onCopy: (String) -> Unit
) {
    val (selectedItem, onSelected) = rememberSaveable { mutableIntStateOf(0) }
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
                    imageVector = Iconify.MdiMagnet,
                    contentDescription = stringResource(R.string.see_the_magnet),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(R.string.magnet_list_title),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = stringResource(R.string.magnet_list_summary),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                HorizontalDivider(Modifier.padding(top = 8.dp))
                list.forEachIndexed { index, s ->
                    val interactionSource = remember { MutableInteractionSource() }
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = index == selectedItem,
                                interactionSource = interactionSource,
                                indication = LocalIndication.current,
                                onClick = {
                                    onSelected(index)
                                }
                            )
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = index == selectedItem,
                            onClick = {
                                onSelected(index)
                            },
                            interactionSource = interactionSource,
                        )
                        Text(
                            text = s,
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
                            onClick = {
                                onCopy(list[selectedItem])
                                dismissRequest()
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.copy_magnet),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        TextButton(
                            onClick = {
                                onOpen(list[selectedItem])
                                dismissRequest()
                            }
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