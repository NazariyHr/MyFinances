package nazar.hr.myfinances.domain.model

data class Balance(
    val id: Int,
    val currency: Currency,
    val balance: Float
)