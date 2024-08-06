package nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nazar.hr.myfinances.domain.repository.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class CurrenciesListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    companion object {
        const val STATE_KEY = "state"
    }

    private var stateValue: CurrenciesListScreenState
        set(value) {
            savedStateHandle[STATE_KEY] = value
        }
        get() {
            return savedStateHandle.get<CurrenciesListScreenState>(STATE_KEY)!!
        }
    val state = savedStateHandle.getStateFlow(STATE_KEY, CurrenciesListScreenState())

    init {
        currencyRepository
            .getCurrenciesFlow()
            .onEach { currencies ->
                stateValue = stateValue.copy(currencies = currencies)
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: CurrenciesListScreenAction) {
        when (action) {
            is CurrenciesListScreenAction.OnRemoveCurrencyClicked -> {
                viewModelScope.launch {
                    currencyRepository.removeCurrency(action.currency.id)
                }
            }
        }
    }
}