package com.example.lelangapps.ui.updateuser

import android.app.Activity
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.lelangapps.NavigationActivity
import com.example.lelangapps.R
import com.example.lelangapps.data.source.model.User
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.data.source.remote.request.UpdateProfilRequest
import com.example.lelangapps.databinding.ActivityUpdateUserBinding
import com.example.lelangapps.ui.profil.ProfilViewModel
import com.example.lelangapps.util.Prefs
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateUserBinding
    private val viewModel : UpdateUserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        mainButton()
//        setEditUser()
//        mainButtonUpdate()
    }

    private fun mainButton() {
        binding.buttonSave.setOnClickListener {
            updateProfil()
        }
    }

    private fun updateProfil() {
        if (binding.editInputTextName.isEmpty()) return
        if (binding.editInputTextTelp.isEmpty()) return
        if (binding.editInputTextEmail.isEmpty()) return

        val id_user = Prefs.getUser()?.user?.id
        val id = Prefs.getUser()?.user?.id
        val body = UpdateProfilRequest(
            name = binding.editInputTextName.text.toString(),
            email = binding.editInputTextEmail.text.toString(),
            telp = binding.editInputTextTelp.text.toString()
        )

        viewModel.updateProfil(Prefs.getUser()?.user?.id!!.toInt(),body).observe(this){
            Log.e("Update",body.toString())
            when (it.state) {
                State.SUCCESS -> {
                    binding.progressBarRegister.visibility = View.GONE
                    showToast("Update Profil Success")
                }
                State.ERROR -> {
                   binding.progressBarRegister.visibility = View.GONE
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    binding.progressBarRegister.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                editInputTextEmail.setText(user.user?.email)
                editInputTextName.setText(user.user?.name)
                editInputTextTelp.setText(user.user?.telp)
                tvNameInisial.text = user.user?.name.getInitial()
            }
        }
    }

    private fun setEditUser() {
        val dataUser = Prefs.getUser()
        binding.apply {
            editInputTextEmail.setText(dataUser?.user?.email)
            editInputTextName.setText(dataUser?.user?.name)
            editInputTextTelp.setText(dataUser?.user?.telp)
        }
        if (dataUser != null) {
            binding.tvNameInisial.text = dataUser.user?.name.getInitial()
        }
    }

    private fun mainButtonUpdate() {

        binding.buttonAddImage.setOnClickListener {
            getImage()
        }

        binding.buttonSave.setOnClickListener{
           // updateUser()
        }

    }

    private fun getImage() {
        ImagePicker.with(this)
            .maxResultSize(1080,1080,true)
            .provider(ImageProvider.BOTH) //Or bothCamereGallery
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                //Use the uri to load the image
                //Only if tou are not using crop featuere
                Picasso.get().load(uri).into(binding.imageViewProfil)
            }
        }

//    private fun updateUser() {
//
//        if (binding.editInputTextName.isEmpty() ) return
//        if (binding.editInputTextEmail.isEmpty()) return
//        if (binding.editInputTextTelp.isEmpty()) return
//
//        val idUser = Prefs.getUser()?.user?.id
//        val body = UpdateProfilRequest(
//            name = binding.editInputTextName.text.toString(),
//            telp = binding.editInputTextTelp.text.toString(),
//            email = binding.editInputTextEmail.text.toString()
//        )
//
//        viewModel.updateProfil(body).observe(this, {
//            when(it.state) {
//                State.SUCCESS -> {
//                    binding.progressBarRegister.visibility = View.GONE
//                    showToast("Edit Success")
//                    pushActivity(NavigationActivity::class.java)
//                    onBackPressedDispatcher.onBackPressed()
//                }
//                State.ERROR -> {
//                    binding.progressBarRegister.visibility = View.GONE
//                    toastError(it.message ?: "Error")
//                }
//                State.LOADING -> {
//                    binding.progressBarRegister.visibility = View.VISIBLE
//
//                }
//            }
//        })
//
//    }
}