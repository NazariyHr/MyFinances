package nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.model.currency.CurrencyType
import nazar.hr.myfinances.domain.model.currency.formattedName
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.components.RemoveIcon
import nazar.hr.myfinances.presentation.common.theme.ColorLightContainers
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

/**
 * Created by nazar at 06.08.2024
 */
@Composable
fun Currency(
    currency: Currency,
    onRemoveClicked: () -> Unit,
    onMainCurrencyChanged: (isMain: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = ColorLightContainers,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val textsToShow = listOf(
                "id:" to currency.id.toString(),
                "sign:" to currency.sign,
                "name:" to currency.name,
                "type:" to currency.type.formattedName()
            )
            textsToShow.forEach { (label, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 1.dp.toPx() / 2,
                                cap = StrokeCap.Round
                            )
                        }
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = currency.isMain,
                    onCheckedChange = { newValue ->
                        onMainCurrencyChanged(newValue)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Is main currency",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }

        RemoveIcon(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopEnd),
            onClicked = onRemoveClicked
        )
    }
}

@Preview
@Composable
private fun CurrencyRegularPreview() {
    MyFinancesTheme {
        MainScreensLayout {
            Currency(
                Currency(
                    id = 1,
                    sign = "UAH",
                    name = "Hryvna",
                    type = CurrencyType.REGULAR,
                    isMain = true
                ),
                onRemoveClicked = {},
                onMainCurrencyChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun CurrencyCryptoPreview() {
    MyFinancesTheme {
        MainScreensLayout {
            Currency(
                Currency(
                    id = 1,
                    sign = "USDT",
                    name = "United States Department of the Treasury",
                    type = CurrencyType.CRYPTO_STABLE_COIN,
                    isMain = false
                ),
                onRemoveClicked = {},
                onMainCurrencyChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}