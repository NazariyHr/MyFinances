package nazar.hr.myfinances.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object TestScreenWithoutNavBar : Screen()

    @Serializable
    data object ReportsMenu : Screen()

    @Serializable
    data object SettingsMenu : Screen()

    @Serializable
    data object CurrenciesList : Screen()

    @Serializable
    data object CreateCurrency : Screen()

    @Serializable
    data object Wallets : Screen()

    @Serializable
    @Parcelize
    data class Wallet(val walletId: Int) : Screen(), Parcelable
}

sealed class ScreenGroup : Screen() {
    @Serializable
    data object WalletsScreensGroup : ScreenGroup() {
        override fun getFirstScreenOfGroup(): Screen = Wallets
    }

    @Serializable
    data object ReportsScreensGroup : ScreenGroup() {
        override fun getFirstScreenOfGroup(): Screen = ReportsMenu
    }

    @Serializable
    data object SettingsScreensGroup : ScreenGroup() {
        override fun getFirstScreenOfGroup(): Screen = SettingsMenu
    }

    abstract fun getFirstScreenOfGroup(): Screen
}