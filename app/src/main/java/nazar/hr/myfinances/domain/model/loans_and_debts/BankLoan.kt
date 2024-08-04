package nazar.hr.myfinances.domain.model.loans_and_debts

import nazar.hr.myfinances.domain.model.Currency

data class BankLoan(
    val id: Int,
    val bank: Bank,
    val currency: Currency,
    val amount: Float,
    val repaidAmount: Float,
    val monthlyPaymentPercent: Float,
    val dayOfMonthToPay: Int
)
