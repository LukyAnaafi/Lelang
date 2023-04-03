package com.example.lelangapps.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllUser(
    val id : Int? = null,
    val id_staff : String? = null,
    val telp : String? = null,
    val name : String? = null,
    val email : String? = null,
    val email_verified_at : String? = null,
    val level : String? = null,
    val created_at : String? = null,
    val updated_at : String? = null
    ): Parcelable
//@Parcelize
//data class userData(
//
//
//) : Parcelable

