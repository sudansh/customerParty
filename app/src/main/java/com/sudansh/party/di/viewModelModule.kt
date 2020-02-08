package com.sudansh.party.di

import com.sudansh.party.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
	viewModel { MainViewModel(get()) }
}