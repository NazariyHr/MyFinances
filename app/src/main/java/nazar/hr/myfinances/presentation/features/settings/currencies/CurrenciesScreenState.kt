package nazar.hr.myfinances.presentation.features.settings.currencies

import nazar.hr.myfinances.domain.model.currency.Currency

data class CurrenciesScreenState(
    val title: String = "Currencies",
    val currencies: List<Currency> = listOf()
)
