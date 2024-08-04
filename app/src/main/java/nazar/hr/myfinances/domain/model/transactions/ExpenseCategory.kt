package nazar.hr.myfinances.domain.model.transactions

data class ExpenseCategory(
    val id: Int,
    val name: String,
    val description: String,
    val type: ExpenseCategoryType
)

enum class ExpenseCategoryType {
    Another,
    GiveDebt
}