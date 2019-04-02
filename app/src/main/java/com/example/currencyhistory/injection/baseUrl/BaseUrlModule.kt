package com.example.currencyhistory.injection.baseUrl

import com.example.currencyhistory.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class BaseUrlModule {

	object DAGGER_CONSTANTS {
		const val BASE_URL = "baseUrlString"
	}

	private object ApiEndpointsConstants {
		const val ProductionURL = "https://api.exchangeratesapi.io/"
		const val StagingURL = "https://api.exchangeratesapi.io"
	}

	@Provides
	@Singleton
	@Named(value = DAGGER_CONSTANTS.BASE_URL)
	fun providesBaseUrl() =
			if (BuildConfig.DEBUG) ApiEndpointsConstants.StagingURL else ApiEndpointsConstants.ProductionURL
}