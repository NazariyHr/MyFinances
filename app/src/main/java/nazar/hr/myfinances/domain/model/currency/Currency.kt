package nazar.hr.myfinances.domain.model.currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val id: Int,
    val sign: String,
    val name: String,
    val type: CurrencyType,
    val isMain: Boolean
) : Parcelable {
    data class EditCurrencyData(
        val sign: String,
        val name: String,
        val type: CurrencyType,
        val isMain: Boolean
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

fun CurrencyType.formattedName(): String {
    return when (this) {
        CurrencyType.REGULAR -> "Regular fiat currency"
        CurrencyType.CRYPTO_STABLE_COIN -> "Crypto stable coin"
    }
}