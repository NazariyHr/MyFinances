package nazar.hr.myfinances.data.local_db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.model.currency.CurrencyType

@Entity(
    indices = [
        Index(value = ["sign", "name"], unique = true)
    ]
)
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sign: String,
    val name: String,
    val type: String
)

fun CurrencyEntity.toCurrency(): Currency {
    return Currency(
        id,
        sign,
        name,
        CurrencyType.valueOf(type)
    )
}

fun Currency.toCurrencyEntity(): CurrencyEntity {
    return CurrencyEntity(
        id,
        sign,
        name,
        type.name
    )
}

fun Currency.EditCurrencyData.toCurrencyEntity(id: Int): CurrencyEntity {
    return CurrencyEntity(
        id,
        sign,
        name,
        type.name
    )
}

fun Currency.CreateCurrencyData.toCurrencyEntity(): CurrencyEntity {
    return CurrencyEntity(
        0,
        sign,
        name,
        type.name
    )
}