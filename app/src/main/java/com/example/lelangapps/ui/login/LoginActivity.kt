package com.example.lelangapps.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.lelangapps.NavigationActivity
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.data.source.remote.request.LoginRequest
import com.example.lelangapps.databinding.ActivityLoginBinding
import com.example.lelangapps.ui.register.RegisterActivity

import com.example.lelangapps.util.Prefs
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }


        mainButton()

        /*binding.buttonLogin.setOnClickListener {
            shar.setLogin(true)
        }*/


        /*val s = Prefs(this)
        if (s.getLogin()) {
            binding.textStatus.text = "Sudah Login"
        }else binding.textStatus.text = "Belum Login"

        binding.btnLogin.setOnClickListener {
            s.setLogin(true)
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLogout.setOnClickListener {
            s.setLogin(false)
            onBackPressedDispatcher.onBackPressed()
        }*/

    }

    private fun mainButton() {
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.textViewDaftar.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
    fun setData(){
    }

    private fun login() {

        if (binding.editTextEmail.isEmpty()) return
        if (binding.editTextPassword.isEmpty()) return

        val body = LoginRequest(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        )

        viewModel.login(body).observe(this, {

            when(it.state) {
                State.SUCCESS -> {
                    binding.loadingBar.visibility = View.GONE
                    showToast("Selamat Datang " + it.data?.user?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    binding.loadingBar.visibility = View.GONE
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    binding.loadingBar.visibility = View.VISIBLE
                }
            }
        })
    }
}