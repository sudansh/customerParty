package com.sudansh.party.utils

import com.sudansh.party.data.local.entities.Constants
import com.sudansh.party.data.local.entities.Customer
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class DistanceUtils {

	companion object {

		/**
		 * Filters the customer list based on the lat,long
		 */
		fun filterCustomer(
			originLatitude: Double,
			originLongitude: Double,
			customers: List<Customer>
		): Map<Customer, Double> {
			return customers.map {
				it to getDistanceBetweenTwoPositions(
					originLatitude,
					originLongitude,
					it.latitude,
					it.longitude
				)
			}.toMap()
		}

		/**
		 * Calculate the distance between two points
		 * ref: Wikipedia
		 */
		private fun getDistanceBetweenTwoPositions(
			lat1: Double,
			long1: Double,
			lat2: Double,
			long2: Double
		): Double {
			val dLat = Math.toRadians((lat2 - lat1))
			val dLon = Math.toRadians(long2 - long1)
			val a = sin(dLat / 2) * sin(dLat / 2) +
					cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
					sin(dLon / 2) * sin(dLon / 2)
			val c = 2 * atan2(sqrt(a), sqrt(1 - a))
			return Constants.R * c
		}
	}
}