package com.ajithvgiri.anxial.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ajithvgiri.anxial.di.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
}