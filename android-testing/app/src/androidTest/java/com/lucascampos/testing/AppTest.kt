package com.lucascampos.testing

/**
 * @author Lucas Campos
 */
class AppTest : App() {
    lateinit var baseUrl: String

    override fun getUrl() = baseUrl
}