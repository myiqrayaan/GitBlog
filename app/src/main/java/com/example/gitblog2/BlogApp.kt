package com.example.gitblog2

import android.app.Application
import com.example.gitblog2.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BlogApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BlogApp)
            modules(koinModule)
        }
    }
}