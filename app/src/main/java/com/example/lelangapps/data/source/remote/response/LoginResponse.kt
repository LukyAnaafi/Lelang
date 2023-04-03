package com.example.lelangapps.data.source.remote.response

import com.example.lelangapps.data.source.model.User


data class LoginResponse(
    val message : String? = null,
    val data : User? = null,
)
