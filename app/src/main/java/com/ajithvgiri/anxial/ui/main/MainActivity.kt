package com.ajithvgiri.anxial.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.ajithvgiri.anxial.R
import com.ajithvgiri.anxial.data.model.BrandResponse
import com.ajithvgiri.anxial.ui.base.BaseActivity
import com.ajithvgiri.anxial.utils.showError

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        mainViewModel.brandResult.observe(this, Observer {
            val brandResult = it ?: return@Observer

            progressBar.visibility = View.GONE

            if (brandResult.error != null) {
                showError(brandResult.error)
            }
            if (brandResult.success != null) {
                updateUiWithBrand(brandResult.success)
            }
        })


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun updateUiWithBrand(brandResponse: BrandResponse) {
        brandResponse.data.let { listOfBrands ->
            val sectionsPagerAdapter = SectionsPagerAdapter(listOfBrands, supportFragmentManager)
            val viewPager: ViewPager = findViewById(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.visibility = View.VISIBLE
            viewPager.visibility = View.VISIBLE
            tabs.setupWithViewPager(viewPager)
        }

    }
}