package com.ajithvgiri.anxial.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


/**
 * Data class that captures user information for logged in users retrieved from /app/login
 */

// Login
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
) : Serializable

@JsonClass(generateAdapter = true)
data class Error(var message: String? = null, var code: Int? = null) : Serializable


// Brand
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
) : Serializable

@JsonClass(generateAdapter = true)
data class Brand(var id: Int? = null, var name: String? = null, var slug: String? = null) :
    Serializable


// Product Response
@JsonClass(generateAdapter = true)
data class ProductResponse(
    var data: List<Products> = ArrayList(),

    var links: Links? = Links(),
    var status: Boolean? = null,
    var code: Int? = null,
    var error: Error? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class Links(
    var first: String? = null,
    var last: String? = null,
    var prev: String? = null,
    var next: String? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class Products(
    var id: Int? = null,
    var name: String? = null,
    var images: List<String>? = ArrayList(),
    var created: String? = null,
    var brand: Brand? = null,
    var brand_type: Brand? = null,
    var category: Brand? = null,
    var stock: List<String>? = ArrayList(),
    var variants: List<Variants>? = ArrayList()

) : Serializable

@JsonClass(generateAdapter = true)
data class Variants(
    var id: Int? = null,
    var sku: String? = null,
    var images: List<String>? = ArrayList(),
    var wholesale_price: Int? = null,
    var attributes: List<Attributes>? = ArrayList()
) : Serializable

@JsonClass(generateAdapter = true)
data class Attributes(
    var value_id: Int? = null,
    var attribute_id: Int? = null,
    var value: String? = null,
    var slug: String? = null,
) : Serializable
