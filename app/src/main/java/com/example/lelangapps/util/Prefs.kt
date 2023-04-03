package com.example.lelangapps.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.model.User
import com.example.lelangapps.ui.profil.ProfilFragment
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel
import kotlin.math.log

object Prefs: KotprefModel() {

    var isLogin by booleanPref(false)
    var user by stringPref()
    var item by stringPref()
    var token by stringPref("tokenDefault")

    fun setUser(data: User?) {
        user = data.toJson()
    }

    fun getUser() : User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }




    fun clearData(context: Context){
    }

    /*private var sharedPreferences : SharedPreferences? = null

    init {
        sharedPreferences = activity.getSharedPreferences("MYPREF",Context.MODE_PRIVATE)
    }


    fun setLogin(value : Boolean) {
        sharedPreferences!!.edit().putBoolean(login, true).apply()
    }

    fun getLogin() : Boolean {
        return sharedPreferences!!.getBoolean(login, false)
    }*/

}