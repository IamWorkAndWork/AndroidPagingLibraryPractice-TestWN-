package com.example.testwn.di

import com.example.testwn.ui.coins_list.CoinsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CoinsListViewModel(get())
    }

}