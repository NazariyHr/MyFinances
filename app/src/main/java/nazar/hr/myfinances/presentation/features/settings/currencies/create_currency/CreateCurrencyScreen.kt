package nazar.hr.myfinances.presentation.features.settings.currencies.create_currency

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nazar.hr.myfinances.domain.model.currency.CurrencyType
import nazar.hr.myfinances.domain.model.currency.formattedName
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.components.ObserveAsEvent
import nazar.hr.myfinances.presentation.common.components.SelectableTextField
import nazar.hr.myfinances.presentation.common.theme.ColorLightContainers
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

@Composable
fun CreateCurrencyScreenRoot(
    navController: NavController,
    viewModel: CreateCurrencyViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CreateCurrencyScreen(
        state = state,
        events = viewModel.events,
        onAction = viewModel::onAction,
        navigateUp = {
            navController.navigateUp()
        }
    )
}

@Composable
private fun CreateCurrencyScreen(
    state: CreateCurrencyScreenState,
    events: Flow<CreateCurrencyScreenEvent>,
    onAction: (action: CreateCurrencyScreenAction) -> Unit,
    navigateUp: () -> Unit
) {
    var sign by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    val currencyTypes by remember {
        mutableStateOf(CurrencyType.entries)
    }
    var selectedType by rememberSaveable(currencyTypes) {
        mutableStateOf(currencyTypes.first())
    }
    val c = LocalContext.current

    ObserveAsEvent(flow = events) { event ->
        when (event) {
            CreateCurrencyScreenEvent.OnCurrencyCreated -> {
                navigateUp()
            }

            is CreateCurrencyScreenEvent.OnCurrencyCreationFailed -> {
                Toast.makeText(c, event.reason, Toast.LENGTH_LONG).show()
            }
        }
    }

    MainScreensLayout {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = sign,
                    onValueChange = { text ->
                        sign = text
                    },
                    label = {
                        Text(
                            text = "Sign",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Type sign here",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { text ->
                        name = text
                    },
                    label = {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Type name here",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                SelectableTextField(
                    selectedValueStr = selectedType.formattedName(),
                    label = "Currency type",
                    options = currencyTypes,
                    onValueChange = { newValue ->
                        selectedType = newValue
                    },
                    optionsBackgroundColor = ColorLightContainers,
                    optionContent = { option ->
                        Text(
                            text = option.formattedName(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )

                Button(
                    enabled = !state.creationInProgress && sign.isNotEmpty() && name.isNotEmpty(),
                    onClick = {
                        onAction(
                            CreateCurrencyScreenAction.OnCreateClicked(
                                sign = sign,
                                name = name,
                                type = selectedType
                            )
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .widthIn(min = 220.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if (state.creationInProgress) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CreateCurrencyScreenPreview() {
    MyFinancesTheme {
        CreateCurrencyScreen(
            state = CreateCurrencyScreenState(),
            events = flowOf(),
            onAction = {},
            navigateUp = {}
        )
    }
}