package com.example.lelangapps.ui.profil.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.repository.ItemAuctionRepository
import com.example.lelangapps.data.source.remote.request.RegisterRequest
import com.google.gson.reflect.TypeToken

class AdminViewModel(val repoItem : ItemAuctionRepository,val authRepo : AppRepository) : ViewModel() {

    fun registerAdmin() = repoItem.getCountItemAuction().asLiveData()
    fun getAllUser() = authRepo.getAllUser().asLiveData()
    fun generateReportItem() = repoItem.getItemAuction().asLiveData()
}