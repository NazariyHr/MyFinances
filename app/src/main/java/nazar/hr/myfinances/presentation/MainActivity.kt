package nazar.hr.myfinances.presentation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import nazar.hr.myfinances.presentation.common.theme.ColorMainBg
import nazar.hr.myfinances.presentation.common.theme.MyFinancesTheme
import nazar.hr.myfinances.presentation.navigation.AppNavigationRoot

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.rgb(229, 246, 230),
                Color.rgb(229, 246, 230)
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
            this@MainActivity.window.navigationBarColor = Color.rgb(229, 246, 230)
        }
        setContent {
            MyFinancesTheme {
                Content(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = ColorMainBg)
                )
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    AppNavigationRoot(modifier)
}