package com.sudansh.party.util

import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.utils.DistanceUtils
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test

class DistanceUtilsTest {

	companion object {
		const val DISTANCE = 50
		const val DUBAI_LATITUDE = 24.0414478
		const val DUBAI_LONGITUDE = 55.5649088
	}

	@Test
	fun `test customers from dubai`() {
		// given
		val customers = arrayListOf(
			Customer(24.9865, 55.1629, 100, "Sudhanshu"),
			Customer(24.9968, 55.11723, 200, "Amit"),
			Customer(24.5933, 55.25142, 300, "Verma")
		)
		// when
		val customersMap =
			DistanceUtils.filterCustomer(
				DUBAI_LATITUDE,
				DUBAI_LONGITUDE, customers
			)
		// then
		val result = customersMap.filterValues { it < DISTANCE }
		assertNotNull(result)
		assertEquals(result.size, 0)
	}

}