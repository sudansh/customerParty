package com.sudansh.party.utils

import com.sudansh.party.data.local.entities.Customer
import io.reactivex.Observable
import java.io.InputStream

object FileUtils {

	fun parseCustomers(inputStream: InputStream): Observable<List<Customer>> {
		val customerList = arrayListOf<Customer>()
		inputStream.bufferedReader()
			.useLines { lines ->
				lines.forEach { line ->
					customerList.add(line.fromJson())
				}
			}

		return Observable.just(customerList)
	}
}