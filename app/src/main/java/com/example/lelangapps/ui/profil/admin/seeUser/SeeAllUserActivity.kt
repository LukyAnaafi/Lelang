package com.example.lelangapps.ui.profil.admin.seeUser

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lelangapps.R
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.ActivitySeeAllUserBinding
import com.example.lelangapps.ui.profil.admin.AdminViewModel
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllUserActivity : AppCompatActivity() {

    lateinit var binding : ActivitySeeAllUserBinding
    private val userAdapter = AllUserAdapter()
    private val viewModel : AdminViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySeeAllUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataUser()
        setUpAdapter()

    }

    private fun getDataUser() {
        viewModel.getAllUser().observe(this){
            when(it.state){
                State.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val dataUser = it.data ?: emptyList()
                    userAdapter.addUser(dataUser.filter { it.level == "user" })

                    if (dataUser.isEmpty()){
                        toastError("Nothing")
                    }

                }
                State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                State.ERROR -> {

                }
            }
        }
    }

    private fun setUpAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvUser.layoutManager = linearLayoutManager
        binding.rvUser.adapter = userAdapter
    }
}