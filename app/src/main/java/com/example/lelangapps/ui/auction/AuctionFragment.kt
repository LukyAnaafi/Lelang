package com.example.lelangapps.ui.auction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.FragmentAuctionBinding
import com.example.lelangapps.ui.auction.detailItem.DetailItemActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.isNull
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuctionFragment : Fragment() {

    private var _binding: FragmentAuctionBinding? = null
    private val viewModel : AuctionViewModel by viewModel()
    private var itemAdapter = ItemAuctionAdapter{
        detailAuction(it)
    }

    //private var itemAuction = ItemsAuction.getItemsAuction()

    /*val itemObserver = Observer<MutableList<ItemsAuction>> { itemList ->
        itemAdapter.updateList(itemList)
    }*/

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val homeViewModel =
            ViewModelProvider(this).get(AuctionViewModel::class.java)*/
        _binding = FragmentAuctionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpAdapter()
        getDataItem()





       /* val recyclerViewItemAuction = binding.recyclerViewItemsAuction
        val linearLayoutManager = GridLayoutManager(this.requireContext(), 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        //set up rv
        recyclerViewItemAuction.layoutManager =linearLayoutManager
        //recyclerViewItemAuction.adapter = ItemAuctionAdapter(itemAuction)*/
        return root
    }



    /*private fun setUpRv() {
        val rvItem = binding.recyclerViewItemsAuction as RecyclerView
        itemAdapter.recyclerAdapter(arrayListOf(),requireContext())
        val linearLayoutManager = GridLayoutManager(this.requireContext(),2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvItem.layoutManager = linearLayoutManager
        rvItem.adapter = itemAdapter
    }*/

    override fun onResume() {
        getDataItem()
        super.onResume()
    }


    private fun getDataItem() {
        viewModel.getItemAuction().observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    val dataItem = it.data ?: emptyList()
                    itemAdapter.addItems(dataItem.filter { it.status_item == "available" })
                    /*val data = it.data ?: emptyList<ItemsAuction>()
                    itemAdapter.addItems(data as ArrayList<ItemsAuction>)*/

                    if (dataItem.isEmpty()){
                        toastError("Data Kosong")
                    }


                }
                State.LOADING -> {

                }

                State.ERROR -> {
                    binding.progressbar.visibility = View.GONE
                    toastError("Error nggak bisa tampil")
                }
            }
        }
    }

    private fun detailAuction(item : ItemsAuction) {
        viewModel.getDetailAuction(item.id_auction).observe(requireActivity()){
            when (it.state) {
                State.SUCCESS -> {
                    intentActivity(DetailItemActivity :: class.java,it.data)
                }
                State.ERROR -> {
                    Toast.makeText(activity,"Item not found" , Toast.LENGTH_SHORT)
                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun setUpAdapter() {
        val linearLayoutManager = GridLayoutManager(this.requireContext(), 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewItemsAuction.layoutManager = linearLayoutManager
        binding.recyclerViewItemsAuction.adapter = itemAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}