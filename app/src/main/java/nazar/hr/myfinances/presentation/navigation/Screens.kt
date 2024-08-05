package nazar.hr.myfinances.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object TestScreenWithoutNavBar : Screen()

    @Serializable
    data object SettingsMenu : Screen()

    @Serializable
    data object Currencies : Screen()

    @Serializable
    data object Wallets : Screen()

    @Serializable
    @Parcelize
    data class Wallet(val walletId: Int) : Screen(), Parcelable
}

sealed class ScreenGroups : Screen() {
    @Serializable
    data object SettingsScreensGroup : ScreenGroups()

    @Serializable
    data object WalletsScreensGroup : ScreenGroups()
}