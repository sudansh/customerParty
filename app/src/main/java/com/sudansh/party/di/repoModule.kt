package com.sudansh.party.di

import com.sudansh.party.repository.CustomerRepository
import org.koin.dsl.module

val repositoryModule = module {
	single { CustomerRepository(get()) }
}