package com.sudansh.party

import android.app.Application
import com.sudansh.party.di.networkModule
import com.sudansh.party.di.repositoryModule
import com.sudansh.party.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			modules(
				listOf(
					networkModule,
					repositoryModule,
					viewModelsModule
				)
			)
		}
	}
}