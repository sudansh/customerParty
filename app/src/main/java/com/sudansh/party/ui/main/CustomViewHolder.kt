package com.sudansh.party.ui.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.databinding.CustomerListRowBinding

class CustomViewHolder(private val binding: CustomerListRowBinding) :
	RecyclerView.ViewHolder(binding.root) {

	fun bind(customer: Customer) {
		binding.customer = customer
		binding.executePendingBindings()
		binding.container.setOnClickListener {
			try {
				val gmmIntentUri =
					Uri.parse("geo:${customer.latitude},${customer.longitude}")

				val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
				mapIntent.setPackage("com.google.android.apps.maps")
				binding.root.context.startActivity(mapIntent)
			} catch (e: Exception) {
				Log.e("CustomViewHolder", "Maps not found")
			}
		}
	}

}