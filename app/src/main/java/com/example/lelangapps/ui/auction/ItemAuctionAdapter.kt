package com.example.lelangapps.ui.auction

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.databinding.RvItemAuctionBinding
import com.example.lelangapps.ui.auction.detailItem.DetailItemActivity
import com.example.lelangapps.util.Constants
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toRupiah


@SuppressLint("NotifyDataSetChanged")
class ItemAuctionAdapter(
    val onClickItem : (item : ItemsAuction) -> Unit,
): RecyclerView.Adapter<ItemAuctionAdapter.ViewHolder>() {

    private var itemAuction = ArrayList<ItemsAuction>()
    @SuppressLint("SetTextI18n")
    inner class ViewHolder(private val itemBinding : RvItemAuctionBinding):RecyclerView.ViewHolder(itemBinding.root)
    {
        val name_item = itemBinding.textViewNameItemAuction

        fun bind(item : ItemsAuction, position: Int){
            itemBinding.apply {
                val context = root.context
                textViewNameItemAuction.text = item.item.item_name
                if(item.item.final_price == null) {
                    textViewHighestBid.text = "Nothing bid yet"
                }else{
                    textViewHighestBid.text = item.item.final_price.toRupiah()
                }
                textViewOpenPriceValue.text = item.item.open_price.toRupiah()

                Glide.with(imageViewItemAuction)
                    .load(Constants.NETWORK_IMAGE_URL+item.image_item)
                    .apply(
                        RequestOptions()
                            .centerCrop()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.auction)
                            .error(R.drawable.auction)
                    )
                    .into(imageViewItemAuction)

                materialCardViewItemAuction.setOnClickListener {
                    root.context.intentActivity(DetailItemActivity::class.java, item)
                    onClickItem.invoke(item)
                }
            }


        }

        /*init {
            itemView.setOnClickListener {v : View ->
                val position : Int = bindingAdapterPosition
                Toast.makeText(itemView.context, itemAuction[position].item.item_name, Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    fun addItems(items : List<ItemsAuction>){
        itemAuction.clear()
        itemAuction.addAll(items)
        notifyDataSetChanged()
    }

    fun setFilteredList(mList : ArrayList<ItemsAuction>){
        itemAuction = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemAuctionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = itemAuction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.imageItem.setImageResource(itemAuction[position].image_item)
        holder.bind(itemAuction[position],position)
        //holder.name_item.text = itemAuction[position].data?.item?.item_name
    }
}


