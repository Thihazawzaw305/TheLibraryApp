package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import com.padcmyanmar.thiha.thelibraryapp.viewholders.FilterChipViewHolder
import com.padcmyanmar.thiha.thelibraryapp.viewholders.ShelfViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class ShelfDetailUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun a_checkShelfDetail(){
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0,
                    ViewActions.click()
                ))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvShelfNameFromShelfDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tvBookCountFromShelfDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rvFilterChip))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun b_checkGridRecyclerView() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0,
                    ViewActions.click()
                ))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnGridView))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.gridBottomSheet))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rBtnSmallGrid))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvSmallGrid))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.btnGridView))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rBtnLargeGrid))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvLargeGrid))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun c_checkSorting() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0,
                    ViewActions.click()
                ))
        Thread.sleep(1000L)


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
            .check(ViewAssertions.matches(ViewMatchers.withText(List_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_1_BOOK_1)))

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
            .check(ViewAssertions.matches(ViewMatchers.withText(List_3_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_1_BOOK_1)))
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
            .check(ViewAssertions.matches(ViewMatchers.withText(List_1_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(1, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_2_BOOK_1)))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvListFromYourBooks)
                .viewHolderViewAtPosition(2, R.id.tvBookTitleFromListView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText(List_3_BOOK_1)))


    }

    @Test
    fun d_checkShowThreeBook() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0,
                    ViewActions.click()
                ))
        Thread.sleep(1000L)



        // 1st book
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(0, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_2_BOOK_1
                        )
                    )
                )
            )

        Thread.sleep(1000L)

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_1_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_2_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_3_BOOK_1
                        )
                    )
                )
            )

        // 2nt book
        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(1))
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(1, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_3_BOOK_1
                        )
                    )
                )
            )

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_1_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_2_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_3_BOOK_1
                        )
                    )
                )
            )

        // 3rd book
        Espresso.onView(withId(R.id.rvFilterChip))
            .perform(RecyclerViewActions.scrollToPosition<FilterChipViewHolder>(2))
        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvFilterChip)
                .viewHolderViewAtPosition(2, R.id.tvCategoryName)
        ).perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_1_BOOK_1
                        )
                    )
                )
            )

        // check show clear button and clear category
        Espresso.onView(withId(R.id.btnClear))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvListFromYourBooks))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_1_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_2_BOOK_1
                        )
                    )
                )
            )
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            List_3_BOOK_1
                        )
                    )
                )
            )
    }
    @Test
    fun e_checkDeleteShelf(){
        // navigate to shelf detail
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ShelfViewHolder>(0,
                ViewActions.click()
            ))
        Thread.sleep(1000L)

        // delete
        Espresso.onView(withId(R.id.btnMoreFromShelfDetail))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.layoutDeleteShelf))
            .perform(ViewActions.click())
        Thread.sleep(1000L)


    }



}