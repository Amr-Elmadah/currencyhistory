package com.example.currencyhistory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.currencyhistory.R
import com.example.currencyhistory.baseMVVM.BaseFragment
import com.example.currencyhistory.util.formatDate
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


class MainFragment : BaseFragment() {

	private val viewModel by lazy {
		ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		lifecycle.addObserver(viewModel)
		return inflater.inflate(R.layout.main_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		val today = Calendar.getInstance()

		configureChart()

		for (i in 0 until 8) {
			viewModel.getRateItem(formatDate(today.time))
			today.add(Calendar.DAY_OF_WEEK, -1)
		}

		viewModel.notifyUpdate.observe(this, Observer {
			val entries = ArrayList<Entry>()
			for ((i, data) in viewModel.currencyList.withIndex()) {
				entries.add(Entry(i.toFloat(), data.rates?.eUR!!.toFloat(), today.time))
			}
			val dataSet = LineDataSet(entries, "USD to EUR") // add entries to dataset
			dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER)
			dataSet.setDrawFilled(true);

			val lineData = LineData(dataSet)
			chart.data = lineData
			chart.notifyDataSetChanged()
			chart.invalidate(); // refresh

		})
	}

	private fun configureChart() {
		chart.setDrawGridBackground(false)
		chart.description.isEnabled = false
		val xAxisFormatter = DayAxisValueFormatter(chart)

		val xAxis = chart.xAxis
		xAxis.position = XAxisPosition.BOTTOM
		xAxis.setDrawGridLines(false)
		xAxis.granularity = 1f // only intervals of 1 day
		xAxis.labelCount = 8
		xAxis.valueFormatter = xAxisFormatter
	}

	companion object {
		fun newInstance() = MainFragment()
	}
}