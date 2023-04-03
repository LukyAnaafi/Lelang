package com.example.lelangapps.ui.register

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lelangapps.NavigationActivity
import com.example.lelangapps.R
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.data.source.remote.request.RegisterRequest
import com.example.lelangapps.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val viewModel : RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    private fun mainButton() {
        binding.buttonRegister.setOnClickListener {
            register()
        }
        binding.iconBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun register() {

        if (binding.editInputTextName.isEmpty()) return
        if (binding.editInputTextTelp.isEmpty()) return
        if (binding.editInputTextEmail.isEmpty()) return
        if (binding.editInputTextPassword.isEmpty()) return

        val body = RegisterRequest(
            binding.editInputTextName.text.toString(),
            binding.editInputTextTelp.text.toString(),
            binding.editInputTextEmail.text.toString(),
            binding.editInputTextPassword.text.toString()
        )

        viewModel.register(body).observe(this, {

            when(it.state) {
                State.SUCCESS -> {
                    binding.progressBarRegister.visibility = View.GONE
                    showToast("Selamat Datang "+ it.data?.user?.name)
                    pushActivity(NavigationActivity::class.java)
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