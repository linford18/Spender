package com.example.user.spender;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TheCalendar extends Activity{
    CalendarView calendarView;
    public int day,m=0,y=0;
    String monthString;
    TextView the_day,the_month;
    RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.CalenderViewCustom);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.calendar);
        root=(RelativeLayout)findViewById(R.id.calendarLayout);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            root.setBackgroundResource(R.drawable.bag_cal_dark);
        } else {

            root.setBackgroundResource(R.drawable.bag_cal);
        }
        Intent intent=getIntent();

        day=intent.getIntExtra("Day",-1);
        m=intent.getIntExtra("Month",-1) ;

        the_day =(TextView) findViewById(R.id.theDate);

        the_month=(TextView) findViewById(R.id.theMonth);
        the_day.setText(String.valueOf(day));
        the_month.setText(monthname(m));

        calendarView=(CalendarView) findViewById(R.id.thecalendarView);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {


            calendarView.setDateTextAppearance(R.style.CalenderViewDateCustomText);
            calendarView.setWeekDayTextAppearance(R.style.CalenderViewWeekCustomText);
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                day=dayOfMonth;
                y=year;
                m=month;
                Log.d("The calendar: month:",String.valueOf(month));
                the_day.setText(String.valueOf(day));
                the_month.setText(monthname(m+1));

                Intent intent = new Intent(getApplicationContext(),DaysDetail.class);
                intent.putExtra("Day",dayOfMonth);
                intent.putExtra("Month",(month+1));
                intent.putExtra("Year",year%2000);
                startActivity(intent);
            }

        });



    }
    String monthname(int m){
        String monthString;
        switch (m) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return (monthString);
    }

}
