package com.example.lelangapps.data.source.model

import android.os.Parcelable
import com.example.lelangapps.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsAuction(
    val id_auction: Int,
    val id_staff : Int? = null,
    val date_open_auction: String? = null,
    val date_close_auction: String? = null,
    val status_item : String? = null,
    val image_item : String? = null,
    val item : ItemDat,
    val user : mUser
) : Parcelable
@Parcelize
data class ItemDat(
    val id_item: Int? = null,
    val item_name: String? = null,
    val open_price: Int? = null,
    val final_price: Int? = null,
    val description_item : String? = null,
): Parcelable
@Parcelize
data class mUser(
    val id : Int? = null,
    val name : String? = null,
    val image : String? = null,
    val final_price: Int? = null
): Parcelable




/* : Parcelable {
    //dummy
    companion object {
        fun getItemsAuction() = mutableListOf(
            ItemsAuction(R.drawable.bmw_images,"BMW i7 Injeksi","Rp.20.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.bmw_images,"BMW i7 Injeksi","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.bmw_images,"BMW i7 Injeksi","Rp.30.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.pit_image,"Sepeda Mamank Racing","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.bmw_images,"BMW i7 Injeksi","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.pit_image,"Sepeda Mamank Racing","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.pit_image,"Sepeda Mamank Racing","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),
            ItemsAuction(R.drawable.pit_image,"Sepeda Mamank Racing","Rp.21.000.00","Mobil ini akwkwkwkwkwkwkw","02.01.52"),

        )
    }
}*/

