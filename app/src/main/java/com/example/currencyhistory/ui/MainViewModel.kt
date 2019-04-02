package com.example.currencyhistory.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyhistory.dataSource.RepositorySource
import com.example.currencyhistory.dataSource.pref.AppPreferencesHelper
import com.example.currencyhistory.dataSource.remoteDataSource.NetworkCallBacks
import com.example.currencyhistory.model.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mRepo: RepositorySource, private val mSharedPrf: AppPreferencesHelper) : ViewModel(), LifecycleObserver {

	val currencyList = mutableListOf<Response>()
	val notifyUpdate = MutableLiveData<Boolean>()

	fun getRateItem(date: String) {
		mRepo.getRateItem(date, callBacks = object : NetworkCallBacks.BaseNetworkCallBacks<Response> {
			override fun onSuccess(result: Response) {
				currencyList.add(result)
				notifyUpdate.value = true
			}
		})
	}

}
