package nazar.hr.myfinances.presentation.features.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

/**
 * Created by nazar at 05.08.2024
 */
@Composable
fun SettingsMenuItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun SettingsMenuItemPreview() {
    MyFinancesTheme {
        SettingsMenuItem(
            text = "Currencies",
            modifier = Modifier.fillMaxWidth()
        )
    }
}