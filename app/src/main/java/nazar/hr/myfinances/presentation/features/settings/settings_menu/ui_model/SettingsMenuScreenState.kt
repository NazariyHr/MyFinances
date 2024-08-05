package nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsMenuScreenState(
    val title: String = "Settings"
) : Parcelable
