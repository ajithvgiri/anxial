package com.ajithvgiri.anxial.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ajithvgiri.anxial.utils.PreferenceHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun providePreferenceHelper(): SharedPreferences = PreferenceHelper.prefs(application.applicationContext)

}