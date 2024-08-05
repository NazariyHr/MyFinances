package nazar.hr.myfinances.presentation.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.theme.ColorMainBg
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

@Composable
fun SettingsScreenRoot(
    navController: NavController,
    viewModel: SettingsViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsScreen(
        state = state
    )
}

@Composable
private fun SettingsScreen(
    state: SettingsScreenState
) {
    MainScreensLayout {
        Box(
            modifier = Modifier
        ) {
            Text(text = state.title)
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MyFinancesTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBg)
        ) {
            SettingsScreen(
                state = SettingsScreenState()
            )
        }
    }
}