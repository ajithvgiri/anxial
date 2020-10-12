package com.ajithvgiri.anxial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.anxial.R
import com.ajithvgiri.anxial.data.model.Variants
import kotlinx.android.synthetic.main.layout_variants.view.*

class VariantsAdapter(
    var variants: List<Variants>
) :
    RecyclerView.Adapter<VariantsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_variants, parent, false)
        return ProductsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return variants.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(variants[position])
    }

    class ProductsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(
            itemView
        ) {
        fun bind(variants: Variants) {
            itemView.textViewSku.text = variants.sku
            itemView.textViewPrice.text = "Price : ${variants.wholesale_price}"
        }
    }

}