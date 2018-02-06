package com.lucascampos.testing

import android.support.test.InstrumentationRegistry
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule
import org.junit.Before
import org.junit.Rule

/**
 * @author Lucas Campos
 */
abstract class BaseInstrumentedTest {

    @Rule
    @JvmField
    var serverRule = InstrumentedTestRequestMatcherRule()

    @Before
    fun setUp() {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as AppTest
        app.baseUrl = serverRule.url("/").toString()
    }
}