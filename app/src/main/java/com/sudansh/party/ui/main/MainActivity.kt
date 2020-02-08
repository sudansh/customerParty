package com.sudansh.party.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sudansh.party.R
import com.sudansh.party.data.local.entities.Constants
import com.sudansh.party.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

	private val mainViewModel by viewModel<MainViewModel>()

	private lateinit var binding: ActivityMainBinding
	private lateinit var listAdapter: CustomerListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		listAdapter = CustomerListAdapter(this@MainActivity)
		binding.rvCustomers.apply {
			adapter = listAdapter
			setHasFixedSize(true)
		}
		observeCustomers()
		addListeners()
	}

	private fun observeCustomers() {
		mainViewModel.isLoading.observe(this, Observer {
			binding.progressBar.visibility = if (it == true) View.VISIBLE else View.GONE
		})
		mainViewModel.error.observe(this, Observer {
			Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
		})
		mainViewModel.customers.observe(this, Observer {
			listAdapter.setData(it)
		})
	}

	private fun addListeners() {
		binding.btnFetchCustomers.setOnClickListener {
			mainViewModel.fetchCustomers(
				Constants.DUBLIN_LATITUDE,
				Constants.DUBLIN_LONGITUDE,
				Constants.DISTANCE
			)
		}
	}
}
