package com.example.lelangapps.data.source.remote.request

data class UpdateProfilRequest(
    val id : Int,
    val name : String? = null,
    val email : String? = null,
    val telp : String? = null
)
