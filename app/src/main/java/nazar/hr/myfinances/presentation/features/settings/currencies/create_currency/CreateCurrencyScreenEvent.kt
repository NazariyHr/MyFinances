package nazar.hr.myfinances.presentation.features.settings.currencies.create_currency

sealed class CreateCurrencyScreenEvent {
    data object OnCurrencyCreated : CreateCurrencyScreenEvent()
    data class OnCurrencyCreationFailed(
        val reason: String
    ) : CreateCurrencyScreenEvent()
}