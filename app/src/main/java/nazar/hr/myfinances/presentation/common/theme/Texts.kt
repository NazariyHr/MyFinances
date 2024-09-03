package nazar.hr.myfinances.presentation.common.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nazar.hr.myfinances.R
import nazar.hr.myfinances.presentation.common.components.MainScreensLayout

val FontFamilyInconsolataRegular = FontFamily(Font(R.font.inconsolata_regular))
val FontFamilyInconsolataMedium = FontFamily(Font(R.font.inconsolata_medium))
val FontFamilyInconsolataLight = FontFamily(Font(R.font.inconsolata_light))
val FontFamilyInconsolataExtraLight = FontFamily(Font(R.font.inconsolata_extra_light))
val FontFamilyInconsolataBold = FontFamily(Font(R.font.inconsolata_bold))
val FontFamilyInconsolataSemiBold = FontFamily(Font(R.font.inconsolata_semi_bold))
val FontFamilyInconsolataExtraBold = FontFamily(Font(R.font.inconsolata_extra_bold))
val FontFamilyInconsolataBlack = FontFamily(Font(R.font.inconsolata_black))

val FontFamilyInconsolataCondensedRegular = FontFamily(Font(R.font.inconsolata_condensed_regular))
val FontFamilyInconsolataCondensedMedium = FontFamily(Font(R.font.inconsolata_condensed_medium))
val FontFamilyInconsolataCondensedLight = FontFamily(Font(R.font.inconsolata_condensed_light))
val FontFamilyInconsolataCondensedExtraLight =
    FontFamily(Font(R.font.inconsolata_condensed_extra_light))
val FontFamilyInconsolataCondensedBold = FontFamily(Font(R.font.inconsolata_condensed_bold))
val FontFamilyInconsolataCondensedSemiBold =
    FontFamily(Font(R.font.inconsolata_condensed_semi_bold))
val FontFamilyInconsolataCondensedExtraBold =
    FontFamily(Font(R.font.inconsolata_condensed_extra_bold))
val FontFamilyInconsolataCondensedBlack = FontFamily(Font(R.font.inconsolata_condensed_black))

val TextColorDefault = Color(10, 31, 11, 255)
val TextColorForHeadLinesAndDisplay = Color(30, 116, 34, 255)

/**
 * Typography for application theme
 */
val Typography = run {
    val t = Typography()
    Typography(
        displayLarge = t.displayLarge.copy(
            fontFamily = FontFamilyInconsolataBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        displayMedium = t.displayMedium.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        displaySmall = t.displaySmall.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        headlineLarge = t.headlineLarge.copy(
            fontFamily = FontFamilyInconsolataBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        headlineMedium = t.headlineMedium.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        headlineSmall = t.headlineSmall.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorForHeadLinesAndDisplay
        ),
        titleLarge = t.titleLarge.copy(
            fontFamily = FontFamilyInconsolataBold,
            color = TextColorDefault
        ),
        titleMedium = t.titleMedium.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorDefault
        ),
        titleSmall = t.titleSmall.copy(
            fontFamily = FontFamilyInconsolataRegular,
            color = TextColorDefault
        ),
        bodyLarge = t.bodyLarge.copy(
            fontFamily = FontFamilyInconsolataBold,
            color = TextColorDefault
        ),
        bodyMedium = t.bodyMedium.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorDefault
        ),
        bodySmall = t.bodySmall.copy(
            fontFamily = FontFamilyInconsolataRegular,
            color = TextColorDefault
        ),
        labelLarge = t.labelLarge.copy(
            fontFamily = FontFamilyInconsolataBold,
            color = TextColorDefault
        ),
        labelMedium = t.labelMedium.copy(
            fontFamily = FontFamilyInconsolataSemiBold,
            color = TextColorDefault
        ),
        labelSmall = t.labelSmall.copy(
            fontFamily = FontFamilyInconsolataRegular,
            color = TextColorDefault
        )
    )
}

@Preview
@Composable
private fun FontsPreviewOnMainBackgroundColor() {
    MyFinancesTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBg)
        ) {
            MainScreensLayout {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Display Large",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "Display Medium",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Display Small",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "Headline Large",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "Headline Medium",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Headline Small",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Title Large",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Title Medium",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Title Small",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Body Large",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Body Medium",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Body Small",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Label Large",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Label Medium",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Label Small",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FontsPreviewInLightContainers() {
    MyFinancesTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBg)
        ) {
            MainScreensLayout {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .background(
                           color =  ColorLightContainers,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Display Large",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "Display Medium",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Display Small",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "Headline Large",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "Headline Medium",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Headline Small",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Title Large",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Title Medium",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Title Small",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Body Large",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Body Medium",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Body Small",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Label Large",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Label Medium",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Label Small",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FontsPreviewInDarkContainers() {
    MyFinancesTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorMainBg)
        ) {
            MainScreensLayout {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(ColorDarkContainers)
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Display Large",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "Display Medium",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Display Small",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "Headline Large",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "Headline Medium",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Headline Small",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Title Large",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Title Medium",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Title Small",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Body Large",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Body Medium",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Body Small",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Label Large",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Label Medium",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "Label Small",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}