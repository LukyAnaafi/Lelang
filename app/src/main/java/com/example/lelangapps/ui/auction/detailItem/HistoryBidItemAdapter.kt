package com.example.lelangapps.ui.auction.detailItem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.data.source.model.HistoryBidItem
import com.example.lelangapps.data.source.model.HistoryBidItemRes
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.databinding.RvHistoryBidBinding
import com.inyongtisto.myhelper.extension.toRupiah

@SuppressLint("NotifyDataSetChanged")
class HistoryBidItemAdapter(
    val click : (history : HistoryBidItemRes) -> Unit,
   //val onClickItem : (item : ItemsAuction) -> Unit,
):RecyclerView.Adapter<HistoryBidItemAdapter.ViewHolder>() {

    private var listHistoryBid = ArrayList<HistoryBidItemRes>()

    @SuppressLint("SetTextI18n")
    inner class ViewHolder(private val binding : RvHistoryBidBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(historyBid : HistoryBidItemRes, position : Int){
            binding.apply {
                textViewTitleName.text = historyBid.data.user?.name
                textViewBidHistory.text = historyBid.data.bid_price.toRupiah()

                relativeLayoutHistory.setOnClickListener {
                    //click.invoke(historyBid)
                }
            }
        }
    }

    fun addHistory(history : List<HistoryBidItemRes>){
        listHistoryBid.clear()
        listHistoryBid.addAll(history)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvHistoryBidBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = listHistoryBid.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listHistoryBid[position],position)
    }
}