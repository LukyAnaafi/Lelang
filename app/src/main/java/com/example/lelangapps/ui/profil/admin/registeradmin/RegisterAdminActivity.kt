package com.example.lelangapps.ui.profil.admin.registeradmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.lelangapps.R
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.data.source.remote.request.RegisterRequest
import com.example.lelangapps.databinding.ActivityRegisterAdminBinding
import com.example.lelangapps.ui.profil.admin.AdminActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.extension.toastSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterAdminActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterAdminBinding
    private val viewModel : RegisterAdminViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roleUser = resources.getStringArray(R.array.user_role)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item_role, roleUser)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.buttonRegister.setOnClickListener {
            registerWithLevel()
        }


    }

    private fun registerWithLevel() {

        if (binding.editInputTextName.isEmpty()) return
        if (binding.editInputTextEmail.isEmpty()) return
        if (binding.editInputTextPassword.isEmpty()) return
        if (binding.editInputTextTelp.isEmpty()) return
        if (binding.autoCompleteTextView.isEmpty()) return

        val body = RegisterRequest(
            name = binding.editInputTextName.text.toString(),
            email = binding.editInputTextEmail.text.toString(),
            password = binding.editInputTextPassword.text.toString(),
            telp = binding.editInputTextTelp.text.toString(),
            level = binding.autoCompleteTextView.text.toString()
        )

        viewModel.registerAdmin(body).observe(this,{
            when(it.state){
                State.SUCCESS -> {
                    val level = binding.autoCompleteTextView.text.toString()
                    binding.progressBarRegister.visibility = View.GONE
                    toastSuccess("Add $level successfully")
                    startActivity(Intent(this, AdminActivity :: class.java))
                    finish()
                }
                State.ERROR -> {
                    binding.progressBarRegister.visibility = View.GONE
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    binding.progressBarRegister.visibility = View.VISIBLE
                }
            }
        })
    }
}