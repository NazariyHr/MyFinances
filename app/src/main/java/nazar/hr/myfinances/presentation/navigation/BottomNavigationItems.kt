package nazar.hr.myfinances.presentation.navigation

import androidx.annotation.DrawableRes
import nazar.hr.myfinances.R

enum class BottomNavigationItems(
    @DrawableRes val icon: Int,
    val screenToNavigate: ScreenGroup
) {
    WALLETS(R.drawable.ic_wallet, ScreenGroup.WalletsScreensGroup),
    REPORTS(R.drawable.ic_reports, ScreenGroup.ReportsScreensGroup),
    SETTINGS(R.drawable.ic_settings, ScreenGroup.SettingsScreensGroup)
}