package com.example.lelangapps.data.source.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryBidItemRes(
    val message : String? = null,
    val id_auction : Int,
    val data : HistoryBidItem
) : Parcelable
@Parcelize
data class HistoryBidItem(
    val id : Int? = null,
    val id_auction: Int,
    val bid_price : Int,
    val user : UserBid? = null
) : Parcelable
@Parcelize
data class UserBid(
    val name : String? = null,
    val image : String? = null,
    val telp : String? = null,
    val email : String? = null
) : Parcelable