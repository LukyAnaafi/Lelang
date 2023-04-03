package com.example.lelangapps.ui.profil.admin.registeradmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.source.remote.request.RegisterRequest
import com.google.gson.reflect.TypeToken

class RegisterAdminViewModel(val repo : AppRepository) : ViewModel() {

    fun registerAdmin(data : RegisterRequest) = repo.addStaff(data).asLiveData()
}