package nazar.hr.myfinances.domain.model.repetitive

import nazar.hr.myfinances.domain.model.Currency
import nazar.hr.myfinances.domain.model.transactions.ExpenseCategory

/**
 * Assuming monthly expense, it is only for planning, and it ISN'T a transaction
 */
data class AssumingMonthlyExpense(
    val id: Int,
    val name: String,
    val comments: String,
    val category: ExpenseCategory,
    val currency: Currency,
    val amount: Float,
    val dayOfMonth: Int
)
