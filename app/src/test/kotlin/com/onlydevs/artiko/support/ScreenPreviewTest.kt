package com.onlydevs.artiko.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

private const val PHONE_QUALIFIERS = "w411dp-h891dp-xxhdpi"

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [36], qualifiers = PHONE_QUALIFIERS)
abstract class ScreenPreviewTest {
    @get:Rule
    val composeRule = createComposeRule()

    protected fun capture(screenName: String, content: @Composable () -> Unit) {
        composeRule.setContent { ArtikoTheme(content = content) }
        composeRule.onRoot().captureRoboImage("build/screenshots/$screenName.png")
    }
}
