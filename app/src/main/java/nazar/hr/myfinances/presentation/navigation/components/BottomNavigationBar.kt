package nazar.hr.myfinances.presentation.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nazar.hr.myfinances.presentation.common.theme.ColorDarkContainers
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.navigation.BottomNavigationItem
import nazar.hr.myfinances.presentation.navigation.Screen
import kotlin.enums.EnumEntries

@Composable
fun BottomNavigationBar(
    navigationBarItems: EnumEntries<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Screen, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .windowInsetsPadding(NavigationBarDefaults.windowInsets)
            .fillMaxWidth()
            .background(ColorDarkContainers)
    ) {
        navigationBarItems.forEachIndexed { index, item ->
            NavBarItem(
                item.icon,
                isSelected = selectedItemIndex == index,
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ) {
                        onItemClick(item.screenToNavigate, index)
                    }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview(modifier: Modifier = Modifier) {
    MyFinancesTheme {
        BottomNavigationBar(
            BottomNavigationItem.entries,
            0,
            { s, i -> },
            modifier = Modifier.wrapContentHeight()
        )
    }
}