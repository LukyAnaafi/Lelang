package com.example.lelangapps.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.databinding.ActivityDetailItemBinding
import com.example.lelangapps.databinding.RvItemCardNewAuctionItemBinding
import com.example.lelangapps.ui.auction.detailItem.DetailItemActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toRupiah
import java.text.FieldPosition

@SuppressLint("NotifyDataSetChanged")
class NewItemAuctionAdapter(
    val onClickItem : (item : ItemsAuction) -> Unit,
) : RecyclerView.Adapter<NewItemAuctionAdapter.ViewHolder>() {

    private var itemAuction = ArrayList<ItemsAuction>()

    @SuppressLint("SetTextI18n")
    inner class ViewHolder(private val itemBinding: RvItemCardNewAuctionItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bindData(item : ItemsAuction, position: Int){
            itemBinding.apply {
                val context = root.context
                textViewNameItemInCard.text = item.item.item_name
                if (item.item.final_price == null) {
                    highestBid.text = item.item.open_price.toRupiah()
                }else{
                    highestBid.text = item.item.final_price.toRupiah()
                }

                cardViewNewItem.setOnClickListener {
                    root.context.intentActivity(DetailItemActivity::class.java, item)
                    onClickItem.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemCardNewAuctionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = itemAuction.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemAuction[position],position)
    }

    fun addItems(items : List<ItemsAuction>){
        itemAuction.clear()
        itemAuction.addAll(items)
        notifyDataSetChanged()
    }
}