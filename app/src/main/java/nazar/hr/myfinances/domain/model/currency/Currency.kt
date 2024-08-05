package nazar.hr.myfinances.domain.model.currency

data class Currency(
    val id: Int,
    val sign: String,
    val name: String,
    val type: CurrencyType
) {
    data class EditCurrencyData(
        val sign: String,
        val name: String,
        val type: CurrencyType
    )

    data class CreateCurrencyData(
        val sign: String,
        val name: String,
        val type: CurrencyType
    )
}

enum class CurrencyType {
    REGULAR,
    CRYPTO_STABLE_COIN
}