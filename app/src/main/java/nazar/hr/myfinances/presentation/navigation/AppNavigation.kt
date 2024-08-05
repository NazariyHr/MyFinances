package nazar.hr.myfinances.presentation.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.features.settings.currencies.CurrenciesScreenRoot
import nazar.hr.myfinances.presentation.features.settings.settings_menu.SettingsMenuScreenRoot
import nazar.hr.myfinances.presentation.features.wallets.WalletsScreenRoot
import nazar.hr.myfinances.presentation.navigation.components.BottomNavigationBar
import kotlin.enums.EnumEntries
import kotlin.reflect.KType

@Composable
fun AppNavigationRoot(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigationBarItems = BottomNavigationItem.entries
    val navigationBarItemsNames =
        navigationBarItems.map { it.screenToNavigate::class.qualifiedName }
    val screenRoutesWithoutNavigationBar =
        listOf(
            Screen.TestScreenWithoutNavBar
        ).map { it::class.qualifiedName }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember {
        derivedStateOf {
            backStackEntry?.destination?.route
        }
    }
    val rootRoute by remember {
        derivedStateOf {
            backStackEntry?.destination?.getRootRoute()
        }
    }
    val showNavigationBar by remember {
        derivedStateOf {
            rootRoute != null &&
                    navigationBarItemsNames.contains(rootRoute) &&
                    !screenRoutesWithoutNavigationBar.contains(currentRoute)
        }
    }
    val selectedItemIndex by remember {
        derivedStateOf {
            navigationBarItemsNames
                .indexOf(rootRoute)
                .let { index -> if (index >= 0) index else 0 }
        }
    }

    AppNavigation(
        showNavigationBar,
        navigationBarItems,
        selectedItemIndex,
        navController,
        modifier
    )
}

@Composable
fun AppNavigation(
    showNavigationBar: Boolean,
    navigationBarItems: EnumEntries<BottomNavigationItem>,
    selectedItemIndex: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenGroups.WalletsScreensGroup,
            modifier = Modifier.weight(1f)
        ) {
            navigation<ScreenGroups.WalletsScreensGroup>(
                startDestination = Screen.Wallets
            ) {
                composableNoTransition<Screen.Wallets> {
                    WalletsScreenRoot(navController)
                }
            }
            navigation<ScreenGroups.SettingsScreensGroup>(
                startDestination = Screen.SettingsMenu
            ) {
                composableNoTransition<Screen.SettingsMenu> {
                    SettingsMenuScreenRoot(navController)
                }
                composableNoTransition<Screen.Currencies> {
                    CurrenciesScreenRoot(navController)
                }
            }
        }
        AnimatedVisibility(visible = showNavigationBar) {
            BottomNavigationBar(
                navigationBarItems,
                selectedItemIndex,
                { screen, index ->
                    if (selectedItemIndex != index) {
                        navController.navigate(screen)
                    } else {
                        val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
                            popUpTo(screen)
                        }
                        navController.navigate(
                            screen, navOptionsBuilder
                        )
                    }
                },
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun AppNavigationPreview(modifier: Modifier = Modifier) {
    MyFinancesTheme {
        AppNavigation(
            showNavigationBar = true,
            navigationBarItems = BottomNavigationItem.entries,
            selectedItemIndex = 0,
            navController = rememberNavController()
        )
    }
}

inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}

fun NavDestination.getRootRoute(): String? {
    return if (parent?.route == null) {
        this.route
    } else {
        parent!!.getRootRoute() ?: parent?.route
    }
}

inline fun <reified T : Any> NavGraphBuilder.composableNoTransition(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        content = content
    )
}
