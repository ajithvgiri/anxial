package com.ajithvgiri.anxial.di.modules

import com.ajithvgiri.anxial.ui.main.MainActivity
import com.ajithvgiri.anxial.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}