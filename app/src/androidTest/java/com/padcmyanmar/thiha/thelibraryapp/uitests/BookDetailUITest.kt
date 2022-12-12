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
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import com.padcmyanmar.thiha.thelibraryapp.viewholders.EbookViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BookDetailUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun onTapBook_checkBookDetailUI(){
        Espresso.onView(ViewMatchers.withText("Hardcover Fiction"))
            .perform(NestedScrollView())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
                .viewHolderViewAtPosition(0, R.id.rvEbookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<EbookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .check(matches(ViewMatchers.isDisplayed()))


        Espresso.onView(withId(R.id.tvBookTitleFromBookDetails))
            .check(matches(ViewMatchers.withText("DESERT STAR")))

        Espresso.onView(withId(R.id.tvAuthorFromBooKDetails))
            .check(matches(ViewMatchers.withText("Michael Connelly")))

        Espresso.onView(withId(R.id.tvAboutThisBookFromBookDetail))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tvRatingAndReview))
            .check(matches(ViewMatchers.isDisplayed()))
    }}