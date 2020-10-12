package com.ajithvgiri.anxial.data.repository

import com.ajithvgiri.anxial.data.Result
import com.ajithvgiri.anxial.data.datasource.HomeDataSource
import com.ajithvgiri.anxial.data.model.BrandResponse
import com.ajithvgiri.anxial.data.model.ProductResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(private val dataSource: HomeDataSource) {

    fun brands(result: (Result<BrandResponse>) -> Unit) = dataSource.brands(result)

    fun products(result: (Result<ProductResponse>) -> Unit) = dataSource.products(result)
}