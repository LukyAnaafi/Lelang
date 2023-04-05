package com.example.lelangapps.ui.profil.admin.seeItemAuction

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.databinding.AuctionItemCardBinding
import com.example.lelangapps.databinding.RvItemAuctionBinding
import com.example.lelangapps.ui.auction.ItemAuctionAdapter
import com.inyongtisto.myhelper.extension.toRupiah


@SuppressLint("NotifyDataSetChanged")
class ListItemAuctionAdapter(
    val onDelete : (item : ItemsAuction, pos : Int) -> Unit
):RecyclerView.Adapter<ListItemAuctionAdapter.ViewHolder>() {

    private val items = ArrayList<ItemsAuction>()

    inner class ViewHolder(val binding : AuctionItemCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemsAuction, position : Int){
            binding.apply {
                textId.text = item.id_auction.toString()
                textItem.text = item.item.item_name
                textOpenPrice.text = item.item.open_price.toRupiah()
                textDateClose.text = item.date_close_auction

                buttonDelete.setOnClickListener {
                    onDelete.invoke(item, adapterPosition)
                }
            }
        }
    }

    fun addItems(iItems : List<ItemsAuction>){
        items.clear()
        items.addAll(iItems)
        notifyDataSetChanged()
    }

    fun removeAt(index : Int){
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAuctionAdapter.ViewHolder {
        return ViewHolder(AuctionItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ListItemAuctionAdapter.ViewHolder, position: Int) {
        // holder.imageItem.setImageResource(itemAuction[position].image_item)
        holder.bind(items[position],position)
        //holder.name_item.text = itemAuction[position].data?.item?.item_name
    }
}