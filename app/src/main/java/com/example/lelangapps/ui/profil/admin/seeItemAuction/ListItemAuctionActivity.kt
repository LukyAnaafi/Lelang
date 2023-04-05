package com.example.lelangapps.ui.profil.admin.seeItemAuction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.ActivityListItemAuctionBinding
import com.example.lelangapps.ui.profil.ProfilViewModel
import com.example.lelangapps.ui.profil.admin.AdminViewModel
import com.example.lelangapps.util.defaultError
import com.inyongtisto.myhelper.extension.showConfirmDialog
import com.inyongtisto.myhelper.extension.showErrorDialog
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.extension.toastSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListItemAuctionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListItemAuctionBinding
    private val viewModel : AdminViewModel by viewModel()
    private val adapter = ListItemAuctionAdapter(
        onDelete = {item, pos ->
            confirmDelete(item,pos)
        }
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemAuctionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataItem()
        setUpAdapter()
    }

    private fun onDelete(item: ItemsAuction, pos: Int) {
        viewModel.deleteItem(item.id_auction).observe(this){
            when(it.state){
                State.SUCCESS -> {
                    adapter.removeAt(pos)
                    toastSuccess("Item Auction Deleted")
                }
                State.ERROR -> {
                    showErrorDialog(it.message.defaultError())

                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun confirmDelete(item: ItemsAuction, pos: Int) {
        showConfirmDialog(
            "Deleted Item",
        "Sure?",
            "Delete"
        ){
            onDelete(item, pos)
        }
    }


    private fun setUpAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewItem.layoutManager = linearLayoutManager
        binding.recyclerViewItem.adapter = adapter

    }

    private fun getDataItem() {
        viewModel.getItem().observe(this){
            when(it.state) {
                State.SUCCESS -> {
                    val dataItem = it.data ?: emptyList()
                    adapter.addItems(dataItem)
                    /*val data = it.data ?: emptyList<ItemsAuction>()
                    itemAdapter.addItems(data as ArrayList<ItemsAuction>)*/

                    if (dataItem.isEmpty()){
                        toastError("Data Kosong")
                    }


                }
                State.LOADING -> {

                }

                State.ERROR -> {
                    toastError("Error nggak bisa tampil")
                }
            }
        }
    }
}