package com.cst.todotasks

import android.app.Application
import android.content.Context

/**
 * Created by nikolozakhvlediani on 12/24/20.
 */
class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    companion object {
        lateinit var instance: TodoApp
        private lateinit var context: Context
    }

    fun getContext() = context
}