package nazar.hr.myfinances.presentation.features.settings.currencies.create_currency

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nazar.hr.myfinances.domain.errors.DataBaseErrors
import nazar.hr.myfinances.domain.model.currency.Currency
import nazar.hr.myfinances.domain.model.currency.CurrencyType
import nazar.hr.myfinances.domain.repository.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class CreateCurrencyViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    companion object {
        const val STATE_KEY = "state"
    }

    private var stateValue: CreateCurrencyScreenState
        set(value) {
            savedStateHandle[STATE_KEY] = value
        }
        get() {
            return savedStateHandle.get<CreateCurrencyScreenState>(STATE_KEY)!!
        }
    val state = savedStateHandle.getStateFlow(STATE_KEY, CreateCurrencyScreenState())

    private val _events = Channel<CreateCurrencyScreenEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CreateCurrencyScreenAction) {
        when (action) {
            is CreateCurrencyScreenAction.OnCreateClicked -> {
                createCurrency(action.sign, action.name, action.type)
            }
        }
    }

    private fun createCurrency(
        sign: String,
        name: String,
        type: CurrencyType
    ) {
        viewModelScope.launch {
            stateValue = stateValue.copy(
                creationInProgress = true
            )
            try {
                currencyRepository.createCurrency(Currency.CreateCurrencyData(sign, name, type))
                _events.send(CreateCurrencyScreenEvent.OnCurrencyCreated)
            } catch (e: DataBaseErrors) {
                val message = when (e) {
                    is DataBaseErrors.UnexpectedError -> "Unexpected error: ${e.message}"
                    is DataBaseErrors.UniqueConstraintError -> "Sign or name already exists, need to use unique ones."
                }
                _events.send(CreateCurrencyScreenEvent.OnCurrencyCreationFailed(message))
            }
            stateValue = stateValue.copy(
                creationInProgress = false
            )
        }
    }
}