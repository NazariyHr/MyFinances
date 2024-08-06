package nazar.hr.myfinances.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.presentation.common.theme.ColorLightContainers
import nazar.hr.myfinances.presentation.common.theme.ColorPrimaryDark
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.common.theme.TextColorDefault

/**
 * Created by nazar at 06.08.2024
 */
@Composable
fun AddButton(
    title: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var iconSize by remember {
        mutableStateOf(0.dp)
    }
    val d = LocalDensity.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ColorLightContainers)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val radius = 20.dp.toPx()
                inset(
                    strokeWidth / 2
                ) {
                    drawOutline(
                        outline = Outline.Rounded(
                            RoundRect(
                                left = 0f,
                                top = 0f,
                                right = size.width,
                                bottom = size.height,
                                radiusX = radius,
                                radiusY = radius
                            )
                        ),
                        color = TextColorDefault,
                        style = Stroke(
                            strokeWidth,
                            pathEffect = PathEffect.dashPathEffect(
                                intervals = floatArrayOf(30f, 10f),
                                phase = 10f
                            )
                        )
                    )
                }
            }
            .clickable(
                indication = rememberRipple(
                    color = ColorPrimaryDark
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClicked()
            }
            .padding(horizontal = 8.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
        ) {
            Spacer(
                modifier = Modifier
                    .size(iconSize)
                    .drawBehind {
                        val padding = size.height / 5

                        drawLine(
                            color = TextColorDefault,
                            start = Offset(center.x, 0f + padding),
                            end = Offset(center.x, size.height - padding),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = TextColorDefault,
                            start = Offset(0f + padding, center.y),
                            end = Offset(size.width - padding, center.y),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp)
                    .onPlaced {
                        iconSize = with(d) { it.size.height.toDp() }
                    }
            )
        }
    }
}

@Preview
@Composable
private fun AddCurrencyButtonPreview() {
    MyFinancesTheme {
        MainScreensLayout {
            AddButton(
                title = "Create currency",
                onClicked = {}
            )
        }
    }
}