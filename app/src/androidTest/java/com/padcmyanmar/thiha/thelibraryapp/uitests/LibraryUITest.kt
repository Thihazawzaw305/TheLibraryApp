package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import com.padcmyanmar.thiha.thelibraryapp.viewholders.FilterChipViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class LibraryUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun checkCategoryAndBookRecyclerView() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvFilterChip))
            .check(matches(ViewMatchers.isDisplayed()))


        Espresso.onView(withId(R.id.tvSorting))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.btnGridView))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkGridRecyclerView() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.btnGridView))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.gridBottomSheet))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rBtnSmallGrid))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvSmallGrid))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.btnGridView))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rBtnLargeGrid))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvLargeGrid))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkShowThreeCategoryChip() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvFilterChip))
            .check(matches(ViewMatchers.hasDescendant(withText("Hardcover Fiction"))))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(2))
            .check(matches(ViewMatchers.hasDescendant(withText("Hardcover Nonfiction"))))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(3))
            .check(matches(ViewMatchers.hasDescendant(withText("Paperback Nonfiction"))))
    }


    @Test
    fun checkShowThreeBook() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        // 1st book
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(0, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_1_BOOK_1))))

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_1_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_2_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_3_BOOK_1))))

        // 2nt book
        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(1))
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(1, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_2_BOOK_1))))

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_1_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_2_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_3_BOOK_1))))

        // 3rd book
        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(2))
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(2, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_3_BOOK_1))))

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(matches(ViewMatchers.hasDescendant(withText(List_1_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_2_BOOK_1))))
            .check(matches(ViewMatchers.hasDescendant(withText(List_3_BOOK_1))))
    }

    @Test
    fun checkSorting() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Thread.sleep(2000L)


        // Sort By Author
        Espresso.onView(withId(R.id.tvSorting))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rBtnAuthor))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(0, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_1_BOOK_1)))

        // Sort By Recently Opened
        Espresso.onView(withId(R.id.tvSorting))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rBtnRecentlyOpened))
            .perform(ViewActions.click())
        Thread.sleep(1000L)


        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(0, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_1_BOOK_1)))
      //   Sort By Title
        Espresso.onView(withId(R.id.tvSorting))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rBtnTitle))
            .perform(ViewActions.click())
        Thread.sleep(1000L)


        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(0, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_1_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(matches(withText(List_3_BOOK_1)))


    }

}
