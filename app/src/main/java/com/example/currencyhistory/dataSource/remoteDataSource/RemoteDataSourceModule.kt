package com.example.currencyhistory.dataSource.remoteDataSource

import com.example.currencyhistory.dataSource.remoteDataSource.retrofit.RemoteDataSourceUsingRetrofit
import com.example.currencyhistory.dataSource.remoteDataSource.retrofit.services.ApiService
import com.example.currencyhistory.dataSource.remoteDataSource.retrofit.services.ServicesModule
import com.example.currencyhistory.injection.modules.jsonParser.gsonModule.GsonModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServicesModule::class, GsonModule::class])
class RemoteDataSourceModule {
	@Provides
	@Singleton
	fun provideRemoteDataSource(
			apiClient: ApiService
	): RemoteDataSource = RemoteDataSourceUsingRetrofit(
			apiClient
	)
}