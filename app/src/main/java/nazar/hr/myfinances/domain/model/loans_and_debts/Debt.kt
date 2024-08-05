package nazar.hr.myfinances.domain.model.loans_and_debts

import nazar.hr.myfinances.domain.model.currency.Currency

data class Debt(
    val id: Int,
    val whoIsBorrower: BorrowerType,
    val financialPartner: FinancialPartner,
    val currency: Currency,
    val debtAmount: Float,
    val repaidAmount: Float,
    val monthlyInterestPayments: MonthlyInterestPayments?
)

enum class BorrowerType {
    Me,
    MyFinancialPartner
}

data class MonthlyInterestPayments(
    val id: Int,
    val paymentPercent: Float,
    val dayOfMonthToPay: Int
)