package com.example.lelangapps.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.ItemAuctionRepository

class DashboardViewModel(val repo : ItemAuctionRepository) : ViewModel() {

    fun getNewItemAuction() = repo.getItemAuction().asLiveData()
    fun getDetail(id_auction : Int) = repo.getDetailAuction(id_auction).asLiveData()

}