package nazar.hr.myfinances.domain.model.money_holders

import nazar.hr.myfinances.domain.model.currency.Currency

data class FinancialGoal(
    val currency: Currency,
    val amount: Float
)