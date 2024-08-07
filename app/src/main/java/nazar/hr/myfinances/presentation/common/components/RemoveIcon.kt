package nazar.hr.myfinances.presentation.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.presentation.common.modifiers.safeSingleClick
import nazar.hr.myfinances.presentation.common.theme.ColorLightContainers
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

/**
 * Created by nazar at 06.08.2024
 */
@Composable
fun RemoveIcon(
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (selected) 2f else 1f, label = "")

    var iconSize by remember {
        mutableStateOf(0.dp)
    }
    val d = LocalDensity.current

    Box(
        modifier = modifier
            .size(24.dp)
            .scale(scale)
            .onPlaced {
                iconSize = with(d) { it.size.height.toDp() }
            }
            .safeSingleClick(
                indication = null,
                interactionSource = null
            ) {
                onClicked()
            }
            .pointerInput(true) {
                awaitEachGesture {
                    awaitFirstDown()
                    selected = true

                    do {
                        val event: PointerEvent = awaitPointerEvent()
                    } while (event.changes.any { it.pressed })
                    selected = false
                }
            }
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val sideLength = size.height / 2.6f
                    val lineColor = Color.Red
                    val lineWidth = 1.dp.toPx()

                    val p = Path()
                    p.moveTo((size.width - sideLength) / 2, 0f)
                    p.lineTo(size.width - ((size.width - sideLength) / 2), 0f)
                    p.lineTo(size.width, (size.height - sideLength) / 2)
                    p.lineTo(size.width, size.height - ((size.height - sideLength) / 2))
                    p.lineTo(size.width - ((size.width - sideLength) / 2), size.height)
                    p.lineTo((size.width - sideLength) / 2, size.height)
                    p.lineTo(0f, size.height - ((size.height - sideLength) / 2))
                    p.lineTo(0f, (size.height - sideLength) / 2)
                    p.close()

                    drawPath(
                        p,
                        color = Color(0f, 0f, 0f, 0.25f)
                    )

                    // Top side
                    drawLine(
                        color = lineColor,
                        start = Offset((size.width - sideLength) / 2, 0f),
                        end = Offset(size.width - ((size.width - sideLength) / 2), 0f),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Bottom side
                    drawLine(
                        color = lineColor,
                        start = Offset((size.width - sideLength) / 2, size.height),
                        end = Offset(size.width - ((size.width - sideLength) / 2), size.height),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Left side
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, (size.height - sideLength) / 2),
                        end = Offset(0f, size.height - ((size.height - sideLength) / 2)),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Right side
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width, (size.height - sideLength) / 2),
                        end = Offset(
                            size.width,
                            size.height - ((size.height - sideLength) / 2)
                        ),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Top left
                    drawLine(
                        color = lineColor,
                        start = Offset((size.width - sideLength) / 2, 0f),
                        end = Offset(0f, (size.height - sideLength) / 2),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Top right
                    drawLine(
                        color = lineColor,
                        start = Offset(size.width - ((size.width - sideLength) / 2), 0f),
                        end = Offset(size.width, (size.height - sideLength) / 2),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Bottom left
                    drawLine(
                        color = lineColor,
                        start = Offset((size.width - sideLength) / 2, size.height),
                        end = Offset(0f, size.height - ((size.height - sideLength) / 2)),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    // Bottom right
                    drawLine(
                        color = lineColor,
                        start = Offset(
                            size.width - ((size.width - sideLength) / 2),
                            size.height
                        ),
                        end = Offset(
                            size.width,
                            size.height - ((size.height - sideLength) / 2)
                        ),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    drawLine(
                        color = lineColor,
                        start = center - Offset(sideLength / 2, sideLength / 2),
                        end = center + Offset(sideLength / 2, sideLength / 2),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )

                    drawLine(
                        color = lineColor,
                        start = center - Offset(sideLength / 2, -(sideLength / 2)),
                        end = center + Offset(sideLength / 2, -(sideLength / 2)),
                        strokeWidth = lineWidth,
                        cap = StrokeCap.Round
                    )
                }
        )
    }
}

@Preview
@Composable
private fun RemoveIconPreview() {
    MyFinancesTheme {
        Box(
            modifier = Modifier
                .background(ColorLightContainers)
                .padding(20.dp)
        ) {
            RemoveIcon(
                modifier = Modifier.size(24.dp),
                onClicked = {}
            )
        }
    }
}