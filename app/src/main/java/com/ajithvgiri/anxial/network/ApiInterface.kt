package com.ajithvgiri.anxial.network


import com.ajithvgiri.anxial.data.model.BrandResponse
import com.ajithvgiri.anxial.data.model.LoginRequest
import com.ajithvgiri.anxial.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("app/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("app/get-brand-types")
    fun brands(@Header("Authorization") auth: String): Call<BrandResponse>

//    @POST("api/{elevatorId}/createftb")
//    fun createFTB(@Path("elevatorId") elevatorId:Int,@Body request: FTBRequest): Call<FTBSendResponse>
}