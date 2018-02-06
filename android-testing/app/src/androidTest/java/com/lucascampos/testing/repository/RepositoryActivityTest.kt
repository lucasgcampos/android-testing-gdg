package com.lucascampos.testing.repository

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.requestmatcher.model.HttpMethod
import com.lucascampos.testing.BaseInstrumentedTest
import com.lucascampos.testing.R
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Lucas Campos
 */
@RunWith(AndroidJUnit4::class)
class RepositoryActivityTest : BaseInstrumentedTest() {

    @get:Rule
    var activityRule = ActivityTestRule(RepositoryActivity::class.java, true, false)

    @Test
    fun shouldListItems() {
        serverRule.addFixture(200, "repositories.json")
                .ifRequestMatches()
                .methodIs(HttpMethod.GET)
                .pathMatches(CoreMatchers.endsWith("/search/repositories"))

        activityRule.launchActivity(null)

        onView(withText("RxJava")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowError() {
        serverRule.addFixture(200, "empty.json")
                .ifRequestMatches()
                .methodIs(HttpMethod.GET)

        activityRule.launchActivity(null)

        onView(withId(R.id.empty_state)).check(matches(isDisplayed()))
    }
}