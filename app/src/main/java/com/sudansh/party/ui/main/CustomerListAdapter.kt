package com.sudansh.party.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.databinding.CustomerListRowBinding

class CustomerListAdapter(context: Context) :
	ListAdapter<Customer, CustomViewHolder>(Customer.DiffCallback()) {

	private val layoutInflater = LayoutInflater.from(context)
	private var customersList: List<Customer> = emptyList()

	override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
		holder.bind(customersList[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
		val binding = CustomerListRowBinding.inflate(layoutInflater, parent, false)
		return CustomViewHolder(binding)
	}

	override fun getItemCount(): Int = customersList.size

	fun setData(list: List<Customer>) {
		customersList = list
		notifyDataSetChanged()
	}
}