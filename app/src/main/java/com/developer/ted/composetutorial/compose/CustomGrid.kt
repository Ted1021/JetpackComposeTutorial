package com.developer.ted.composetutorial.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface GridScope {
    @Stable
    fun Modifier.span(columns: Int = 1, rows: Int = 1, tag: String?) = this.then(GridData(columns, rows, tag))

    companion object : GridScope
}

private data class GridData(
    val columnSpan: Int,
    val rowSpan: Int,
    val tag: String?
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = this@GridData
}

private val Measurable.gridData: GridData?
    get() = parentData as? GridData

private val Measurable.columnSpan: Int
    get() = gridData?.columnSpan ?: 1

private val Measurable.rowSpan: Int
    get() = gridData?.rowSpan ?: 1

private val Measurable.tag: String
    get() = gridData?.tag ?: ""

@Composable
fun Grid(
    totalColumns: Int = 1,
    totalRows: Int = 1,
    contentPadding: Dp = 0.dp,
    modifier: Modifier = Modifier,
    content: @Composable GridScope.() -> Unit
) {
    Layout(
        content = { GridScope.content() },
        modifier = modifier
    ) { measurables, constraints ->
        val spans = measurables.map { it.gridData ?: throw Exception() }
        val contentPaddingPx = contentPadding.roundToPx()

        // 1. check exception (column, row 둘 중 하나라도 초과하면 throw)
        checkInvalidCell(totalColumns, totalRows, spans)

        // 2. calculate constraints
        // 2-1. base constraints
        val baseConstraints = Constraints.fixed(
            width = (constraints.maxWidth - ((totalColumns - 1) * contentPaddingPx)) / totalColumns,
            height = (constraints.maxHeight - ((totalRows - 1) * contentPaddingPx)) / totalRows
        )

        // 2-2. cell constraints
        val cellConstraints = measurables.map { measurable ->
            val cellColumn = measurable.columnSpan
            val cellRow = measurable.rowSpan
            val cellColumnPadding = (cellColumn - 1) * contentPaddingPx
            val cellRowPadding = (cellRow - 1) * contentPaddingPx

            Constraints.fixed(
                width = baseConstraints.maxWidth * cellColumn + cellColumnPadding,
                height = baseConstraints.maxHeight * cellRow + cellRowPadding
            )
        }

        // 3. measure cells
        val placeables = measurables.mapIndexed { index, measurable -> measurable.measure(cellConstraints[index]) }

        // 4. place cells
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            val surroundingCoordinates = mutableListOf<Pair<Int, Int>>()

            placeables.forEachIndexed { index, placeable ->
                // cell spec
                val columnSpan = spans[index].columnSpan
                val rowSpan = spans[index].rowSpan

                when (index) {
                    // highlight cell
                    0 -> {
                        for (row in 0 until totalRows) {
                            when {
                                row < rowSpan -> {
                                    for (column in columnSpan until totalColumns) {
                                        surroundingCoordinates.add(Pair(row, column))
                                    }
                                }
                                else -> {
                                    for (column in 0 until totalColumns) {
                                        surroundingCoordinates.add(Pair(row, column))
                                    }
                                }
                            }
                        }
                        placeable.placeRelative(0, 0)
                    }
                    // normal cell
                    else -> {
                        val x = surroundingCoordinates[index - 1].second
                        val y = surroundingCoordinates[index - 1].first
                        val xSpace = if (x == 0) 0 else contentPaddingPx
                        val ySpace = if (y == 0) 0 else contentPaddingPx

                        placeable.placeRelative(
                            x = x * (baseConstraints.maxWidth + xSpace),
                            y = y * (baseConstraints.maxHeight + ySpace)
                        )
                    }
                }
            }
        }
    }
}

private fun checkInvalidCell(
    columns: Int,
    rows: Int,
    spans: List<GridData>
) {
    spans.forEach { span ->
        if (span.columnSpan > columns || span.rowSpan > rows) throw Exception()
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFF,
    widthDp = 420,
    heightDp = 400
)
@Composable
fun PreviewGridImage() {
    Grid(
        totalColumns = 3,
        totalRows = 3,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .span(2, 2, "main")
            //.background(color = colorResource(id = R.color.design_default_color_error))
        ) {
            Text(text = "2 x 2", modifier = Modifier.align(Alignment.Center))
        }


        (0..4).forEach { idx ->
            val colorRes = when (idx % 3) {
                0 -> MaterialTheme.colors.onPrimary
                1 -> MaterialTheme.colors.primaryVariant
                else -> MaterialTheme.colors.onSecondary
            }
            Box(
                modifier = Modifier
                    .span(1, 1, "$idx")
                    .background(color = colorRes)
            ) {
                Text(
                    text = "1 x 1",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}