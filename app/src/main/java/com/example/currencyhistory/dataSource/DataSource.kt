package com.example.currencyhistory.dataSource

import android.os.Handler
import android.os.Looper
import com.example.currencyhistory.dataSource.remoteDataSource.NetworkCallBacks
import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSource
import com.example.currencyhistory.model.Response
import com.google.gson.Gson
import kotlin.concurrent.thread

class DataSource constructor(
		private val mGson: Gson,
		private val mRemoteDataSource: RemoteDataSource
) : RepositorySource {

	private val TAG = "C.DataSource"

	private lateinit var mHandler: Handler
	private val mThreadLocal: Thread

	init {
		mThreadLocal = thread {
			//the looper is due to the fact that the
			//threads in java is design to run on job
			//for only one time, the looper will just
			// keep.. well, "looping" the thread.
			Looper.prepare()
			mHandler = Handler()
			Looper.loop()
		}
	}

	override fun clearDataBase() {
	}

	override fun getRateItem(date: String, base: String, symbols: String, callBacks: NetworkCallBacks.BaseNetworkCallBacks<Response>) {
		mHandler.post {
			mRemoteDataSource.getRateItem(date, base, symbols, callBacks)
		}
	}
}