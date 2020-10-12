package com.ajithvgiri.anxial.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajithvgiri.anxial.R
import com.ajithvgiri.anxial.adapter.VariantsAdapter
import com.ajithvgiri.anxial.data.model.Products
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.recyclerView

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Product Details"

        val products = intent.getSerializableExtra("products") as Products

        textViewName.text = products.name
        textViewProductDescription.text = products.brand?.name
        textViewVariant.text = products.variants?.size.toString()


        val variantsAdapter = products.variants?.let { VariantsAdapter(it) }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProductDetails)
            adapter = variantsAdapter
            variantsAdapter?.notifyDataSetChanged()
        }
    }
}