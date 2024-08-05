package nazar.hr.myfinances.presentation.features.settings.settings_menu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model.SettingsMenuScreenState
import javax.inject.Inject

@HiltViewModel
class SettingsMenuViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        const val STATE_KEY = "state"
    }

    private var stateValue: SettingsMenuScreenState
        set(value) {
            savedStateHandle[STATE_KEY] = value
        }
        get() {
            return savedStateHandle.get<SettingsMenuScreenState>(STATE_KEY)!!
        }
    val state = savedStateHandle.getStateFlow(STATE_KEY, SettingsMenuScreenState())


}