package com.example.lelangapps.data.source.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequest(

    @field:SerializedName("name")
    val name : String,

    @field:SerializedName("telp")
    val telp : String,

    @field:SerializedName("email")
    val email : String,

    @field:SerializedName("password")
    val password : String,

    @field:SerializedName("level")
    val level : String? = null
) : Parcelable
