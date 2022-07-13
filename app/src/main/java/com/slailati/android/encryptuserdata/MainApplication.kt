package com.slailati.android.encryptuserdata

import android.app.Application
import com.slailati.android.encryptuserdata.di.module.dataModule
import com.slailati.android.encryptuserdata.di.module.domainModule
import com.slailati.android.encryptuserdata.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dataModule,
                domainModule,
                viewModelModule
            ).androidContext(this@MainApplication)
        }
    }

}