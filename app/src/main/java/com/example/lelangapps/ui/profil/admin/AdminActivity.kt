package com.example.lelangapps.ui.profil.admin

import android.content.Intent
import android.media.MediaDrm.LogMessage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.response.BaseListResponse
import com.example.lelangapps.data.source.remote.response.ItemAucResponse
import com.example.lelangapps.databinding.ActivityAdminBinding
import com.example.lelangapps.ui.profil.admin.generatereport.GenerateReportActivity
import com.example.lelangapps.ui.profil.admin.registeradmin.RegisterAdminActivity
import com.example.lelangapps.ui.profil.admin.seeItemAuction.ListItemAuctionActivity
import com.example.lelangapps.ui.profil.admin.seeUser.SeeAllUserActivity
import com.inyongtisto.myhelper.extension.extra

class AdminActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminBinding
    private val itemAucR by extra<ItemAucResponse>()
    private val item by extra<ItemsAuction>()
    //lateinit var itemAuctionRes : ItemAucResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textButtonCreateStaff.setOnClickListener {
            startActivity(Intent(this, RegisterAdminActivity::class.java))
        }

        binding.materialCardViewItemSold.setOnClickListener {
            startActivity(Intent(this, SeeAllUserActivity::class.java))
        }

        binding.materialCardViewTotalItem.setOnClickListener {
            startActivity(Intent(this, GenerateReportActivity::class.java))
        }

        binding.materialCardViewLiveAuction.setOnClickListener {
            startActivity(Intent(this, ListItemAuctionActivity::class.java))
        }

        binding.apply {
            Log.e("count",itemAucR.toString())
            textViewValueTotalItem.text = itemAucR?.count.toString()
            textViewValueLiveAuction.text = itemAucR?.count.toString()
        }
    }
}