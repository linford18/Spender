package com.example.user.spender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by user on 26-07-2020.
 */
public class GraphFirst extends Activity {
    Button btnBarChart, btnPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_graph_one);
        BarChart barChart = (BarChart) findViewById(R.id.fragment_verticalbarchart_chart);

        btnBarChart = (Button) findViewById(R.id.btnBarChart);
        btnPieChart =(Button) findViewById(R.id.btnPieChart);
        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), BarChartActivity.class);
                startActivity(I);
            }
        });
        btnPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), PieChartActivity.class);
                startActivity(I);
            }
        });
    }
}