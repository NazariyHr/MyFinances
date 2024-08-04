package nazar.hr.myfinances.domain.model.transactions

import nazar.hr.myfinances.domain.model.Balance
import nazar.hr.myfinances.domain.model.assets.Asset
import nazar.hr.myfinances.domain.model.loans_and_debts.BankLoan
import nazar.hr.myfinances.domain.model.loans_and_debts.Debt
import nazar.hr.myfinances.domain.model.money_holders.FinancialCushion
import nazar.hr.myfinances.domain.model.money_holders.PiggyBank
import nazar.hr.myfinances.domain.model.money_holders.Wallet
import java.util.Calendar

sealed class Transaction(
    open val id: Int,
    open val dayOfTransaction: Calendar,
    open val comments: String
) {
    data class TransferWalletToWallet(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val fromWallet: WalletTransactionInfo,
        val toWallet: WalletTransactionInfo
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money taken from this wallet
     */
    data class Expense(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val category: ExpenseCategory,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money obtained into this wallet
     */
    data class Income(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val category: IncomeCategory,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * This class represents either my and my financial partner's repayments.
     * It depends on value from whoIsBorrower in [debt]
     */
    data class DebtRepayment(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val debt: Debt,
        val dayOfRepayment: Calendar,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float,
        val amountInDebtCurrency: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * This class represents either my and my financial partner's monthly interest payments for debt.
     * It depends on value from whoIsBorrower in [debt]
     */
    data class DebtMonthlyPayment(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val debt: Debt,
        val dayOfPayment: Calendar,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float,
        val amountInDebtCurrency: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * This class represents my loan repayments.
     */
    data class BankLoanRepayment(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val loan: BankLoan,
        val dayOfRepayment: Calendar,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * This class represents my monthly payments for loan.
     */
    data class BankLoanMonthlyPayment(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val loan: BankLoan,
        val dayOfPayment: Calendar,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param asset - asset that has been bought
     * @param wallet - wallet from which money was taken
     */
    data class AssetBuying(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val asset: Asset,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param asset - asset that has been sold
     * @param wallet - money obtained into this wallet
     */
    data class AssetSelling(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val asset: Asset,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money obtained into this wallet
     */
    data class AssetIncome(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val assetOfIncome: Asset,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * This class represents an additional investment into asset after it's buying.
     * It's like an additional expense into asset.
     */
    data class AssetInvestment(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val asset: Asset,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money taken from this wallet
     */
    data class PutIntoPiggyBank(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float,
        val piggyBank: PiggyBank,
        val amountInPiggyBankBalanceAfterTransaction: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money transferred into this wallet
     */
    data class GetFromPiggyBank(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val piggyBank: PiggyBank,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float,
        val amountInPiggyBankBalanceAfterTransaction: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money taken from this wallet
     */
    data class PutIntoFinancialCushion(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float,
        val financialCushion: FinancialCushion,
        val financialCushionBalance: Balance,
        val amountInFinancialCushionBalanceAfterTransaction: Float
    ) : Transaction(id, dayOfTransaction, comments)

    /**
     * @param wallet - money transferred into this wallet
     */
    data class GetFromFinancialCushion(
        override val id: Int,
        override val dayOfTransaction: Calendar,
        override val comments: String,
        val financialCushion: FinancialCushion,
        val financialCushionBalance: Balance,
        val amountInFinancialCushionBalanceAfterTransaction: Float,
        val wallet: Wallet,
        val balance: Balance,
        val amount: Float
    ) : Transaction(id, dayOfTransaction, comments)
}