package nazar.hr.myfinances.domain.model

import nazar.hr.myfinances.domain.model.currency.Currency

data class Settings(
    val mainCurrency: Currency
)
