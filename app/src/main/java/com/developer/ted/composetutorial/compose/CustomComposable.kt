package com.developer.ted.composetutorial.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measureables, constraints ->
        val placeables = measureables.map { measurable -> measurable.measure(constraints) }
        val width = placeables.maxOf { it.width }
        val height = placeables.sumOf { it.height }

        layout(width, height) {
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = y)
                y += placeable.height
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
fun PreviewCustomColumn() {
    CustomColumn(modifier = Modifier.fillMaxWidth()) {
        Text(text = "1")
        Text(text = "2")
        Text(text = "3")
        Text(text = "4")
    }
}