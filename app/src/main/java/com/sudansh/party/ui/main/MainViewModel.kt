package com.sudansh.party.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.repository.CustomerRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val customerRepository: CustomerRepository) : ViewModel() {

	val customers: MutableLiveData<List<Customer>> = MutableLiveData()
	val isLoading = MutableLiveData<Boolean>(false)
	val error = MutableLiveData<String>()
	private val compositeDisposable = CompositeDisposable()

	fun fetchCustomers(lat: Double, long: Double, distance: Int) {
		isLoading.postValue(true)
		compositeDisposable
			.add(customerRepository
					 .filterCustomer(lat, long)
					 .flatMap { customerMapWithDistance ->
						 return@flatMap Observable.just(
							 customerMapWithDistance.filterValues { it <= distance })
					 }
					 .flatMap { customerMapFilteredByDistance ->
						 return@flatMap Observable.just(
							 customerMapFilteredByDistance.map { it.key })
					 }
					 .map { nearbyCustomers -> nearbyCustomers.sortedBy { it.userId } }
					 .subscribe({ nearbySortedCustomersByUserId ->
									customers.value =
										nearbySortedCustomersByUserId
									isLoading.postValue(false)
								}, {
									it.printStackTrace()
									error.postValue("Something went wrong. Please try again.")
									isLoading.postValue(false)
								})
			)
	}

	override fun onCleared() {
		super.onCleared()
		compositeDisposable.clear()
	}
}