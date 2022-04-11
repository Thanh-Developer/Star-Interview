package com.interview.star.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.interview.star.ui.base.BaseFragmentTest
import com.interview.star.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest : BaseFragmentTest() {

    /**
     *  Check recyclerview is displayed
     */
    @Test
    fun recyclerView_isVisible() {
        onView(withId(R.id.rvUser)).check(matches(isDisplayed()))
    }

    /**
     *  Check open new fragment
     */
    @Test
    fun clickItem_isDetailFragmentVisible() {
        // Swipe first item in list
        onView(withId(R.id.rvUser))
            .perform(
                actionOnItemAtPosition<HomeAdapter.TaskViewHolder>(
                    POSITION_SWIPE,
                    swipeLeft()
                )
            )

        // Confirm nav to AddNoteActivityVisible and display title
        onView(withId(R.id.imgBack)).check(matches(isDisplayed()))
    }

    /**
     *  Check scroll to position
     */
    @Test
    fun ableScrollToPosition() {
        onView(withId(R.id.rvUser)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                POSITION_SCROLL
            )
        )
    }

    /**
     *  Check search hint is correct
     */
    @Test
    fun editSearchHint_isCorrect() {
        onView(withId(R.id.edtSearch)).check(matches(withHint(R.string.hint_search)))
    }

    /**
     *  Check search case
     */
    @Test
    fun searchShouldHaveData() {
        // Put data to search
        onView(withId(R.id.edtSearch)).perform(typeText("Thanh-Developer"))

        // Click done in keyboard
        onView(withId(R.id.edtSearch)).perform(pressImeActionButton())
    }

}