package nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list

import nazar.hr.myfinances.domain.model.currency.Currency

sealed class CurrenciesListScreenAction {
    data class OnRemoveCurrencyClicked(
        val currency: Currency
    ) : CurrenciesListScreenAction()
    data class OnMainCurrencyChangedClicked(
        val isMain: Boolean,
        val currency: Currency
    ) : CurrenciesListScreenAction()
}