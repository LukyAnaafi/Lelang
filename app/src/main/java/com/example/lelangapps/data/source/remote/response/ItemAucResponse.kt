package com.example.lelangapps.data.source.remote.response

import com.example.lelangapps.data.source.model.ItemsAuction

data class ItemAucResponse(
    val message : String? = null,
    val data : ItemsAuction? = null,
    val count : Int
)
