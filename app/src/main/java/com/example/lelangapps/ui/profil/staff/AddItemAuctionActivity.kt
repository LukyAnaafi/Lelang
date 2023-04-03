package com.example.lelangapps.ui.profil.staff

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lelangapps.data.source.remote.network.State
import com.example.lelangapps.databinding.ActivityAddItemAuctionBinding
import com.example.lelangapps.util.Prefs
//import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddItemAuctionActivity : AppCompatActivity() {

    private lateinit var bindind : ActivityAddItemAuctionBinding
    private val viewModel : ItemAuctionViewModel by viewModel()
    private var fileImage : File? = null
    private var uriImage : Uri? = null
    private val requestGallery = 2121


    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri ->
        if (uri != null){
            val file = getFileFromUri(uri)
            if (file == null) {
                Toast.makeText(this,"File not found",Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityAddItemAuctionBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        datePickAuctionClose()
        mainButton()

//        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        pickIntent.type = "image/*"

        bindind.apply {
            buttonAddImage.setOnClickListener {
                picImage()
                //ImagePicker.with(this@AddItemAuctionActivity).start(requestGallery)
            }

        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (Activity.RESULT_OK == requestGallery && data != null){
//            val fileUri = data.data
//            setImage(fileUri!!)
//            ImagePicker.getFile(data)?.let { create(it, fileUri) }
//        }
//
//    }

    private fun doRequest(file: File,uri: Uri) {
        val requestFile = file.asRequestBody(contentResolver.getType(uri)?.toMediaTypeOrNull())
        val bodyImage = MultipartBody.Part.createFormData("image",file.name,requestFile)
    }

    private fun setImage(uri: Uri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
            val source = ImageDecoder.createSource(contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source)
            bindind.apply {
                imageInsertAuction.setImageBitmap(bitmap)
            }
        }else {
            @Suppress("DEPRECATION") val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            bindind.apply {
                imageInsertAuction.setImageBitmap(bitmap)
            }
        }
    }

    private fun getFileFromUri(uri : Uri):File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null,null,null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = cursor?.getString(columnIndex!!)
        cursor?.close()
        return filePath?.let { File(it) }
    }

    private fun mainButton() {
        bindind.buttonAddItem.setOnClickListener {

            if (validate() == true) {
                create()
            }
        }
//        bindind.buttonAddImage.setOnClickListener {
//           picImage()
//            /*pickMedia.launch(
//                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
//            )*/
//        }

    }

    private fun picImage() {
        com.github.drjacky.imagepicker.ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data!!
            //val fileImg  = MultipartBody.Part.createFormData(fileImage!!.name,fileImage!!.path,fileImage.asRequestBody("image/*".toMediaType()))
            //Use the uri to load the image


            fileImage = File(uri.path!!)
            Picasso.get().load(uri).into(bindind.imageInsertAuction)
           // fileImage!!.asRequestBody("image/*".toMediaType())
        }


    }

//    private fun uploadImage(uri: Uri) {
//        lifecycleScope.launch {
//            val stream = contentResolver.openInputStream(uri) ?: return@launch
//            val request =
//                stream.readBytes().toRequestBody("image/*".toMediaTypeOrNull(), 0, content.size)
//            val filePart = MultipartBody.Part.createFormData(
//                "file",
//                "test.jpg",
//                request
//            )
//
//        }
//    }

    private fun validate(): Boolean {
        bindind.apply {
            if (editTextAddItemName.isEmpty()) return false
            if (editTextAddOpenPriceItem.isEmpty()) return false
            if (editTextAddDescriptionText.isEmpty()) return false
            if (editTextAddDateClosed.isEmpty()) return false

            return true
        }
    }

     fun create(/*file: File,uri: Uri*/) {
//        val requestFile = file.asRequestBody(contentResolver.getType(uri)?.toMediaTypeOrNull())
//        val bodyImage = MultipartBody.Part.createFormData("image",file.name,requestFile)
         val fileImg = fileImage.toMultipartBody()

        val imageFile = ""

        /*val reqData = CreateItemAucRequest(
            image_item = bindind.editTextAddItemName.text.toString(),
            name_item = bindind.editTextAddItemName.getString(),
            opening_price = bindind.editTextAddOpenPriceItem.text.toString(),
            description_item = bindind.editTextAddDescriptionText.text.toString(),
            date_close_auction = bindind.editTextAddDateClosed.text.toString(),


            )*/

        //MultipartBody.Part.createFormData(
          //  "jpg",
           // fileImage!!.name,
            //fileImage!!.asRequestBody("image/*".toMediaType())
        //)

         if (fileImg != null) {
             val requestImageFile =
                 fileImage?.asRequestBody("image/jpg".toMediaTypeOrNull())
             val imageMultipart = requestImageFile?.let { it1 ->
                 MultipartBody.Part.createFormData(
                     "image_item",
                     fileImage?.name,
                     it1
                 )
             }
             viewModel.create(
                 "Bearer "+Prefs.token,
                 image_item = imageMultipart,
                 name_item = bindind.editTextAddItemName.text.toString().toRequestBody("text/plain".toMediaType()),
                 opening_price = bindind.editTextAddOpenPriceItem.text.toString().toRequestBody("text/plain".toMediaType()),
                 description_item = bindind.editTextAddDescriptionText.text.toString().toRequestBody("text/plain".toMediaType()),
                 date_close_auction = bindind.editTextAddDateClosed.text.toString().toRequestBody("text/plain".toMediaType())
             ).observe(this) {
                 when(it.state) {
                     State.SUCCESS -> {
                         bindind.progressBarRegister.visibility = View.GONE
                         toastSuccess("Add Item Successfuly")
                         onBackPressedDispatcher.onBackPressed()
                     }
                     State.ERROR -> {
                         bindind.progressBarRegister.visibility = View.GONE
                         showErrorDialog("You don't have permission")
                     }
                     State.LOADING -> {
                         bindind.progressBarRegister.visibility = View.VISIBLE
                     }
                 }
             }
         }
    }

    private fun datePickAuctionClose() {
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        bindind.btnDatePicekAuctionClosed.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    /*private fun uploadFile(file: File): String? {

    }*/

    private fun updateLable(myCalendar: Calendar) {
       val myFormat = "dd-MM-yyyy"
       val sdf = SimpleDateFormat(myFormat, Locale.UK)
       bindind.editTextAddDateClosed.setText(sdf.format(myCalendar.time))
    }


}