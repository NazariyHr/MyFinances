package nazar.hr.myfinances.presentation.features.settings.settings_menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.features.settings.settings_menu.components.SettingsMenuItem
import nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model.SettingMenuItems
import nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model.SettingsMenuScreenState
import nazar.hr.myfinances.presentation.features.settings.settings_menu.ui_model.getTitle
import nazar.hr.myfinances.presentation.navigation.Screen

@Composable
fun SettingsMenuScreenRoot(
    navController: NavController,
    viewModel: SettingsMenuViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsMenuScreen(
        state = state,
        onMenuItemClicked = { settingMenuItem ->
            when (settingMenuItem) {
                SettingMenuItems.CURRENCIES -> {
                    navController.navigate(Screen.Currencies)
                }

                SettingMenuItems.CURRENCY_EXCHANGES -> {

                }
            }
        }
    )
}

@Composable
private fun SettingsMenuScreen(
    state: SettingsMenuScreenState,
    onMenuItemClicked: (SettingMenuItems) -> Unit
) {
    MainScreensLayout {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                text = state.title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            SettingMenuItems.entries.forEachIndexed { index, settingMenuItem ->
                val gap = 4.dp
                SettingsMenuItem(
                    text = settingMenuItem.getTitle(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = if (index == 0) 0.dp else gap / 2,
                            bottom = if (index == SettingMenuItems.entries.count() - 1) 0.dp else gap / 2
                        )
                        .clickable {
                            onMenuItemClicked(settingMenuItem)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MyFinancesTheme {
        SettingsMenuScreen(
            state = SettingsMenuScreenState(),
            onMenuItemClicked = {}
        )
    }
}