package nazar.hr.myfinances.presentation.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
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
import nazar.hr.myfinances.presentation.common.components.DoubleBackPressToExit
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.features.reports.ReportsScreenRoot
import nazar.hr.myfinances.presentation.features.settings.currencies.create_currency.CreateCurrencyScreenRoot
import nazar.hr.myfinances.presentation.features.settings.currencies.currencies_list.CurrenciesListScreenRoot
import nazar.hr.myfinances.presentation.features.settings.settings_menu.SettingsMenuScreenRoot
import nazar.hr.myfinances.presentation.features.wallets.WalletsScreenRoot
import nazar.hr.myfinances.presentation.navigation.components.BottomNavigationBar
import kotlin.enums.EnumEntries
import kotlin.reflect.KType

@Composable
fun AppNavigationRoot(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigationBarItems = BottomNavigationItems.entries
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

    val currentTabRootScreen by remember {
        derivedStateOf {
            navigationBarItems.find { it.screenToNavigate::class.qualifiedName == rootRoute }?.screenToNavigate
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

    DoubleBackPressToExit()
    AppNavigation(
        showNavigationBar,
        navigationBarItems,
        selectedItemIndex,
        navController,
        currentTabRootScreen,
        modifier
    )
}

@Composable
fun AppNavigation(
    showNavigationBar: Boolean,
    navigationBarItems: EnumEntries<BottomNavigationItems>,
    selectedItemIndex: Int,
    navController: NavHostController,
    currentTabRootScreen: ScreenGroup?,
    modifier: Modifier = Modifier
) {
    var openedScreens by rememberSaveable {
        mutableStateOf(listOf<Screen>())
    }

    Column(
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = ScreenGroup.WalletsScreensGroup,
            modifier = Modifier.weight(1f)
        ) {
            navigation<ScreenGroup.WalletsScreensGroup>(
                startDestination = ScreenGroup.WalletsScreensGroup.getFirstScreenOfGroup()
            ) {
                composable<Screen.Wallets> {
                    WalletsScreenRoot(navController)
                }
            }
            navigation<ScreenGroup.ReportsScreensGroup>(
                startDestination = ScreenGroup.ReportsScreensGroup.getFirstScreenOfGroup()
            ) {
                composable<Screen.ReportsMenu> {
                    ReportsScreenRoot(navController)
                }
            }
            navigation<ScreenGroup.SettingsScreensGroup>(
                startDestination = ScreenGroup.SettingsScreensGroup.getFirstScreenOfGroup()
            ) {
                composable<Screen.SettingsMenu> {
                    SettingsMenuScreenRoot(navController)

                    DisposableEffect(true) {
                        openedScreens = openedScreens + Screen.SettingsMenu
                        onDispose {
                            openedScreens = openedScreens.filterNot { it is Screen.SettingsMenu }
                        }
                    }
                }
                composable<Screen.CurrenciesList>(
                    enterTransition = {
                        if (!openedScreens.contains(Screen.CreateCurrency))
                            slideIn(
                                initialOffset = { fullSize ->
                                    IntOffset(0, fullSize.height)
                                }
                            )
                        else fadeIn()
                    },
                    exitTransition = {
                        if (!openedScreens.contains(Screen.CreateCurrency))
                            slideOut(
                                targetOffset = { fullSize ->
                                    IntOffset(0, fullSize.height + fullSize.height / 4)
                                }
                            )
                        else fadeOut()
                    }
                ) {
                    CurrenciesListScreenRoot(navController)

                    DisposableEffect(true) {
                        openedScreens = openedScreens + Screen.CurrenciesList
                        onDispose {
                            openedScreens = openedScreens.filterNot { it is Screen.CurrenciesList }
                        }
                    }
                }
                composable<Screen.CreateCurrency>(
                    enterTransition = {
                        slideIn(
                            initialOffset = { fullSize ->
                                IntOffset(0, fullSize.height)
                            }
                        )
                    },
                    exitTransition = {
                        slideOut(
                            targetOffset = { fullSize ->
                                IntOffset(0, fullSize.height + fullSize.height / 4)
                            }
                        )
                    }
                ) {
                    DisposableEffect(true) {
                        openedScreens = openedScreens + Screen.CreateCurrency
                        onDispose {
                            openedScreens = openedScreens.filterNot { it is Screen.CreateCurrency }
                        }
                    }
                    CreateCurrencyScreenRoot(navController)
                }
            }
        }

        AnimatedVisibility(visible = showNavigationBar) {
            BottomNavigationBar(
                navigationBarItems = navigationBarItems,
                selectedItemIndex = selectedItemIndex,
                onItemClick = { screen, index ->
                    if (selectedItemIndex != index) {
                        val navOptionsBuilder = navOptionsEachTabIsRootTop(currentTabRootScreen!!)
                        navController.navigate(screen, navOptionsBuilder)
                    } else {
                        val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
                            popUpTo(screen)
                        }
                        navController.navigate(
                            screen,
                            navOptionsBuilder
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
            navigationBarItems = BottomNavigationItems.entries,
            selectedItemIndex = 0,
            currentTabRootScreen = null,
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

/**
 * Use this nav options when navigating to root screen in some of bottom navigation tab.
 * In this case, when you press back button in root screen of any navigation tab, the application wil be closed.
 */
fun navOptionsEachTabIsRootTop(tabRootScreen: Screen): NavOptionsBuilder.() -> Unit {
    val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
        popUpTo(tabRootScreen) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    return navOptionsBuilder
}
