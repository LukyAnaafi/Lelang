package com.example.lelangapps.ui.profil.admin.seeUser

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.AllUser
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.databinding.RvItemAuctionBinding
import com.example.lelangapps.databinding.UserCardRvBinding
import com.example.lelangapps.ui.auction.detailItem.DetailItemActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toRupiah


@SuppressLint("NotifyDataSetChanged")
class AllUserAdapter(): RecyclerView.Adapter<AllUserAdapter.ViewHolder>() {

    private var user = ArrayList<AllUser>()

    @SuppressLint("SetTextI18n")
    inner class ViewHolder(private val itemBinding : UserCardRvBinding):RecyclerView.ViewHolder(itemBinding.root)
    {

        fun bind(users : AllUser, position: Int){
            itemBinding.apply {
                Log.e("Adapter",users.toString())
                val context = root.context
                textId.text = users.id.toString()
                textEmail.text = users.email.toString()
                textUser.text = users.name.toString()
                textTelp.text = users.telp.toString()
                textLevel.text = users.level.toString()

            }


        }

        /*init {
            itemView.setOnClickListener {v : View ->
                val position : Int = bindingAdapterPosition
                Toast.makeText(itemView.context, itemAuction[position].item.item_name, Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    fun addUser(users : List<AllUser>){
        user.clear()
        user.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UserCardRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = user.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.imageItem.setImageResource(itemAuction[position].image_item)
        holder.bind(user[position],position)
        //holder.name_item.text = itemAuction[position].data?.item?.item_name
    }
}


