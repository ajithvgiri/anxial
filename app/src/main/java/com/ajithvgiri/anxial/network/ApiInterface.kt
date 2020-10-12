package com.ajithvgiri.anxial.network


import com.ajithvgiri.anxial.data.model.BrandResponse
import com.ajithvgiri.anxial.data.model.LoginRequest
import com.ajithvgiri.anxial.data.model.LoginResponse
import com.ajithvgiri.anxial.data.model.ProductResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("app/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("app/get-brand-types")
    fun brands(@Header("Authorization") auth: String): Call<BrandResponse>

    @GET("app/get-products")
    fun products(
        @Header("Authorization") auth: String,
        @Query("page") page: Int = 5,
        @Query("sort_by") sort_by: Int = 1
    ): Call<ProductResponse>

}