package com.ajithvgiri.anxial.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.anxial.R
import com.ajithvgiri.anxial.data.model.Products
import kotlinx.android.synthetic.main.layout_products.view.*

class ProductsAdapter(
    var products: List<Products>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_products, parent, false)
        return ProductsViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ProductsViewHolder(itemView: View, var onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(
            itemView
        ) {
        fun bind(products: Products) {
            itemView.textViewName.text = products.name
            itemView.textViewQuantity.text = "Q: ${products.stock?.size}"
            products.variants?.let {
                if (it.isNotEmpty()) {
                    itemView.textViewPrice.text = "Rs: ${it[0].wholesale_price.toString()}"
                }
            }

            itemView.setOnClickListener {
                onItemClickListener.onItemClickListener(products)
            }
        }
    }

}