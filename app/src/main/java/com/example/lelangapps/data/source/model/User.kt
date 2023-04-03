package com.example.lelangapps.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val user : dataUser ? = null,
    val token: String?
) : Parcelable
@Parcelize
data class dataUser(
    val email: String?,
    val id: Int?,
    val id_staff: Int?,
    val image: String?,
    val level: String?,
    val name: String?,
    val telp: String?,
) : Parcelable