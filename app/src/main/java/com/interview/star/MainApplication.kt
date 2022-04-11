package com.interview.star

import android.app.Application
import com.interview.star.di.networkModule
import com.interview.star.di.repositoryModule
import com.interview.star.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}