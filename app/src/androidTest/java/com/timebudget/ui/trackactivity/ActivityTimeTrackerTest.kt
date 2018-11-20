package com.timebudget.ui.trackactivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import com.timebudget.R
import com.timebudget.ui.trackerlist.ActivityTimeTrackerList
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

@LargeTest
class ActivityTimeTrackerTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(ActivityTimeTrackerList::class.java)


    @Test
    fun test_Save_Time_Properly_Displayed() {
        onView(withId(R.id.bntAdd)).perform(click())

        onView(
            allOf(
                withId(R.id.save_time_container),
                withChild(withId(R.id.groupIntervalType))
            )
        ).check(
            matches(isDisplayed())
        )
    }

}