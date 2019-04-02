package com.example.currencyhistory.injection.modules.database

import android.content.Context
import com.example.currencyhistory.injection.context.ContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class PrefModule {

	private val PrefName = "CURRENCY_SHARED_PREF"
	@Provides
	@Singleton
	fun providesPref(context: Context) =
			context.getSharedPreferences(PrefName, Context.MODE_PRIVATE)!!
}