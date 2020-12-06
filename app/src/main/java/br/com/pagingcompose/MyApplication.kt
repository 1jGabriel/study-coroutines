package br.com.pagingcompose

import android.app.Application
import br.com.pagingcompose.di.appModule
import br.com.pagingcompose.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level =Level.NONE)
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}
