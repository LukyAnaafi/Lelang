package com.example.lelangapps.data.source.remote.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part



data class CreateItemAucRequest(
    val image_item: String? = null,
    //val image_item : MultipartBody.Part,
    val name_item : String? = null,
    val opening_price : String? = null,
    val description_item : String? = null,
    val date_close_auction : String? = null
)
