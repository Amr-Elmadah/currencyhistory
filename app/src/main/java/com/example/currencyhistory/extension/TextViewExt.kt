package com.example.currencyhistory.extension

import android.widget.TextView

val TextView.textString: String
	get() = text.toString()
