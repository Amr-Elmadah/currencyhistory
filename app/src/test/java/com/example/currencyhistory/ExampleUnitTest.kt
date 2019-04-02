package com.example.currencyhistory

import com.example.currencyhistory.model.Response
import com.google.gson.Gson
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
	@Test
	fun parseTheResponse() {
		var json = "{\"base\":\"USD\",\"rates\":{\"EUR\":0.6905600442},\"date\":\"2010-01-12\"}"
		val obj = Gson().fromJson<Response>(json, Response::class.java)
		print(obj)
	}
}
