package com.ajithvgiri.anxial.data.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

// Login Request
@JsonClass(generateAdapter = true)
data class LoginRequest(var email: String, var password: String) : Serializable
