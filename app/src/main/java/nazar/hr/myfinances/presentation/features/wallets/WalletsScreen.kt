package nazar.hr.myfinances.presentation.features.wallets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

@Composable
fun WalletsScreenRoot(
    navController: NavController,
    viewModel: WalletsViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    WalletsScreen(
        state = state
    )
}

@Composable
private fun WalletsScreen(
    state: WalletsScreenState
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
private fun WalletsScreenPreview() {
    MyFinancesTheme {
        WalletsScreen(
            state = WalletsScreenState()
        )
    }
}