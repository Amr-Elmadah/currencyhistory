package com.example.currencyhistory.model

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RatesTest {
	private lateinit var json: String
	private lateinit var obj: Response

	@Before
	fun setup() {
		json = "{\"base\":\"USD\",\"rates\":{\"EUR\":0.6905600442},\"date\":\"2010-01-12\"}"
		obj = Gson().fromJson<Response>(json, Response::class.java)
	}

	@Test
	fun checkBaseNotNullOrEmpty() {
		Assert.assertTrue(!obj.base.isNullOrBlank())
	}

	@Test
	fun checkBaseIsUSD() {
		Assert.assertTrue(obj.base.equals("USD"))
	}

	@Test
	fun checkRatesNotNull() {
		Assert.assertTrue(obj.rates != null)
	}

	@Test
	fun checkDateNotNullOrEmpty() {
		Assert.assertTrue(!obj.date.isNullOrBlank())
	}
}
