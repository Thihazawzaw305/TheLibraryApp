package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)

class YourShelfUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun a_checkShowEmptyShelfView() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.btnCreateNew))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun b_checkCreateShelfAndShowCreatedShelf() {
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.btnCreateNew))
            .perform(ViewActions.click())

        Thread.sleep(1000L)
        Espresso.onView(withId(R.id.tvCancel))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.ivCheck))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.etShelfName)).perform(
            ViewActions.typeText("Fav"),
            ViewActions.pressImeActionButton()
        )
        Thread.sleep(1000L)


        Espresso.onView(withId(R.id.rvShelves))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            "Fav"
                        )
                    )
                )
            )

    }

    @Test
    fun c_checkAddThreeBook(){
        Espresso.onView(withId(R.id.action_library))
            .perform(ViewActions.click())

        // add first book
        Thread.sleep(1000L)
        Espresso.onView(RecyclerViewMatcher(R.id.rvListFromYourBooks).viewHolderViewAtPosition(1,R.id.btnMoreFromListView))
            .perform(ViewActions.click())


        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        // add second book
        Espresso.onView(RecyclerViewMatcher(R.id.rvListFromYourBooks).viewHolderViewAtPosition(2,R.id.btnMoreFromListView))
            .perform(ViewActions.click())


        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(ViewActions.click())
        Thread.sleep(1000L)


        // add third book
        Espresso.onView(RecyclerViewMatcher(R.id.rvListFromYourBooks).viewHolderViewAtPosition(0,R.id.btnMoreFromListView))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.layoutAddToShelf))
            .perform(ViewActions.click())
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.tvAddToShelfTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvAddToShelf).viewHolderViewAtPosition(0,R.id.cbAddToShelf))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.ivAddToShelSave))
            .perform(ViewActions.click())

        // check book count
        Espresso.onView(withId(R.id.tabLayoutFromLibrary))
            .perform(selectTabAtPosition(1))
        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.rvShelves))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            "Fav"
                        )
                    )
                )
            )

    }




}