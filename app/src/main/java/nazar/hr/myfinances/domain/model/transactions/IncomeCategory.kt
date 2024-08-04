package nazar.hr.myfinances.domain.model.transactions

data class IncomeCategory(
    val id: Int,
    val name: String,
    val description: String,
    val type: IncomeCategoryType
)

enum class IncomeCategoryType {
    Another,
    TakeDebt,
    Salary
}