package com.assignment

import android.support.multidex.MultiDexApplication
import com.assignment.di.appcomponents.appComponent
import org.koin.core.context.GlobalContext.startKoin


class AssignemntApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AssignemntApplication

    }
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appComponent)

        }
    }
}