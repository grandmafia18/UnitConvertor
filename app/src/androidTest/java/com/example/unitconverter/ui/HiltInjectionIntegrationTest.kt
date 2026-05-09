package com.example.unitconverter.ui

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.unitconverter.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HiltInjectionIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test fun ui_uses_fake_engine_binding() {
        hiltRule.inject()

        composeRule.onNodeWithTag(TestTags.INPUT).performTextClearance()
        composeRule.onNodeWithTag(TestTags.INPUT).performTextInput("10")
        composeRule.onNodeWithTag(TestTags.CONVERT).performClick()

        // Because FakeEngine always returns 123.456, UI result must contain the formatted value.
        composeRule.onNodeWithTag(TestTags.RESULT).assertTextContains("123.46", substring = true)
    }
}
