package com.shihcheeng.hacgcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.datamodel.MainDataModel

@Composable
fun MainCardView(
    modifier: Modifier = Modifier,
    mainModel: MainDataModel,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = mainModel.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(R.string.time_by_author, mainModel.time, mainModel.publisher),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 2.dp)
            )
            HacgAsyncImage(
                url = mainModel.imageURl,
                modifier = Modifier
                    .height(220.dp)
                    .padding(vertical = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Text(
                text = mainModel.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 2.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, top = 8.dp)
            ) {
                ProvideTextStyle(
                    value = MaterialTheme.typography
                        .labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                ) {
                    Text(
                        text = mainModel.category.name
                    )
                    Text(text = " \u2022 ")
                    Text(text = mainModel.tags.joinToString("、") { x -> x.name })
                }
            }
        }
    }
}

