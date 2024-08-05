package nazar.hr.myfinances.domain.model

import nazar.hr.myfinances.domain.model.currency.Currency

data class Balance(
    val id: Int,
    val currency: Currency,
    val balance: Float
)