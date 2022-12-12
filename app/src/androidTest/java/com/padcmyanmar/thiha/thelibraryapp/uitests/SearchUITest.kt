package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import com.padcmyanmar.thiha.thelibraryapp.viewholders.SearchListViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class SearchUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }
    @Test
    fun checkSearch(){
        Espresso.onView(withId(R.id.flSearchBar))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.ivSearchBack))
            .check(matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkSearchShowBookList() {
        Espresso.onView(withId(R.id.flSearchBar))
            .perform(ViewActions.click())
        Thread.sleep(1000L)


        Espresso.onView(withId(R.id.edtBookSearch))
            .perform(
                ViewActions.typeText("The"),
                ViewActions.closeSoftKeyboard()
            )

        Thread.sleep(3000L)

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("The Rotarian"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("The Crisis"))))

        Espresso.onView(withId(R.id.rvSearchBook))
            .perform(RecyclerViewActions.scrollToPosition<SearchListViewHolder>(7))
        Espresso.onView(withId(R.id.rvSearchBook))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("The Plague"))))
}}