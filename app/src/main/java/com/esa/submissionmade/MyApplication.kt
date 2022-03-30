package com.esa.submissionmade

import android.app.Application
import com.esa.submissionmade.core.di.databaseModule
import com.esa.submissionmade.core.di.networkModule
import com.esa.submissionmade.core.di.repositoryModule
import com.esa.submissionmade.di.useCaseModule
import com.esa.submissionmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}