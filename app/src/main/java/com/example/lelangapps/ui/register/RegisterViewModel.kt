package com.example.lelangapps.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.source.remote.request.LoginRequest
import com.example.lelangapps.data.source.remote.request.RegisterRequest

class RegisterViewModel(val repo : AppRepository) : ViewModel() {

    fun register(data : RegisterRequest) = repo.register(data).asLiveData()

}