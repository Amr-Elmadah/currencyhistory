package com.example.currencyhistory.ui

import android.os.Bundle
import com.example.currencyhistory.R
import com.example.currencyhistory.baseMVVM.BaseActivity
import com.example.currencyhistory.extension.addFragment

class MainActivity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		addFragment(MainFragment.newInstance(), R.id.main_container)
	}
}
