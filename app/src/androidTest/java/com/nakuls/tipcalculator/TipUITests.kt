package com.nakuls.tipcalculator

import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nakuls.tipcalculator.ui.theme.TipCalculatorTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.text.NumberFormat

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nakuls.tipcalculator", appContext.packageName)
    }

    @Test
    fun calculate_20_percent_tip(){
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipLayout()
            }
        }
        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextReplacement("10")

        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextReplacement("20")

        val expectedTip = NumberFormat
            .getCurrencyInstance()
            .format(2)
        composeTestRule.onAllNodes(isRoot()).onFirst().printToLog("TAG")
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }
}

