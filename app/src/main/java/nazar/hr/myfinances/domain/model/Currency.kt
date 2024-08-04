package nazar.hr.myfinances.domain.model

data class Currency(
    val id: Int,
    val sign: String,
    val name: String,
    val type: CurrencyType
)

enum class CurrencyType {
    Regular,
    CryptoStableCoin
}

/**
 * @param value - how much [payingCurrency] needed to buy exactly 1.0 amount of [buyingCurrency]
 */
data class CurrencyExchange(
    val id: Int,
    val payingCurrency: Currency,
    val buyingCurrency: Currency,
    val value: Float
)