package com.ajithvgiri.anxial.network


import com.ajithvgiri.anxial.data.model.LoginRequest
import com.ajithvgiri.anxial.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("app/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

//    @GET("api/{elevatorId}/listftb")
//    fun getFTB(@Path("elevatorId") elevatorId:Int): Call<FTBResponse>
//
//    @POST("api/{elevatorId}/createftb")
//    fun createFTB(@Path("elevatorId") elevatorId:Int,@Body request: FTBRequest): Call<FTBSendResponse>
}