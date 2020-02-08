package com.sudansh.party.repository

import com.sudansh.party.TestUtils.mockCustomersList
import com.sudansh.party.TestUtils.mockCustomersMap
import com.sudansh.party.data.local.entities.Customer
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(CustomerRepository::class)
class CustomerRepositoryTest {

	private lateinit var customerRepository: CustomerRepository

	@Before
	fun setUp() {
		customerRepository = PowerMockito.mock(CustomerRepository::class.java)
	}

	@Test
	fun testGetCustomersRepository() {
		//given
		val testCUstomerObserver: TestObserver<List<Customer>> = TestObserver()
		Mockito.`when`(customerRepository.getCustomers()).thenReturn(
			Observable.just(
				mockCustomersList
			)
		)
		//when
		val customersMock = customerRepository.getCustomers()
		customersMock.subscribe(testCUstomerObserver)
		//then
		assertNotNull(customersMock)
		testCUstomerObserver.assertTerminated()
		testCUstomerObserver.assertValue(mockCustomersList)
		testCUstomerObserver.assertValueAt(0) { it[0].name == "Sudhanshu" }
		testCUstomerObserver.assertValueAt(0) { it[1].name == "Singh" }
	}

	@Test
	fun testCustomersMapHasDistances() {
		//given
		Mockito.`when`(customerRepository.filterCustomer(0.0, 0.0))
			.thenReturn(Observable.just(mockCustomersMap))
		//when
		val customersFiltered = customerRepository.filterCustomer(0.0, 0.0)
		//then
		assertNotNull(customersFiltered)
		customersFiltered.test().assertTerminated()
		customersFiltered.test().assertResult(mockCustomersMap)
		customersFiltered.test().assertValueAt(0) { it.containsValue(100.0) }
		customersFiltered.test()
			.assertValueAt(0) { it.containsKey(Customer(40.0, -59.0, 3, "FilterSudhanshu")) }
	}
}