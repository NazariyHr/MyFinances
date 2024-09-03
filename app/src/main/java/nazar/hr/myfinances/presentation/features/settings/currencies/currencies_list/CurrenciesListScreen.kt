package nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.model.currency.CurrencyType
import nazar.hr.myfinances.presentation.common.components.AddButton
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list.components.Currency
import nazar.hr.myfinances.presentation.navigation.Screen

@Composable
fun CurrenciesListScreenRoot(
    navController: NavController,
    viewModel: CurrenciesListViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CurrenciesListScreen(
        state = state,
        onAction = viewModel::onAction,
        onCreateCurrencyClicked = {
            navController.navigate(Screen.CreateCurrency)
        }
    )
}

@Composable
private fun CurrenciesListScreen(
    state: CurrenciesListScreenState,
    onAction: (action: CurrenciesListScreenAction) -> Unit,
    onCreateCurrencyClicked: () -> Unit
) {
    MainScreensLayout {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = state.title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    AddButton(
                        title = "Create currency",
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        onClicked = {
                            onCreateCurrencyClicked()
                        }
                    )
                }
                itemsIndexed(
                    items = state.currencies,
                    key = { _, currency -> currency.id }
                ) { index, currency ->
                    val gap = 8.dp
                    Currency(
                        currency = currency,
                        modifier = Modifier.padding(
                            top = if (index != 0) gap / 2 else 0.dp,
                            bottom = if (index != state.currencies.count() - 1) gap / 2 else 0.dp
                        ),
                        onRemoveClicked = {
                            onAction(CurrenciesListScreenAction.OnRemoveCurrencyClicked(currency))
                        },
                        onMainCurrencyChanged = { isMain ->
                            onAction(
                                CurrenciesListScreenAction.OnMainCurrencyChangedClicked(
                                    isMain,
                                    currency
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CurrenciesListScreenPreview() {
    val previewCurrencies = listOf(
        Currency(
            id = 1,
            sign = "UAH",
            name = "Hryvna",
            type = CurrencyType.REGULAR,
            isMain = true
        ),
        Currency(
            id = 2,
            sign = "USDT",
            name = "United States Department of the Treasury",
            type = CurrencyType.CRYPTO_STABLE_COIN,
            isMain = false
        )
    )
    MyFinancesTheme {
        CurrenciesListScreen(
            state = CurrenciesListScreenState(
                currencies = previewCurrencies
            ),
            onAction = {},
            onCreateCurrencyClicked = {}
        )
    }
}