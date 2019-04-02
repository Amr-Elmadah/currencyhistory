package com.example.currencyhistory.dataSource.remoteDataSource.retrofit

import com.example.currencyhistory.dataSource.remoteDataSource.NetworkCallBacks
import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSource
import com.example.currencyhistory.dataSource.remoteDataSource.retrofit.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceUsingRetrofit(private var apiClient: ApiService) : RemoteDataSource {

	class DefaultRetrofitHandler<ResponseType> constructor(private val callbacks: NetworkCallBacks.BaseNetworkCallBacks<ResponseType>) :
			Callback<ResponseType> {

		override fun onResponse(call: Call<ResponseType>?, response: Response<ResponseType>) {
			if (response.isSuccessful) {
				callbacks.onSuccess(response.body()!!)
			} else {
				callbacks.onError(response.errorBody()?.string()!!)
			}
		}

		override fun onFailure(call: Call<ResponseType>?, t: Throwable) {
			try {
				callbacks.onError(t.message!!)
			} catch (e: Exception) {
				callbacks.onError("Unknown error")
			}

			t.printStackTrace()
		}
	}

	override fun getRateItem(date: String, base: String, symbols: String, callBacks: NetworkCallBacks.BaseNetworkCallBacks<com.example.currencyhistory.model.Response>) {
		apiClient.getRateItem(date, base, symbols).enqueue(DefaultRetrofitHandler(callBacks))
	}
}