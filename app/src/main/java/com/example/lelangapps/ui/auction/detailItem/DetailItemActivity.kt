package com.example.lelangapps.ui.auction.detailItem

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.HistoryBidItem
import com.example.lelangapps.data.source.model.HistoryBidItemRes
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.data.source.remote.request.BidRequest
import com.example.lelangapps.data.source.remote.response.BidResponse
import com.example.lelangapps.databinding.ActivityDetailItemBinding
import com.example.lelangapps.ui.auction.AuctionViewModel
import com.example.lelangapps.util.Constants
import com.example.lelangapps.util.Prefs
import com.example.lelangapps.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailItemBinding
    private val item by extra<ItemsAuction>()
    private val viewModel : AuctionViewModel by viewModel()
    private var historyAdapter = HistoryBidItemAdapter{
        getDataHistoryBIdItem(it)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.getHistoryBidItem(history.id_auction).observe(this){
//            when(it.state) {
//                State.SUCCESS -> {
//                    val dataHistory = it.data ?: emptyList()
//                    historyAdapter.addHistory(dataHistory)
//
//                    if (dataHistory.isEmpty()){
//                        toastError("Data Kosong")
//                    }
//                }
//                State.ERROR -> {
//                    Toast.makeText(this, "History cannot open", Toast.LENGTH_SHORT).show()
//                }
//                State.LOADING -> {
//
//                }
//            }
//        }

        if (item?.status_item == "sold")

        binding.apply {
            Glide.with(imageViewDetailItem)
                .load(Constants.NETWORK_IMAGE_URL+item?.image_item)
                .apply(
                    RequestOptions()
                        .centerCrop()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.auction)
                        .error(R.drawable.auction)
                ).into(imageViewDetailItem)
        }

        binding.apply {
            if (Prefs.isLogin == false){
                materialCardViewEditBidsBtn.visibility = View.GONE
            }else{
                materialCardViewEditBidsBtn.visibility = View.VISIBLE
            }
        }



        item?.let {
            //logs("item: " + toJson())
          //  binding.textViewNameItemInDetail.text= it?.item?.item_name
        }

        //Set up data for detail item
        setUpDataItem()


        //setUp Adapter for history bid
        setAdapterHistory()

        //bid proccess
        binding.apply {
            buttonBids.setOnClickListener {
                if (validateBid() == true){
                    postBid()
                }

            }
        }

    }

    private fun getDataHistoryBIdItem(history : HistoryBidItemRes) {

    }

    private fun setAdapterHistory() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerViewHistoryBid.layoutManager = linearLayoutManager
        binding.recyclerViewHistoryBid.adapter = historyAdapter
    }

    private fun validateBid(): Boolean {
        if (binding.editTextBid.isEmpty()) return false

        return true
    }

    private fun postBid() {
        item.let {
            val bidDataReq = BidRequest(
                id_auction = it!!.id_auction,
                bid = binding.editTextBid.text.toString()
            )

            viewModel.bidAuction(bidDataReq).observe(this){
               // val msg = BidResponse(it.data?.message)
                /*val m = it.data?.message.toString()*/
               // Toast.makeText(this,"${m}",Toast.LENGTH_SHORT).show()

                when(it.state) {
                    State.SUCCESS -> {
                        //it.message?.let { it1 -> showToast(it1) }
                        /*showToast(it.message.toString())*/
                        Toast.makeText(this,it.data?.message, Toast.LENGTH_SHORT).show()
                        //Toast.makeText(this,"$res",Toast.LENGTH_SHORT).show()
                    }
                    State.ERROR -> {
                        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                    }
                    State.LOADING -> {

                    }
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setUpDataItem() {
        binding.apply {
            item?.let {
                textViewNameItemInDetail.text = it?.item?.item_name
                dateCloseTextView.text = it.date_close_auction
                if (it.item.final_price == null){
                    textViewHighestBidValue.text = it.item.open_price.toRupiah()
                }else{
                    textViewHighestBidValue.text = it.item.final_price.toRupiah()
                }
                textViewItemDescription.text = it.item.description_item.toString()
            }
        }
    }
}