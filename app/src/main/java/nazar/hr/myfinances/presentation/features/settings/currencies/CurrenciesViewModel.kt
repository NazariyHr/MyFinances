package nazar.hr.myfinances.presentation.features.settings.currencies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        const val STATE_KEY = "state"
    }

    private var stateValue: CurrenciesScreenState
        set(value) {
            savedStateHandle[STATE_KEY] = value
        }
        get() {
            return savedStateHandle.get<CurrenciesScreenState>(STATE_KEY)!!
        }
    val state = savedStateHandle.getStateFlow(STATE_KEY, CurrenciesScreenState())


}