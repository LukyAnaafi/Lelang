package com.example.lelangapps.ui.profil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lelangapps.NavigationActivity
import com.example.lelangapps.data.source.model.User
import com.example.lelangapps.data.source.model.dataUser
import com.example.lelangapps.databinding.FragmentProfilBinding
import com.example.lelangapps.ui.profil.admin.AdminActivity
import com.example.lelangapps.ui.profil.staff.AddItemAuctionActivity
import com.example.lelangapps.ui.updateuser.UpdateUserActivity
import com.example.lelangapps.util.Prefs
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.pushActivity
import com.squareup.picasso.Picasso

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
/*        val notificationsViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)*/

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root




        mainButton()



        //val textView: TextView = binding.textHome
        /*notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            doLogout(requireContext())
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }
        binding.buttonAddItem.setOnClickListener{
            val i = Intent(activity, AddItemAuctionActivity::class.java)
            activity?.startActivity(i)
        }
        binding.buttonEditProfile.setOnClickListener {
            val i = Intent(activity, UpdateUserActivity::class.java)
            activity?.startActivity(i)
        }

        binding.imageViewProfil.setOnClickListener {
            getImage()
        }

        binding.buttonToAdminPage.setOnClickListener {
            val i = Intent(activity, AdminActivity :: class.java)
            activity?.startActivity(i)
        }
    }

    private fun doLogout(context: Context) {
        val editor = context.getSharedPreferences("logout",Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

    private fun getImage() {
        ImagePicker.with(this.requireActivity())
            .maxResultSize(1080,1080,true)
            .provider(ImageProvider.BOTH) //Or bothCameraGallery()
            .createIntentFromDialog { launcher.launch(it) }
    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                // Use the uri to load the image
                // Only if you are not using crop feature:
                Picasso.get().load(uri).into(binding.imageViewProfil)

            }
        }

    private fun setUser() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                textViewNameProfil.text = user.user?.name
                textViewEmailProfil.text = user.user?.email
                textViewTelpProfil.text = user.user?.telp
                tvNameInisial.text = user.user?.name.getInitial()
                textViewWelcomeName.text = user.user?.name
            }
        }
        if (user?.user?.level == "administrator"){
            binding.buttonToAdminPage.visibility = View.VISIBLE
        }else{
            binding.buttonToAdminPage.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}