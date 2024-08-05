package nazar.hr.myfinances.presentation.features.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsScreenState(
    val title: String = "Settings"
) : Parcelable
