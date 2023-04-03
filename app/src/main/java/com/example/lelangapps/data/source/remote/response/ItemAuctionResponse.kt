package com.example.lelangapps.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemAuctionResponse(
    val message : String? = null,
    val data : dataAuc? = null
) : Parcelable
@Parcelize
data class dataAuc(
    val id_auction : Int? = null,
    val id_staff : Int? = null,
    val date_close_auction : String? = null,
    val date_open_auction : String? = null,
    val item : ItemAuc? = null
): Parcelable
@Parcelize
data class ItemAuc(
    val id_item : Int? = null,
    val item_name : String? = null,
    val open_price : Int? = null,
    val final_price : Int? = null
) : Parcelable
