package nazar.hr.myfinances.presentation.features.settings.currencies.create_currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateCurrencyScreenState(
    val title: String = "Create currency",
    val creationInProgress: Boolean = false
) : Parcelable
