package nazar.hr.myfinances.domain.model.transactions

import nazar.hr.myfinances.domain.model.Balance
import nazar.hr.myfinances.domain.model.money_holders.Wallet

data class WalletTransactionInfo(
    val wallet: Wallet,
    val balance: Balance,
    val balanceChange: Float,
    val commission: Float
)