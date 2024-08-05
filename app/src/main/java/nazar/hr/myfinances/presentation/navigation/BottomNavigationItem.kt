package nazar.hr.myfinances.presentation.navigation

import androidx.annotation.DrawableRes
import nazar.hr.myfinances.R

enum class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val screenToNavigate: Screen
) {
    WALLETS(R.drawable.ic_wallet, ScreenGroups.WalletsScreensGroup),
    SETTINGS(R.drawable.ic_settings, ScreenGroups.SettingsScreensGroup)
}