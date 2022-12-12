package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
import com.padcmyanmar.thiha.thelibraryapp.R
import com.padcmyanmar.thiha.thelibraryapp.activities.MainActivityWithBottomNav
import com.padcmyanmar.thiha.thelibraryapp.viewholders.EbookViewHolder
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeUITest {
    private val activityTestRule =
        ActivityTestRule<MainActivityWithBottomNav>(MainActivityWithBottomNav::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun checkEmptyCarousel() {
        Espresso.onView(withId(R.id.flNoRecentBooks))
            .check(matches(isDisplayed()))
    }
    @Test
    fun checkThreeListBook() {
        Thread.sleep(2000L)

        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollView())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
                .viewHolderViewAtPosition(0, R.id.tvBookCategory)
        )
            .check(matches(withText("Hardcover Fiction")))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
                .viewHolderViewAtPosition(0, R.id.rvEbookList)
        )
            .check(matches(hasDescendant(withText(List_1_BOOK_1))))
            .check(matches(hasDescendant(withText(List_1_BOOK_2))))
            .check(matches(hasDescendant(withText(List_1_BOOK_3))))

        Thread.sleep(1000L)

        Espresso.onView(withText("Paperback Nonfiction"))
            .perform(NestedScrollView())

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
                .viewHolderViewAtPosition(1, R.id.tvBookCategory)
        )
            .check(matches(withText("Hardcover Nonfiction")))

        Espresso.onView(
            RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
                .viewHolderViewAtPosition(1, R.id.rvEbookList)
        )
            .check(matches(hasDescendant(withText(List_2_BOOK_1))))
            .check(matches(hasDescendant(withText(List_2_BOOK_2))))
            .check(matches(hasDescendant(withText(List_2_BOOK_3))))
    }

    @Test
    fun onTapBook_navigateToBookDetailAndAddedToCarousal() {
        Thread.sleep(2000L)

        // first book
        Espresso.onView(withText("Hardcover Nonfiction"))
            .perform(NestedScrollView  ())


        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
            .viewHolderViewAtPosition(0, R.id.rvEbookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<EbookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.carouselBook)
            .viewHolderViewAtPosition(0,R.id.tvBookTitleFromRecentBook)
        ).check(matches(withText(List_1_BOOK_1)))

        // second book
        Espresso.onView(withText("Paperback Nonfiction"))
            .perform(NestedScrollView())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
            .viewHolderViewAtPosition(1, R.id.rvEbookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<EbookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.carouselBook)
            .viewHolderViewAtPosition(0,R.id.tvBookTitleFromRecentBook)
        ).check(matches(withText(List_2_BOOK_1)))

        //third book
        Espresso.onView(withText("Picture Books"))
            .perform(NestedScrollView())

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.rvEbook)
            .viewHolderViewAtPosition(2, R.id.rvEbookList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<EbookViewHolder>(0,
                    ViewActions.click()
                ))

        Thread.sleep(1000L)

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.btnBackArrowFromBookDetails))
            .perform(ViewActions.click())

        Thread.sleep(1000L)

        Espresso.onView(RecyclerViewMatcher.recyclerViewWithId(R.id.carouselBook)
            .viewHolderViewAtPosition(0,R.id.tvBookTitleFromRecentBook)
        ).check(matches(withText(List_3_BOOK_1)))




    }









}

//@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
//@RunWith(AndroidJUnit4ClassRunner::class)
//class TestHomeUI {
//    @get:Rule
//    val rule = activityScenarioRule<BasedActivity>()
//
//    @After
//    fun cleanUp() {
//        rule.scenario.close()
//    }
//
//@Test
//fun launch_emptyCarouselView() {
//    Espresso.onView(withId(R.id.layoutEmptyRecentList))
//        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//}
//
//    @Test
//    fun launch_showThreeListThreeBooks() {
//
//        Thread.sleep(2000L)
//
//        Espresso.onView(ViewMatchers.withText("Hardcover Nonfiction"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(0, R.id.tvBookListTitle))
//            .check(ViewAssertions.matches(ViewMatchers.withText("Hardcover Fiction")))
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(0, R.id.rvBooksFromBookList))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_1_BOOK_1))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_1_BOOK_2))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_1_BOOK_3))))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(ViewMatchers.withText("Paperback Nonfiction"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(1, R.id.tvBookListTitle))
//            .check(ViewAssertions.matches(ViewMatchers.withText("Hardcover Nonfiction")))
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(1, R.id.rvBooksFromBookList))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_2_BOOK_1))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_2_BOOK_2))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_2_BOOK_3))))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(ViewMatchers.withText("Picture Books"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(2, R.id.tvBookListTitle))
//            .check(ViewAssertions.matches(ViewMatchers.withText("Paperback Nonfiction")))
//
//        Espresso.onView(
//            RecyclerViewMatcher.recyclerViewWithId(R.id.rvBookList)
//            .viewHolderViewAtPosition(2, R.id.rvBooksFromBookList))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_3_BOOK_1))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_3_BOOK_2))))
//            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(withText(CAT_3_BOOK_3))))
//
//        Thread.sleep(1000L)
//
//

//        Espresso.onView(withText("Paperback Nonfiction"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .check(matches(hasDescendant(withText("Hardcover Nonfiction"))))
//
//
//        Thread.sleep(2000L)
//
//        Espresso.onView(withText("Picture Books"))
//            .perform(NestedScrollViewExtension())
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .check(matches(hasDescendant(withText("Paperback Nonfiction"))))
//
//
//        Thread.sleep(2000L)

//
//        Espresso.onView(withId(R.id.rvBookList))
//            .perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(1))
//            .check(matches(hasDescendant(withText("Hardcover Nonfiction"))))
//
//        Thread.sleep(1000L)
//
//        Espresso.onView(withId(R.id.rvBookList))
//            .perform(RecyclerViewActions.scrollToPosition<BookListViewHolder>(2))
//            .check(matches(hasDescendant(withText("Paperback Nonfiction"))))
// //}