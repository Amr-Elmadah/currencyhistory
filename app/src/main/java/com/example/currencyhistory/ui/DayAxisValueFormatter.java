package com.example.currencyhistory.ui;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Calendar;
import java.util.Locale;

public class DayAxisValueFormatter extends ValueFormatter {

    private final BarLineChartBase<?> chart;

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value) {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DAY_OF_MONTH, (int) value);

        String monthName = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String yearName = c.getDisplayName(Calendar.YEAR, Calendar.LONG, Locale.getDefault());

        if (chart.getVisibleXRange() > 30 * 6) {

            return monthName + " " + yearName;
        } else {

            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            String appendix = "th";

            switch (dayOfMonth) {
                case 1:
                    appendix = "st";
                    break;
                case 2:
                    appendix = "nd";
                    break;
                case 3:
                    appendix = "rd";
                    break;
                case 21:
                    appendix = "st";
                    break;
                case 22:
                    appendix = "nd";
                    break;
                case 23:
                    appendix = "rd";
                    break;
                case 31:
                    appendix = "st";
                    break;
            }

            return dayOfMonth == 0 ? "" : dayOfMonth + appendix + " " + monthName;
        }
    }
}
