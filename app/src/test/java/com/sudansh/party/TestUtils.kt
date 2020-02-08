package com.sudansh.party

import com.sudansh.party.data.local.entities.Customer

object TestUtils {
	val mockCustomersList = arrayListOf(
		Customer(19.0, -90.0, 1, "Sudhanshu"),
		Customer(20.0, -70.0, 2, "Singh")
	)
	val mockCustomersMap = mapOf(
		Customer(40.0, -59.0, 3, "FilterSudhanshu") to 100.0,
		Customer(40.0, -75.0, 4, "FilterSingh") to 200.0
	)
}