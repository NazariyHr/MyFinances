package nazar.hr.myfinances.domain.model.repetitive

import nazar.hr.myfinances.domain.model.Currency
import nazar.hr.myfinances.domain.model.assets.Asset
import nazar.hr.myfinances.domain.model.transactions.IncomeCategory

/**
 * Assuming monthly income, it is only for planning, and it ISN'T a transaction
 * @param asset - if income connected to some asset, for example planned monthly dividends from business, insurance payments ect.
 */
data class AssumingMonthlyIncome(
    val id: Int,
    val name: String,
    val comments: String,
    val category: IncomeCategory,
    val currency: Currency,
    val amount: Float,
    val dayOfMonth: Int,
    val asset: Asset?
)
