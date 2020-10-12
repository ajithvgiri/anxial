package com.ajithvgiri.anxial.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ajithvgiri.anxial.R
import com.ajithvgiri.anxial.adapter.OnItemClickListener
import com.ajithvgiri.anxial.adapter.ProductsAdapter
import com.ajithvgiri.anxial.data.model.ProductResponse
import com.ajithvgiri.anxial.data.model.Products
import com.ajithvgiri.anxial.ui.base.BaseFragment
import com.ajithvgiri.anxial.ui.product.ProductDetails
import com.ajithvgiri.anxial.utils.showError
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A products fragment containing a simple view.
 */
class ProductsFragment : BaseFragment(), OnItemClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainViewModel.getAllProducts()
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ProductsFragment {
            return ProductsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        mainViewModel.productResult.observe(viewLifecycleOwner, Observer {
            val productResult = it ?: return@Observer

            progressBar.visibility = View.GONE

            if (productResult.error != null) {
                context?.showError(productResult.error)
            }
            if (productResult.success != null) {
                updateUiWithProducts(productResult.success)
            }
        })
    }

    private fun updateUiWithProducts(productResponse: ProductResponse) {

        /*
         * Setup the adapter class for the RecyclerView
         * */
        val filterBrandType = arguments?.get(ARG_SECTION_NUMBER)
        val productsAdapter = if (filterBrandType != 0) {
            ProductsAdapter(
                productResponse.data.filter { it.brand_type?.id == filterBrandType },
                this
            )
        } else {
            ProductsAdapter(productResponse.data, this)
        }
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productsAdapter
            productsAdapter.notifyDataSetChanged()
        }

    }

    override fun onItemClickListener(products: Products) {
        val intent = Intent(requireActivity(), ProductDetails::class.java)
        intent.putExtra("products", products)
        startActivity(intent)
    }
}