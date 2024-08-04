package nazar.hr.myfinances.domain.model.money_holders

import nazar.hr.myfinances.domain.model.Balance

data class Wallet(
    val id: Int,
    val name: String,
    val comments: String,
    val balances: List<Balance>
)