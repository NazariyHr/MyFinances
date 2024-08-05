package nazar.hr.myfinances.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.presentation.common.theme.ColorMainBg
import nazar.hr.myfinances.presentation.common.theme.DefaultPadding
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme

@Composable
fun MainScreensLayout(
    paddingTop: Dp = 0.dp,//DefaultPadding,
    paddingBottom: Dp = DefaultPadding,
    paddingStart: Dp = DefaultPadding,
    paddingEnd: Dp = DefaultPadding,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBg)
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Box(
                modifier = Modifier.padding(
                    top = paddingTop,
                    start = paddingStart,
                    end = paddingEnd,
                    bottom = paddingBottom
                ),
                content = content
            )
        }
    }
}

@Preview
@Composable
private fun MainScreensLayoutPreview() {
    MyFinancesTheme {
        MainScreensLayout {}
    }
}