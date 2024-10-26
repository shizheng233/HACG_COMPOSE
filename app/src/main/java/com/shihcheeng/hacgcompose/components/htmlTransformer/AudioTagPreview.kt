package com.shihcheeng.hacgcompose.components.htmlTransformer

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioTagPreview(
    modifier: Modifier = Modifier,
    audioLink: String
) {
    val slideState = AudioTagPreviewState.rememberSliderState()
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .then(modifier),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 6.0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "12",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Slider(
                state = slideState,
                modifier = Modifier
                    .padding(all = 2.dp)
                    .weight(1f)
            )
            Text(
                text = "14",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object AudioTagPreviewState {


    @Composable
    fun rememberSliderState(
        value: Float = 0f,
        @IntRange(from = 0) steps: Int = 0,
        onValueChangeFinished: (() -> Unit)? = null,
        valueRange: ClosedFloatingPointRange<Float> = 0f..1f
    ) = remember {
        SliderState(
            value = value,
            steps = steps,
            onValueChangeFinished = onValueChangeFinished,
            valueRange = valueRange
        )
    }

}

@Preview
@Composable
fun AudioTagPreviewPreview() {
    AudioTagPreview(audioLink = "")
}