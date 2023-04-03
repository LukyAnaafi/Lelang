package com.example.lelangapps.ui.profil.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.ItemAuctionRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ItemAuctionViewModel(private val repo: ItemAuctionRepository) : ViewModel() {

    fun create(
        token : String,
        image_item: MultipartBody.Part?,
        name_item: RequestBody,
        opening_price: RequestBody,
        description_item: RequestBody,
        date_close_auction: RequestBody
    ) = repo.createItemAuction(
        token,
        image_item = image_item,
        name_item = name_item,
        opening_price = opening_price,
        description_item = description_item,
        date_close_auction = date_close_auction).asLiveData()
    /*fun uploadFile(image_item : MultipartBody.Part? = null) = repo.uploadFile()*/


}