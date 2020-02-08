package com.sudansh.party.data.local.entities

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Customer(
	val latitude: Double,
	val longitude: Double,
	@SerializedName("user_id") val userId: Long,
	val name: String
) {
	/**
	 * Provides a [DiffUtil.ItemCallback] class for use with the [Customer].
	 */
	class DiffCallback : DiffUtil.ItemCallback<Customer>() {
		override fun areItemsTheSame(oldItem: Customer, newItem: Customer) =
			oldItem.userId == newItem.userId

		override fun areContentsTheSame(oldItem: Customer, newItem: Customer) =
			oldItem == newItem
	}
}