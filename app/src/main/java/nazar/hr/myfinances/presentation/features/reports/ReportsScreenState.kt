package nazar.hr.myfinances.presentation.features.reports

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportsScreenState(
    val title: String = "Reports"
) : Parcelable
