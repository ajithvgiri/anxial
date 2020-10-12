package com.ajithvgiri.anxial.ui.main

import com.ajithvgiri.anxial.data.model.ProductResponse

/**
 * Brand result : success (brand details) or error message.
 */
data class ProductResult(
    val success: ProductResponse? = null,
    val error: String? = null
)