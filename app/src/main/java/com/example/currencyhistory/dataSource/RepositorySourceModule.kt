package com.example.currencyhistory.dataSource

import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSource
import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSourceModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RemoteDataSourceModule::class/*, LocalDataSourceModule::class*/])
class RepositorySourceModule {

	@Provides
	@Singleton
	fun providesRepositorySource(
			gson: Gson,
			remoteDataSource: RemoteDataSource
	): RepositorySource =
			DataSource(gson, remoteDataSource)
}