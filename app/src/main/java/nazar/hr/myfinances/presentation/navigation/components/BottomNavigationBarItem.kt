package nazar.hr.myfinances.presentation.navigation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.R
import nazar.hr.myfinances.presentation.common.theme.ColorPrimaryDark
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

@Composable
fun NavBarItem(
    @DrawableRes icon: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val iconHeight = 24.dp
    val verticalPadding = 16.dp
    val horizontalPadding = 12.dp
    val highLightLineHeight = 5.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .height(iconHeight + verticalPadding + verticalPadding - (if (isSelected) highLightLineHeight else 0.dp))
                    .fillMaxWidth()
                    .then(
                        if (isSelected)
                            Modifier.background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        ColorPrimaryDark.copy(alpha = 0.05f),
                                        ColorPrimaryDark.copy(alpha = 0.5f)
                                    )
                                )
                            )
                        else Modifier
                    )
            )
            if (isSelected) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(highLightLineHeight)
                        .background(ColorPrimaryDark)
                )
            }
        }
        Icon(
            modifier = Modifier
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                .size(iconHeight)
                .align(Alignment.Center),
            painter = painterResource(id = icon),
            tint = Color.White,
            contentDescription = "icon"
        )
    }
}

@Preview
@Composable
private fun NavBarItemPreview() {
    MyFinancesTheme {
        NavBarItem(
            icon = R.drawable.ic_settings,
            isSelected = true,
            modifier = Modifier
        )
    }
}