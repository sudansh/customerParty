package com.sudansh.party

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.sudansh.party.ui.main.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {


	@Rule
	@JvmField
	var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

	@Test
	fun testButtonClickShowsItems() {
		// perform a click on the button
		onView(withId(R.id.btnFetchCustomers)).perform(click())
		onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

		val list = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.rv_customers)
		val size = list.adapter?.itemCount ?: 0
		onView(withId(R.id.rv_customers))
			.inRoot(
				RootMatchers.withDecorView(
					Matchers.`is`(mActivityTestRule.activity.window.decorView)
				)
			)
			.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(size - 1))
	}
}