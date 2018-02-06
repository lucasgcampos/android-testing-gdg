package com.lucascampos.testing

import android.app.Application

/**
 * @author Lucas Campos
 */
open class App : Application() {

    open fun getUrl() = BuildConfig.BASE_URL

}