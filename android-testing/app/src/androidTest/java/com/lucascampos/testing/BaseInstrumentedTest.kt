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

    /**
     * Configurando nosso aplicativo de teste para utilizar nosso servidor localhost que foi
     * preparado pela biblioteca MockWebServer e não a api do github.
     */
    @Before
    fun setUp() {
        val app  = InstrumentationRegistry.getTargetContext().applicationContext as AppTest
        app.baseUrl = serverRule.url("/").toString()
    }
}

