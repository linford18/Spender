package com.example.user.spender;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;


public class TheCalendar extends Activity{
    CalendarView calendarView;
    public int day,m=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendarView=(CalendarView) findViewById(R.id.thecalendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                day=dayOfMonth;
                y=year;
                m=month;
                Log.d("The calendar: month:",String.valueOf(month));

                Intent intent = new Intent(getApplicationContext(),DaysDetail.class);
                intent.putExtra("Day",dayOfMonth);
                intent.putExtra("Month",(month+1));
                intent.putExtra("Year",year%2000);
                startActivity(intent);
            }

        });



    }

}
