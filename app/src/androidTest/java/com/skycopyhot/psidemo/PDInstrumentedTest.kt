package com.skycopyhot.psidemo

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.skycopyhot.psidemo.ui.MapsActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PDInstrumentedTest {

    @Rule
    @JvmField
    val mapsActivityRule = ActivityTestRule<MapsActivity>(MapsActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.skycopyhot.psidemo", appContext.packageName)
    }

    @Test
    fun clickMenu() {

        onView(withId(R.id.action_refresh))
                .check(matches(isDisplayed()))


        onView(withId(R.id.action_refresh))
                .perform(click())
    }
}
