package com.example.currencyhistory.injection.modules.cacheModule

import android.content.Context
import com.example.currencyhistory.injection.context.ContextModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class CacheModule {
	private val cacheSize = 50 * 1024 * 1024 // 50 MB

	@Provides
	@Singleton
	fun providesCache(appContext: Context) = Cache(appContext.cacheDir, cacheSize.toLong())
}