package com.ajithvgiri.anxial.di.component

import com.ajithvgiri.anxial.AnxialApplication
import com.ajithvgiri.anxial.di.modules.ActivityModule
import com.ajithvgiri.anxial.di.modules.AppModule
import com.ajithvgiri.anxial.di.modules.FragmentModule
import com.ajithvgiri.anxial.di.modules.ViewModelModule
import com.ajithvgiri.anxial.ui.base.BaseActivity
import com.ajithvgiri.anxial.ui.base.BaseFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {
    fun inject(anxialApplication: AnxialApplication)

    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
}