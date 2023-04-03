package com.example.lelangapps.ui.updateuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.source.remote.request.UpdateProfilRequest

class UpdateUserViewModel(val repo : AppRepository) : ViewModel() {

    fun updateUser(data : UpdateProfilRequest) = repo.update(data).asLiveData()
}