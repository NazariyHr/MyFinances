package nazar.hr.myfinances.presentation.features.settings.currencies.create_currency

import nazar.hr.myfinances.domain.model.currency.CurrencyType

sealed class CreateCurrencyScreenAction {
    data class OnCreateClicked(
        val sign: String,
        val name: String,
        val type: CurrencyType
    ) : CreateCurrencyScreenAction()
}