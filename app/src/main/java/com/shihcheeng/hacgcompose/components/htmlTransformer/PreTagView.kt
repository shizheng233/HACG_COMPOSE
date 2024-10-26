package com.shihcheeng.hacgcompose.components.htmlTransformer

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shihcheeng.hacgcompose.R
import org.jsoup.nodes.Element

@Composable
fun PreTagView(
    modifier: Modifier = Modifier,
    element: Element
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.preview_text),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = element.wholeText(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .horizontalScroll(
                        state = rememberScrollState()
                    )
                    .padding(top = 8.dp)
            )
        }
    }
}
