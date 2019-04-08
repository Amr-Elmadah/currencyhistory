package com.example.currencyhistory.injection

import com.example.currencyhistory.baseMVVM.BaseActivity
import com.example.currencyhistory.baseMVVM.BaseDialogFragment
import com.example.currencyhistory.baseMVVM.BaseFragment
import com.example.currencyhistory.dataSource.RepositorySourceModule
import com.example.currencyhistory.dataSource.remoteDataSource.RemoteDataSourceModule
import com.example.currencyhistory.injection.baseUrl.BaseUrlModule
import com.example.currencyhistory.injection.context.ContextModule
import com.example.currencyhistory.injection.modules.cacheModule.CacheModule
import com.example.currencyhistory.injection.modules.database.PrefModule
import com.example.currencyhistory.injection.modules.imageLoader.glideModule.GlideModule
import com.example.currencyhistory.injection.modules.jsonParser.gsonModule.GsonModule
import com.example.currencyhistory.injection.modules.okhttpclient.OkhttpClientModule
import com.example.currencyhistory.injection.modules.retrofitModule.RetrofitModule
import com.example.currencyhistory.ui.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
		modules = [
			BaseUrlModule::class,
			ContextModule::class,
			CacheModule::class,
			PrefModule::class,
			GlideModule::class,
			GsonModule::class,
			OkhttpClientModule::class,
			RetrofitModule::class,
			RepositorySourceModule::class,
			RemoteDataSourceModule::class
		]
)

interface AppComponent {
	fun inject(baseFragment: BaseFragment)
	fun inject(baseActivity: BaseActivity)
	fun inject(baseDialogFragment: BaseDialogFragment)
}
