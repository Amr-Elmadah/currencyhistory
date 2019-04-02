package com.example.currencyhistory.dataSource.remoteDataSource

import com.example.currencyhistory.model.Response

interface RemoteDataSource {
	fun getRateItem(date: String, base: String = "USD", symbols: String = "EUR", callBacks: NetworkCallBacks.BaseNetworkCallBacks<Response>)
}