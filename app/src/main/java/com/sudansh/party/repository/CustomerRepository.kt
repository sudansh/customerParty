package com.sudansh.party.repository

import com.sudansh.party.data.local.entities.Customer
import com.sudansh.party.data.network.CustomerApi
import com.sudansh.party.utils.DistanceUtils
import com.sudansh.party.utils.FileUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CustomerRepository(private val customerApi: CustomerApi) {

	/**
	 * Fetch customer list from the network
	 */
	fun getCustomers(): Observable<List<Customer>> {
		return customerApi.getCustomers()
			.toObservable()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.flatMap { return@flatMap FileUtils.parseCustomers(it.byteStream()) }
	}

	fun filterCustomer(
		lat: Double,
		long: Double
	): Observable<Map<Customer, Double>> {
		return getCustomers()
			.flatMap {
				return@flatMap Observable.just(
					DistanceUtils.filterCustomer(lat, long, it)
				)
			}
	}
}