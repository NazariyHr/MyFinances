package nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model

enum class SettingMenuItems {
    CURRENCIES,
    CURRENCY_EXCHANGES
}

fun SettingMenuItems.getTitle(): String {
    return when (this) {
        SettingMenuItems.CURRENCIES -> "Currencies"
        SettingMenuItems.CURRENCY_EXCHANGES -> "Currency exchanges"
    }
}