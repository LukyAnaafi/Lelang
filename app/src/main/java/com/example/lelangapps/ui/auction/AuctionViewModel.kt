package com.example.lelangapps.ui.auction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.lelangapps.data.repository.ItemAuctionRepository
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.request.BidRequest

class AuctionViewModel(val repo: ItemAuctionRepository) : ViewModel() {

    fun getItemAuction() = repo.getItemAuction().asLiveData()
    fun getDetailAuction(id_auction : Int) = repo.getDetailAuction(id_auction).asLiveData()
    fun bidAuction(data : BidRequest) = repo.bidAuction(data).asLiveData()
    fun getHistoryBidItem(id_auction: Int) = repo.getHistoryBidItem(id_auction).asLiveData()

}