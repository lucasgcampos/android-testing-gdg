package com.lucascampos.testing.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.lucascampos.testing.AppTest

/**
 * @author Lucas Campos
 */
class TestingRunner : AndroidJUnitRunner() {

    /**
     * Estamos configurando nosso apk de teste para ter como Application o nosso AppTest.class
     * que criamos no ambiente de teste.
     */
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }

}