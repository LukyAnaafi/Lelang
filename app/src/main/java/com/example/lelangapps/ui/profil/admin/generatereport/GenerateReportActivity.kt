package com.example.lelangapps.ui.profil.admin.generatereport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lelangapps.R
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.ActivityGenerateReportBinding
import com.example.lelangapps.ui.profil.admin.AdminViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateReportActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGenerateReportBinding
    private val generateReportItemAdapter = GenerateReportItemAdapter()
    private val viewModel : AdminViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setGenerateReport()
    }

    private fun setAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewReport.layoutManager = linearLayoutManager
        binding.recyclerViewReport.adapter = generateReportItemAdapter
    }

    private fun setGenerateReport() {
        viewModel.generateReportItem().observe(this){
            when(it.state){
                State.SUCCESS -> {
                    val dataItem = it.data ?: emptyList()
                    generateReportItemAdapter.addGenerateReport(dataItem.filter {it.status_item == "sold" })
                }
                State.ERROR -> {

                }
                State.LOADING -> {

                }
            }
        }
    }
}