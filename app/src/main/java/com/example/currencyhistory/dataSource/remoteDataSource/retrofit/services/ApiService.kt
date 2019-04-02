package com.example.currencyhistory.dataSource.remoteDataSource.retrofit.services

import com.example.currencyhistory.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("{date}")
	fun getRateItem(@Path("date") date: String, @Query("base") base: String = "USD", @Query("symbols") symbols: String = "EUR"): Call<Response>
}