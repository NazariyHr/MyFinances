package nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import nazar.hr.myfinances.domain.model.currency.Currency

@Parcelize
data class CurrenciesListScreenState(
    val title: String = "Currencies",
    val currencies: List<Currency> = listOf()
) : Parcelable
