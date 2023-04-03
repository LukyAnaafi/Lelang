package com.example.lelangapps.data.repository

import com.example.lelangapps.data.source.local.LocalDataSource
import com.example.lelangapps.data.source.remote.RemoteDataSource
import com.example.lelangapps.data.source.remote.network.Resource
import com.example.lelangapps.data.source.remote.request.BidRequest
import com.example.lelangapps.data.source.remote.request.CreateItemAucRequest
import com.example.lelangapps.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ItemAuctionRepository(val local : LocalDataSource, val remote : RemoteDataSource) {

    fun getItemAuction() = flow {
        emit(Resource.loading(null))
        try {
            remote.getItemAuction().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                }
            }
        }catch (e : Exception) {
            emit(Resource.error(e.message.defaultError(),null))
        }
    }

    fun getCountItemAuction() = flow {
        emit(Resource.loading(null))
        try {
            remote.getItemAuction().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                }
            }
        }catch (e : Exception) {
            emit(Resource.error(e.message.defaultError(),null))
        }
    }

    fun createItemAuction(token : String,
                          image_item : MultipartBody.Part?,
                          name_item : RequestBody,
                          opening_price : RequestBody,
                          description_item : RequestBody,
                          date_close_auction : RequestBody
    ) = flow {
        emit(Resource.loading(null))
        try {
            remote.createItemAuc(token, image_item = image_item, name_item = name_item, opening_price = opening_price, description_item = description_item,date_close_auction = date_close_auction).let {
                if (it.isSuccessful){
                    val body = it.body()?.data
                    emit(Resource.success(body))
                }else {
                    emit(Resource.error("Terjadi kesalahan",null))
                    logs("error:" + "keterangan error token ketoke")
                }
            }
        }catch (e: Exception){
            emit(Resource.error(e.message ?: "Terjadi kesalahan",null))
            logs("Error" + e.message)
        }
    }

    fun getDetailAuction(id_auction : Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getDetailAuction(id_auction).let {
                if(it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                }else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                }
            }
        }catch (e : Exception){
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun getHistoryBidItem(id_auction : Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getHistoryBidItem(id_auction).let {
                if(it.isSuccessful){
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                }else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                }
            }
        }catch (e : Exception){
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun bidAuction(data : BidRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.bidAuction(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    emit(Resource.success(body))
                    //emit(Resource.success(it.body()?.data?.message))
                }else {
                   //emit(Resource.success(it.body()?.data?.message))
                  //  emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                    logs("error: "+ "error iki" )
                }
            }
        }catch (e : Exception){
            //emit(Resource.error(e.message ?: "Terjadi kesalahan",null))
            emit(Resource.error(e.message ?: "Salah ",null))
            logs("Error" + e.message)
        }
    }

//    fun uploadFile(file : MultipartBody.Part? = null) = flow {
//        emit(Resource.loading(null))
//        try {
//            remote.createItemAuc().let {
//                if (it.isSuccessful){
//                    val body = it.body()
//                    val fileName = body
//                    emit(Resource.success(fileName))
//                }else{
//                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
//                }
//            }
//        } catch (e: Exception){
//            emit(Resource.error(e.message.defaultError(),null))
//        }
//    }


}