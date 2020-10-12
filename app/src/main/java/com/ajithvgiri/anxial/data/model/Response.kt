package com.ajithvgiri.anxial.data.model

import com.squareup.moshi.JsonClass
import java.io.Serializable


/**
 * Data class that captures user information for logged in users retrieved from /app/login
 */
@JsonClass(generateAdapter = true)
data class LoginResponse(
    var status: Boolean? = null,
    var code: Int? = null,
    var data: LoginData? = null,
    var error: Error? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class LoginData(
    var access_token: String? = null,
    var token_type: String? = null,
    var expires_in: Int? = null
) :
    Serializable

@JsonClass(generateAdapter = true)
data class Error(var message: String? = null, var code: Int? = null) : Serializable


@JsonClass(generateAdapter = true)
data class BrandResponse(
    var data: List<BrandData> = ArrayList(),
    var status: Boolean? = null,
    var code: Int? = null,
    var error: Error? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class BrandData(
    var id: Int? = null,
    var name: String? = null,
    var slug: String? = null,
    var brand: List<Brand>? = ArrayList()
)

@JsonClass(generateAdapter = true)
data class Brand(var id: Int? = null, var name: String? = null, var slug: String? = null)