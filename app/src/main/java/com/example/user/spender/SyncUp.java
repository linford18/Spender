package com.example.user.spender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.Calendar;

/**
 * Created by user on 26-07-2020.
 */
public class SyncUp extends Activity {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH)+ 1;
    int year = calendar.get(Calendar.YEAR)%2000;
    ScrollView root;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.statistics);
        root=(ScrollView) findViewById(R.id.stats);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            root.setBackgroundResource(R.drawable.bag_cal_dark);
        } else {

            root.setBackgroundResource(R.drawable.bag_cal);
        }
        gridLayout=(GridLayout)findViewById(R.id.statsGrid);

        setSingleEvent(gridLayout);

    }

    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI) {
                        case 0:
                            Intent intent0 = new Intent(view.getContext(),BarChartActivity.class);
                            intent0.putExtra("Day",day);
                            intent0.putExtra("Month",(month));
                            intent0.putExtra("Year",year);
                            startActivity(intent0);
                            break;
                        case 1:
                            Intent intent1 = new Intent(view.getContext(),PieChartActivity.class);
                            intent1.putExtra("Day",day);
                            intent1.putExtra("Month",(month));
                            intent1.putExtra("Year",year);
                            startActivity(intent1);
                            break;
                        case 2:
                            Intent intent2 = new Intent(view.getContext(),RadarChartActivity.class);
                            intent2.putExtra("Day",day);
                            intent2.putExtra("Month",(month));
                            intent2.putExtra("Year",year);
                            startActivity(intent2);
                            break;
                        case 3:
                            Intent intent3 = new Intent(view.getContext(),CombinedChartActivity.class);
                            intent3.putExtra("Day",day);
                            intent3.putExtra("Month",(month));
                            intent3.putExtra("Year",year);
                            startActivity(intent3);
                            break;
                        case 4:
                            Intent intent4 = new Intent(view.getContext(),StackedBarActivity.class);
                            intent4.putExtra("Day",day);
                            intent4.putExtra("Month",(month));
                            intent4.putExtra("Year",year);
                            startActivity(intent4);
                            break;
                        case 5:
                            Intent intent5 = new Intent(view.getContext(),PreviewColumnChartActivity.class);
                            intent5.putExtra("Day",day);
                            intent5.putExtra("Month",(month));
                            intent5.putExtra("Year",year);
                            startActivity(intent5);
                            break;
                        case 6:
                            Intent intent6 = new Intent(view.getContext(),LineColumnDependencyActivity.class);
                            intent6.putExtra("Day",day);
                            intent6.putExtra("Month",(month));
                            intent6.putExtra("Year",year);
                            startActivity(intent6);
                            break;
                    }
                }
            });
        }
    }

}
