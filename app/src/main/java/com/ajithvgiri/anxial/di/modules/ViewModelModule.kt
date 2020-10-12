package com.ajithvgiri.anxial.di.modules

import androidx.lifecycle.ViewModel
import com.ajithvgiri.anxial.di.factory.ViewModelKey
import com.ajithvgiri.anxial.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

}