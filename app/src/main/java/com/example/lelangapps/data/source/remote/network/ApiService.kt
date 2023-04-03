package com.example.lelangapps.data.source.remote.network


import android.service.autofill.UserData
import com.example.lelangapps.data.source.model.AllUser
import com.example.lelangapps.data.source.model.HistoryBidItemRes
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.request.*
import com.example.lelangapps.data.source.remote.response.*
import com.example.lelangapps.util.Prefs
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body login : LoginRequest,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data : RegisterRequest,
    ): Response<RegisterResponse>

    @POST("register")
    suspend fun addStaff(
        @Body data : RegisterRequest,
        @Header("Authorization") token : String = "Bearer "+Prefs.token,
    ): Response<RegisterResponse>

    @PUT("update-user/{id}")
    suspend fun update(
        @Path("id") int: Int,
        @Body data : UpdateProfilRequest,
    ): Response<LoginResponse>

    @Multipart
    @POST("insertlelang")
    suspend fun createItemAuc(
       // @Body data : CreateItemAucRequest,
        /*@Part image_item : MultipartBody.Part,*/
        @Header("Authorization") token : String = "Bearer "+Prefs.token,
        @Part image_item : MultipartBody.Part? = null,
        @Part("name_item") name_item : RequestBody,
        @Part("opening_price") opening_price : RequestBody,
        @Part("description_item") description_item : RequestBody,
        @Part("date_close_auction") date_close_auction : RequestBody,
    ): Response<BaseSingleResponse<ItemAucResponse>>

    @GET("itemAuction")
    suspend fun getItemAuc() : Response<BaseListResponse<ItemsAuction>>

    @GET("itemAuction")
    suspend fun getCountItemAuc() : Response<BaseSingleResponse<ItemAucResponse>>

    @GET("itemAuction")
    suspend fun getDetailAuction(
        @Query("id_auction") id_auction : Int,
    ) : Response<BaseSingleResponse<ItemsAuction>>

    @POST("bid")
    suspend fun bidAuction(
        @Body data : BidRequest,
        @Header("Authorization") token: String = "Bearer "+Prefs.token
    ) : Response<BidResponse>

    @GET("history")
    suspend fun getBidHistoryItem(
        @Query("id_auction") id_auction: Int,
    ) : Response<BaseListResponse<HistoryBidItemRes>>

    @GET("getUser")
    suspend fun getAllUser() : Response<BaseListResponse<AllUser>>
}