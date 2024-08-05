package nazar.hr.myfinances.presentation.features.wallets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletsScreenState(
    val title: String = "Wallets"
) : Parcelable
