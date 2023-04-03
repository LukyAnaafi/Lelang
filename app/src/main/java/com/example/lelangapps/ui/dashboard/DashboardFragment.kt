package com.example.lelangapps.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lelangapps.data.source.model.ItemsAuction
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.FragmentDashboardBinding
import com.example.lelangapps.ui.auction.detailItem.DetailItemActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel : DashboardViewModel by viewModel()
    private var newItemAdapter = NewItemAuctionAdapter{
        detailNewItemAuction(it)
    }


    //private var newItem = ItemsAuction.getItemsAuction()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //setZoomRV()
        setUpRecyclerView()
        getDataItem()

        return root
    }

    override fun onResume() {
        getDataItem()
        super.onResume()
    }

    private fun getDataItem() {
        viewModel.getNewItemAuction().observe(viewLifecycleOwner){
            when(it.state) {
                State.SUCCESS -> {
                    val dataItem = it.data ?: emptyList()
                    newItemAdapter.addItems(dataItem)

                    if (dataItem.isEmpty()){
                        toastError("Item auction sold out")
                    }
                }
                State.LOADING -> {

                }
                State.ERROR -> {

                }
            }
        }
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewNewItemAuction.layoutManager = linearLayoutManager
        binding.recyclerViewNewItemAuction.adapter = newItemAdapter
    }

    private fun detailNewItemAuction(item : ItemsAuction) {
        viewModel.getDetail(item.id_auction).observe(requireActivity()){
            when(it.state) {
                State.SUCCESS -> {
                    intentActivity(DetailItemActivity :: class.java, it.data)
                }
                State.ERROR -> {

                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun setZoomRV() {
        val recyclerViewNewItem = binding.recyclerViewNewItemAuction

        //Set up RecyclerView for new item auction
        val linearLayoutManager = ZoomRecyclerLayout(this.requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewNewItem)
        recyclerViewNewItem.isNestedScrollingEnabled = false

        recyclerViewNewItem.layoutManager = linearLayoutManager
        //recyclerViewNewItem.adapter = NewItemAuctionAdapter(newItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}