package com.example.lelangapps.ui.profil.admin.generatereport

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.model.User
import com.example.lelangapps.databinding.RvGenerateReportBinding
import com.inyongtisto.myhelper.extension.toRupiah
import kotlin.coroutines.coroutineContext

class GenerateReportItemAdapter() : RecyclerView.Adapter<GenerateReportItemAdapter.ViewHolder>() {

    private var itemGenerate = ArrayList<ItemsAuction>()

    inner class ViewHolder(private val binding : RvGenerateReportBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item : ItemsAuction, position : Int){
            binding.apply {
                if (item.user.id == null){
                    textViewIdUser.text = "-"
                }else{
                    textViewIdUser.text = item.user.id.toString()
                }

                if (item.user.name != null){
                    textViewNameUser.text = item.user.name
                }else{
                    textViewNameUser.text = "-"
                }
                textViewNameItem.text = item.item.item_name

                textViewPrice.text = item.item.final_price.toRupiah()

                val sColor = "#00FF22"
                val uColor = "#FF0000"
                if (item.item.final_price.toRupiah() > item.item.open_price.toRupiah()){
                    textViewStatus.setTextColor(Color.parseColor(sColor))
                    textViewStatus.text = "Sold"
                }else{
                    textViewStatus.setTextColor(Color.parseColor(uColor))
                    textViewStatus.text = "Unsold"
                }
            }
        }
    }

    fun addGenerateReport(itemsReport : List<ItemsAuction>){
        itemGenerate.clear()
        itemGenerate.addAll(itemsReport)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvGenerateReportBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = itemGenerate.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemGenerate[position],position)
    }
}