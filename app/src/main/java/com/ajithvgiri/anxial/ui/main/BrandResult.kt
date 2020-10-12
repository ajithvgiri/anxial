package com.ajithvgiri.anxial.ui.main

import com.ajithvgiri.anxial.data.model.BrandResponse

/**
 * Brand result : success (brand details) or error message.
 */
data class BrandResult(
    val success: BrandResponse? = null,
    val error: String? = null
)