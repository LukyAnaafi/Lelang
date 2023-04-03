package com.example.lelangapps.di

import com.example.lelangapps.ui.auction.AuctionViewModel
import com.example.lelangapps.ui.dashboard.DashboardViewModel
import com.example.lelangapps.ui.login.LoginViewModel
import com.example.lelangapps.ui.profil.admin.AdminViewModel
import com.example.lelangapps.ui.profil.admin.registeradmin.RegisterAdminViewModel
import com.example.lelangapps.ui.profil.staff.AddItemAuctionActivity
import com.example.lelangapps.ui.profil.staff.ItemAuctionViewModel
import com.example.lelangapps.ui.register.RegisterViewModel
import com.example.lelangapps.ui.updateuser.UpdateUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { UpdateUserViewModel(get())}
    viewModel { ItemAuctionViewModel(get())}
    viewModel { AuctionViewModel(get())}
    viewModel { DashboardViewModel(get())}
    viewModel { RegisterAdminViewModel(get())}
    viewModel { AdminViewModel(get(),get())}
}