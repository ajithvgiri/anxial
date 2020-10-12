package com.ajithvgiri.anxial.data.datasource

import android.app.Application
import com.ajithvgiri.anxial.data.Result
import com.ajithvgiri.anxial.data.model.LoggedInUser
import com.ajithvgiri.anxial.data.model.LoginRequest
import com.ajithvgiri.anxial.data.model.LoginResponse
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

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor(application: Application) {

    val api: ApiInterface = RetrofitService(application).createService(ApiInterface::class.java)
    val preferences = PreferenceHelper.prefs(application)

    val currentUser = if (preferences[PREFERENCE_TOKEN, ""]?.isNotEmpty() == true) {
        preferences[PREFERENCE_TOKEN, ""]?.let { userId ->
            LoggedInUser(userId, "")
        }
    } else {
        null
    }

    fun login(username: String, password: String, result: (Result<LoginResponse>) -> Unit) {
        try {
            val loginRequest = LoginRequest(username, password)
            api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Timber.e("error ${t.localizedMessage}")
                    result(Result.Error(IOException(t.localizedMessage)))
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        preferences[PREFERENCE_TOKEN] = response.body()?.data?.access_token
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

    fun logout() {
        // TODO: revoke authentication
    }
}