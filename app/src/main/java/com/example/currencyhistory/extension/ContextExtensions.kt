package com.example.currencyhistory.extension

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat

val Context.statusBarHeightPreLollipop: Int
	get() =
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			throw IllegalAccessError("Use [ViewCompat.setOnApplyWindowInsetsListener] instead")
		} else {
			var result = 0
			val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
			if (resourceId > 0) {
				result = resources.getDimensionPixelSize(resourceId)
			}
			result
		}

val Context.navigationBarHeightPreLollipop: Int
	get() =
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			throw IllegalAccessError("Use [ViewCompat.setOnApplyWindowInsetsListener] instead")
		} else {
			var result = 0
			val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
			if (resourceId > 0) {
				result = resources.getDimensionPixelSize(resourceId)
			}
			result
		}

inline fun Context.getWindowInsetBottom(
		view: View, dispatch: Boolean,
		crossinline callback: (value: Int) -> Unit
) {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
		ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
			callback(insets.systemWindowInsetBottom)
			return@setOnApplyWindowInsetsListener if (dispatch) {
				val consumed = (v as ViewGroup).dispatchInsetsToChildren(insets)
				// If any of the children consumed the insets, return an appropriate value
				if (consumed) {
					insets.consumeSystemWindowInsets()
				} else insets
			} else {
				insets.consumeSystemWindowInsets()
			}
		}
	} else {
		callback(navigationBarHeightPreLollipop)
	}
}

inline fun Context.getWindowInsetTop(
		view: View, dispatch: Boolean,
		crossinline callback: (value: Int) -> Unit
) {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
		ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
			callback(insets.systemWindowInsetTop)
			return@setOnApplyWindowInsetsListener if (dispatch) {
				val consumed = (v as ViewGroup).dispatchInsetsToChildren(insets)
				// If any of the children consumed the insets, return an appropriate value
				if (consumed)
					insets.consumeSystemWindowInsets()
				else insets
			} else {
				insets.consumeSystemWindowInsets()
			}
		}
	} else {
		callback(statusBarHeightPreLollipop)
	}
}

fun Context.convertDpTpPx(value: Float): Float {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

fun Context.convertSpTpPx(value: Float): Float {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, resources.displayMetrics)
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(this, msg, duration).show()
}

fun Context.isNetworkConnected(): Boolean {
	val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
	return cm?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}