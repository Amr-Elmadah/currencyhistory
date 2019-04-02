package com.example.currencyhistory.util

import android.content.res.Resources
import android.graphics.Bitmap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date = Calendar.getInstance().time, format: String = "YYYY-MM-dd"): String {
	val df = SimpleDateFormat(format, Locale.getDefault())
	return df.format(date)
}

inline fun <reified T> Gson.fromJson(json: String) =
		this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Int.dpToPx(): Float {
	return this * Resources.getSystem().displayMetrics.density
}

fun getCurrencySymbol(currencyCode: String): String {
	return Currency.getInstance(currencyCode).symbol
}

fun Bitmap.toByteArray(quality: Int = 100): ByteArray {
	val stream = ByteArrayOutputStream()
	compress(Bitmap.CompressFormat.JPEG, quality, stream)
	return stream.toByteArray()
}

fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
	// haversine great circle distance approximation, returns meters
	val theta = lon1 - lon2
	var dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + (Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
			* Math.cos(deg2rad(theta)))
	dist = Math.acos(dist)
	dist = rad2deg(dist)
	dist *= 60 // 60 nautical miles per degree of seperation
	dist *= 1852 // 1852 meters per nautical mile
	return dist
}

private fun deg2rad(deg: Double): Double {
	return deg * Math.PI / 180.0
}

private fun rad2deg(rad: Double): Double {
	return rad * 180.0 / Math.PI
}

