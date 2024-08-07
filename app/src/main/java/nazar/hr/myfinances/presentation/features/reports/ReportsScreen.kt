package nazar.hr.myfinances.presentation.features.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
fun ReportsScreenRoot(
    navController: NavController,
    viewModel: ReportsViewModel =
        hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ReportsScreen(
        state = state
    )
}

@Composable
private fun ReportsScreen(
    state: ReportsScreenState
) {
    MainScreensLayout {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = state.title,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Preview
@Composable
private fun ReportsScreenPreview() {
    MyFinancesTheme {
        ReportsScreen(
            state = ReportsScreenState()
        )
    }
}