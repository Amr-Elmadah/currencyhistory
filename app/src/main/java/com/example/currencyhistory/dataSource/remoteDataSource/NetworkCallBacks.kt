package com.example.currencyhistory.dataSource.remoteDataSource

import com.example.currencyhistory.App

interface NetworkCallBacks {

	interface BaseNetworkCallBacks<OnSuccessReturnType> {
		fun onSuccess(result: OnSuccessReturnType)

		fun onError(err: String) {
			App.instance.showToast(err)
		}
	}
}