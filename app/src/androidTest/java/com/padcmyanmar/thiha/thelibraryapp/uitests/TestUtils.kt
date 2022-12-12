package com.padcmyanmar.thiha.thelibraryapp.uitests

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ListView
import android.widget.ScrollView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColor
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.tabs.TabLayout
import org.hamcrest.*
import org.hamcrest.core.AllOf.allOf

const val List_1_BOOK_1 = "DESERT STAR"
const val List_1_BOOK_2 = "THE BOYS FROM BILOXI"
const val List_1_BOOK_3 = "FAIRY TALE"

const val List_2_BOOK_1 = "FRIENDS, LOVERS, AND THE BIG TERRIBLE THING"
const val List_2_BOOK_2 = "SURRENDER"
const val List_2_BOOK_3 = "I'M GLAD MY MOM DIED"

const val List_3_BOOK_1 = "THE BODY KEEPS THE SCORE"
const val List_3_BOOK_2 = "BRAIDING SWEETGRASS"
const val List_3_BOOK_3 = "ALL ABOUT LOVE"

fun <T> first(matcher: Matcher<T>): Matcher<T> {
    return object :BaseMatcher<T>(){

        var isFirst : Boolean = true

        override fun describeTo(description: Description?) {
            description?.appendText(FIRST_ITEM_DESCRIPTION)
        }

        override fun matches(item: Any?): Boolean {
            if (isFirst && matcher.matches(item)) {
                isFirst = false
                return true
            }
            return false
        }
    }
}

const val FIRST_ITEM_DESCRIPTION = "Return The First Matching Item"

class NestedScrollView(scrolltoAction: ViewAction = ViewActions.scrollTo()) : ViewAction by scrolltoAction {
    override fun getConstraints(): Matcher<View> {
        return Matchers.allOf(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
            ViewMatchers.isDescendantOfA(
                Matchers.anyOf(ViewMatchers.isAssignableFrom(NestedScrollView::class.java),
                    ViewMatchers.isAssignableFrom(ScrollView::class.java),
                    ViewMatchers.isAssignableFrom(HorizontalScrollView::class.java),
                    ViewMatchers.isAssignableFrom(ListView::class.java))))
    }
}

fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
    }

    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)?.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }
}

fun selectTabAtPosition(tabIndex: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription() = "with tab at index $tabIndex"

        override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

        override fun perform(uiController: UiController, view: View) {
            val tabLayout = view as TabLayout
            val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                ?: throw PerformException.Builder()
                    .withCause(Throwable("No tab at index $tabIndex"))
                    .build()

            tabAtIndex.select()
        }
    }
}