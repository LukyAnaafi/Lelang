package com.example.lelangapps.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.source.remote.request.LoginRequest

class LoginViewModel(val repo : AppRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("Ini aku jadi PRESIDEN")
    }

    fun login(data : LoginRequest) = repo.login(data).asLiveData()
}