package com.ajithvgiri.anxial.data.model

import com.squareup.moshi.JsonClass
import java.io.Serializable


/**
 * Data class that captures user information for logged in users retrieved from /app/login
 */
@JsonClass(generateAdapter = true)
data class LoginResponse(
    var status: Boolean,
    var code: Int,
    var data: Data,
    var error: Error
) : Serializable

@JsonClass(generateAdapter = true)
data class Data(var access_token: String, var token_type: String, var expires_in: Int) :
    Serializable

@JsonClass(generateAdapter = true)
data class Error(var message: String, var code: Int) : Serializable