package com.ajithvgiri.anxial.data.datasource

import android.app.Application
import com.ajithvgiri.anxial.data.Result
import com.ajithvgiri.anxial.data.model.*
import com.ajithvgiri.anxial.network.ApiInterface
import com.ajithvgiri.anxial.network.RetrofitService
import com.ajithvgiri.anxial.utils.AppConst.Companion.PREFERENCE_TOKEN
import com.ajithvgiri.anxial.utils.PreferenceHelper
import com.ajithvgiri.anxial.utils.PreferenceHelper.get
import com.ajithvgiri.anxial.utils.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class HomeDataSource @Inject constructor(application: Application) {

    private val api: ApiInterface =
        RetrofitService(application).createService(ApiInterface::class.java)
    private val preferences = PreferenceHelper.prefs(application)

    fun brands(result: (Result<BrandResponse>) -> Unit) {
        try {
            val token = "bearer ${preferences[PREFERENCE_TOKEN, ""]}"
            api.brands(token).enqueue(object : Callback<BrandResponse> {
                override fun onFailure(call: Call<BrandResponse>, t: Throwable) {
                    Timber.e("error ${t.localizedMessage}")
                    result(Result.Error(IOException(t.localizedMessage)))
                }

                override fun onResponse(
                    call: Call<BrandResponse>,
                    response: Response<BrandResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        result(Result.Success(response.body()!!))
                    } else {
                        result(Result.Error(IOException(response.body().toString())))
                    }
                }
            })
        } catch (e: Throwable) {
            result(Result.Error(IOException("Error logging in ${e.localizedMessage}", e)))
        }
    }

    fun products(result: (Result<ProductResponse>) -> Unit) {
        try {
            val token = "bearer ${preferences[PREFERENCE_TOKEN, ""]}"
            api.products(token).enqueue(object : Callback<ProductResponse> {
                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Timber.e("error ${t.localizedMessage}")
                    result(Result.Error(IOException(t.localizedMessage)))
                }

                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        result(Result.Success(response.body()!!))
                    } else {
                        result(Result.Error(IOException(response.body().toString())))
                    }
                }
            })
        } catch (e: Throwable) {
            result(Result.Error(IOException("Error logging in ${e.localizedMessage}", e)))
        }
    }

}