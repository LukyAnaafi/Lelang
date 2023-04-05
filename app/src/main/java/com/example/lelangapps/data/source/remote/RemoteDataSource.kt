package com.example.lelangapps.data.source.remote

import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.network.ApiService
import com.example.lelangapps.data.source.remote.request.*
import com.example.lelangapps.data.source.remote.response.BaseSingleResponse
import com.example.lelangapps.util.Prefs
import com.google.gson.reflect.TypeToken
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart

class RemoteDataSource(private val api : ApiService) {
    //Tugas utama file ini adalah penghubung antara Repository dengan remote data source

    //Auth
    suspend fun login(data : LoginRequest) = api.login(data)
    suspend fun register(data : RegisterRequest) = api.register(data)
    //suspend fun updateUser(id : Int,data : UpdateProfilRequest) = api.update(id,data)
    suspend fun addStaff(data: RegisterRequest) = api.addStaff(data)
    suspend fun getAllUser() = api.getAllUser()
    suspend fun updateProfil(id: Int,data : UpdateProfilRequest) = api.updateUser(id,data)

    //Item Auction
    suspend fun createItemAuc(
        token : String,
        image_item : MultipartBody.Part?,
        name_item : RequestBody,
        opening_price : RequestBody,
        description_item : RequestBody,
        date_close_auction : RequestBody
    ) = api.createItemAuc(
        token,
        image_item = image_item,
        name_item = name_item,
        opening_price = opening_price,
        description_item = description_item,
        date_close_auction = date_close_auction
    )

    suspend fun getItemAuction() = api.getItemAuc()
    suspend fun getDetailAuction(id_auction : Int) = api.getDetailAuction(id_auction)
    suspend fun bidAuction(data : BidRequest) = api.bidAuction(data)
    suspend fun getHistoryBidItem(id_auction : Int) = api.getBidHistoryItem(id_auction)
    suspend fun getCountItemAuc() = api.getCountItemAuc()
    suspend fun deleteAuctionItem(id_auction: Int) = api.deleteAuctionItem(id_auction)


}