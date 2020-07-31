package com.example.user.spender;

/**
 * Created by user on 26-07-2020.
 */
import android.app.Activity;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.ArrayList;
import java.util.Random;

public class BarChartActivity extends Activity {
    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 50;
    private static final int MIN_Y_VALUE = 5;
    private static final String SET_LABEL = "Average Temperature";
    private static final String[] DAYS = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
    private DecimalFormat mFormat;
    LinearLayout root;
    GridLayout gridLayout;

    private BarChart chart; //=(BarChart) findViewById(R.id.fragment_verticalbarchart_chart);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_bar_chart);
        root=(LinearLayout) findViewById(R.id.bar_bg);

        chart =(BarChart) findViewById(R.id.fragment_verticalbarchart_chart);
        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);

//        ArrayList NoOfEmp = new ArrayList();
//
//        NoOfEmp.add(new BarEntry(945f, 0));
//        NoOfEmp.add(new BarEntry(1040f, 1));
//        NoOfEmp.add(new BarEntry(1133f, 2));
//        NoOfEmp.add(new BarEntry(1240f, 3));
//        NoOfEmp.add(new BarEntry(1369f, 4));
//        NoOfEmp.add(new BarEntry(1487f, 5));
//        NoOfEmp.add(new BarEntry(1501f, 6));
//        NoOfEmp.add(new BarEntry(1645f, 7));
//        NoOfEmp.add(new BarEntry(1578f, 8));
//        NoOfEmp.add(new BarEntry(1695f, 9));
//
//
//        ArrayList<String> year = new ArrayList<>();
//
//        year.add("2008");
//        year.add("2009");
//        year.add("2010");
//        year.add("2011");
//        year.add("2012");
//        year.add("2013");
//        year.add("2014");
//        year.add("2015");
//        year.add("2016");
//        year.add("2017");
//        ArrayList<IBarDataSet> dataSets = null;
//
//        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
//        BarEntry v1e1 = new BarEntry(110.000f,0); //Kunjungan Pasien Bulan 1
//        valueSet1.add(v1e1);
//        BarEntry v1e2 = new BarEntry(40.000f,0); //Kunjungan Pasien Bulan 2
//        valueSet1.add(v1e2);
//        BarEntry v1e3 = new BarEntry(60.000f,0); //Kunjungan Pasien Bulan 3
//        valueSet1.add(v1e3);
//        BarEntry v1e4 = new BarEntry(100.000f,0); //Kunjungan Pasien Bulan 4
//        valueSet1.add(v1e4);
//
//        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
//        BarEntry v2e1 = new BarEntry(150.000f,0); //Klinis Pasien Bulan 1
//        valueSet2.add(v2e1);
//        BarEntry v2e2 = new BarEntry(90.000f,0); //Klinis Pasien Bulan 2
//        valueSet2.add(v2e2);
//        BarEntry v2e3 = new BarEntry(60.000f,0); //Klinis Pasien Bulan 3
//        valueSet2.add(v2e3);
//        BarEntry v2e4 = new BarEntry(80.000f,0); //Klinis Pasien Bulan 4
//        valueSet2.add(v2e4);
//
//        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Pasien 1");
//        barDataSet1.setColor(Color.rgb(0, 155, 0));
//        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Pasien 2");
//        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
//
//        dataSets = new ArrayList<>();
//        dataSets.add(barDataSet1);
//        dataSets.add(barDataSet2);
//
////        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
//        chart.animateY(5000);
//        BarData data = new BarData(year,dataSets);
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        chart.setData(data);
    }


//    protected BarData generateBarData(int dataSets, float range, int count) {
//
//        ArrayList<IBarDataSet> sets = new ArrayList<>();
//
//        for(int i = 0; i < dataSets; i++) {
//
//            ArrayList<BarEntry> entries = new ArrayList<>();
//
//            for(int j = 0; j < count; j++) {
//                entries.add(new BarEntry(j, (float) (Math.random() * range) + range / 4));
//            }
//
//            BarDataSet ds = new BarDataSet(entries,  "New DataSet " + String.valueOf(i));
//            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
//            sets.add(ds);
//        }
//
//        BarData d = new BarData(sets);
//        d.setValueTypeface(tf);
//        return d;
//    }

    private void configureChartAppearance() {
        chart.getDescription().setEnabled(false);

        chart.setDrawValueAboveBar(false);

        final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; // Your List / array with String Values For X-axis Labels

// Set the value formatter
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));

//        XAxis xAxis = chart.getXAxis();
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return DAYS[(int) value];
//            }
//        });

        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setGranularity(10f);
        axisLeft.setAxisMinimum(0);

        YAxis axisRight = chart.getAxisRight();
        axisRight.setGranularity(10f);
        axisRight.setAxisMinimum(0);
    }

    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < MAX_X_VALUE; i++) {
            float x = i;
            float y = randomFloatBetween(MIN_Y_VALUE, MAX_Y_VALUE);
            values.add(new BarEntry(x, y));
        }

        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        return data;
    }

    private void prepareChartData(BarData data) {
        data.setValueTextSize(12f);
        chart.setData(data);
        chart.invalidate();
    }
    public float randomFloatBetween(float min, float max) {
        Random r = new Random();
        float random = min + r.nextFloat() * (max - min);
        return random;
    }

}