package com.ajithvgiri.anxial.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajithvgiri.anxial.di.factory.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}