package nazar.hr.myfinances.presentation.features.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        const val STATE_KEY = "state"
    }

    private var stateValue: SettingsScreenState
        set(value) {
            savedStateHandle[STATE_KEY] = value
        }
        get() {
            return savedStateHandle.get<SettingsScreenState>(STATE_KEY)!!
        }
    val state = savedStateHandle.getStateFlow(STATE_KEY, SettingsScreenState())


}