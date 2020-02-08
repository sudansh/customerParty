package com.sudansh.party.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.repository.CustomerRepository
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(CustomerRepository::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val mockCustomersMap = Observable.just(
        emptyMap<Customer, Double>()
    )

    private lateinit var mainViewModel: MainViewModel
    private lateinit var customerRepository: CustomerRepository

    @Mock
    lateinit var viewModelObserver: Observer<List<Customer>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        customerRepository = mock(CustomerRepository::class.java)
        mainViewModel = MainViewModel(customerRepository)
    }

    @Test
    fun testCustomerViewModelSubscription() {
        //given
        Mockito.`when`(customerRepository.filterCustomer(0.0, 0.0))
            .thenReturn(mockCustomersMap)
        mainViewModel.customers.observeForever(viewModelObserver)

        //when
        mainViewModel.fetchCustomers(0.0, 0.0, 0)

        //then
        assertNotNull(mainViewModel)
        verify(viewModelObserver).onChanged(arrayListOf())
    }
}