package com.example.lelangapps.data.repository

import com.example.lelangapps.data.source.local.LocalDataSource
import com.example.lelangapps.data.source.remote.RemoteDataSource
import com.example.lelangapps.data.source.remote.network.Resource
import com.example.lelangapps.data.source.remote.request.LoginRequest
import com.example.lelangapps.data.source.remote.request.RegisterRequest
import com.example.lelangapps.data.source.remote.request.UpdateProfilRequest
import com.example.lelangapps.util.Prefs
import com.example.lelangapps.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow

class AppRepository(val local : LocalDataSource, val remote : RemoteDataSource) {

    fun login(data : LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    Prefs.token = user?.token ?: "tokenErrorkawan"
                    emit(Resource.success(user))
                    logs("success:" +body.toString())
                }else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                    logs("error:" + "keterangan error")
                }
            }
        }catch (e: Exception){
            emit(Resource.error(e.message ?: "Terjadi kesalahan dari anda",null))
            logs("Error" + e.message)
        }
    }

    fun register(data : RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    Prefs.token = user?.token ?: "tokenErrorkawan"
                    emit(Resource.success(user))
                    logs("success:" +body.toString())
                }else {
                    emit(Resource.error("0ne of the email or phone number is already registered, please change it",null))
                    logs("error:" + "keterangan error")
                }
            }
        }catch (e: Exception){
            emit(Resource.error(e.message ?: "Terjadi kesalahan",null))
            logs("Error" + e.message)
        }
    }

    fun addStaff(data : RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful){
                    val body = it.body()?.data
                    emit(Resource.success(body))
                    logs("success:" +body.toString())
                }else {
                    emit(Resource.error("0ne of the email or phone number is already registered, please change it",null))
                    logs("error:" + "keterangan error")
                }
            }
        }catch (e: Exception){
            emit(Resource.error(e.message ?: "Terjadi kesalahan",null))
            logs("Error" + e.message)
        }
    }

    fun addStaff(data: RegisterRequest,token : String) = flow {
        emit(Resource.loading(null))
        try {
            remote.addStaff(data).let {
                if (it.isSuccessful){
                    val body = it.body()?.data
                    emit(Resource.success(body))
                    logs("success: "+body.toString())
                }else {
                    emit(Resource.error("Error ya???",null))
                    logs("error: " + "wwkkkwkw")

                }
            }
        }catch (e : Exception){
            emit(Resource.error(e.message ?: "Terjadi error",null))
            logs("Error" + e.message)
        }
    }

    fun update(data : UpdateProfilRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                }else {
                    emit(Resource.error("Terjadi kesalahan",null))

                }
            }
        }catch (e: Exception){
            emit(Resource.error(e.message ?: "Terjadi kesalahan",null))

        }
    }

    fun getAllUser() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAllUser().let {
                if (it.isSuccessful){
                    val body = it.body()
                    val user = body?.data
                    emit(Resource.success(user))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(),null))
                }
            }
        }catch (e : Exception) {
            emit(Resource.error(e.message.defaultError(),null))
        }
    }

}