package nazar.hr.myfinances.presentation.features.settings.settings_menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout
import nazar.hr.myfinances.presentation.common.components.safeSingleClick
import nazar.hr.myfinances.presentation.common.theme.ColorLightContainers
import nazar.hr.myfinances.presentation.common.theme.ColorPrimaryDark
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

/**
 * Created by nazar at 05.08.2024
 */
@Composable
fun SettingsMenuItem(
    text: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(ColorLightContainers)
            .safeSingleClick(
                indication = rememberRipple(
                    color = ColorPrimaryDark
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClicked()
            }
//            .clickable(
//                indication = rememberRipple(
//                    color = ColorPrimaryDark
//                ),
//                interactionSource = remember { MutableInteractionSource() }
//            ) {
//            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
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
        MainScreensLayout {
            SettingsMenuItem(
                text = "Currencies",
                modifier = Modifier.fillMaxWidth(),
                onClicked = {}
            )
        }

    }
}