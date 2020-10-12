package com.ajithvgiri.anxial.di.modules


import com.ajithvgiri.anxial.ui.main.ProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePlaceholderFragment(): ProductsFragment

}