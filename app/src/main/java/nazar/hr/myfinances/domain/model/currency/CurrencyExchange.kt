package nazar.hr.myfinances.domain.model.currency

/**
 * @param value - how much [payingCurrency] needed to buy exactly 1.0 amount of [buyingCurrency]
 */
data class CurrencyExchange(
    val id: Int,
    val payingCurrency: Currency,
    val buyingCurrency: Currency,
    val value: Float
)