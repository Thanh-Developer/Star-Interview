package com.interview.star.ui.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.interview.star.ui.base.BaseFragmentTest
import com.interview.star.R
import com.interview.star.ui.home.HomeAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailFragmentTest : BaseFragmentTest() {

    @Before
    fun setUp() {
        onView(withId(R.id.rvUser))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.TaskViewHolder>(
                    POSITION_SWIPE,
                    swipeLeft()
                )
            )
    }

    /**
     *  Check back icon is displayed
     */
    @Test
    fun imgBack_isVisible() {
        onView(withId(R.id.imgBack)).check(matches(isDisplayed()))
    }

    /**
     *  Check web view is displayed
     */
    @Test
    fun webView_isVisible() {
        onView(withId(R.id.webViewProfile)).check(matches(isDisplayed()))
    }

    /**
     *  Check back icon is working
     */
    @Test
    fun imgBack_isClick() {
        // Click to back icon
        onView(withId(R.id.imgBack)).perform(click())

        // Check search view is displayed
        onView(withId(R.id.edtSearch)).check(matches(isDisplayed()))
    }
}