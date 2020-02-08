package com.sudansh.party.data.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface CustomerApi {

	@GET("intercom-take-home-test/customers.txt")
	fun getCustomers(): Single<ResponseBody>
}