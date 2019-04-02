package com.example.currencyhistory

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.currencyhistory.baseMVVM.ActivityLifecycleHandler
import com.example.currencyhistory.injection.AppComponent
import com.example.currencyhistory.injection.DaggerAppComponent
import com.example.currencyhistory.injection.context.ContextModule

@SuppressLint("ShowToast")
class App : Application() {
	lateinit var component: AppComponent
	private var mToast: Toast? = null

	override fun onCreate() {
		super.onCreate()
		instance = this

		mToast = Toast.makeText(applicationContext, null, Toast.LENGTH_LONG)

		component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
		registerActivityLifecycleCallbacks(ActivityLifecycleHandler())
	}

	fun showToast(text: String) {
		if (mToast != null) {
			mToast?.cancel()
		}
		mToast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
		mToast?.show()
	}

	companion object {
		@SuppressLint("StaticFieldLeak")
		@get:Synchronized
		lateinit var instance: App
			private set
	}
}