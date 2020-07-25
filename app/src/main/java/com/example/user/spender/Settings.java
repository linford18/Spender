package com.example.user.spender;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import java.util.logging.Logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by user on 20-08-2017.
 */
public class Settings extends AppCompatActivity {
    LinearLayout root;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,20);
        calendar.set(Calendar.MINUTE,42);
        calendar.set(Calendar.SECOND,20);
        Switch sw1;
        root=(LinearLayout)findViewById(R.id.MainBgImage);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        sw1 = (Switch)findViewById(R.id.themeMode);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            sw1.setChecked(true);
        sw1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str1;
                Switch sw1;
                sw1 = (Switch)findViewById(R.id.themeMode);

                try {
                    if (sw1.isChecked()) {
                        str1 = sw1.getTextOn().toString();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(getApplicationContext(), str1 + " Mode Enabled", Toast.LENGTH_SHORT).show();
                        root.setBackgroundResource(R.drawable.bag_cal_dark);
                    } else {
                        str1 = sw1.getTextOff().toString();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        Toast.makeText(getApplicationContext(), str1 + " Mode Enabled", Toast.LENGTH_SHORT).show();
                        root.setBackgroundResource(R.drawable.bag_cal);
                    }
                }
                catch (NullPointerException n){

                }
                finish();
                overridePendingTransition(0, 0);
//                startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
                Intent intent2=getIntent();
                startActivity(intent2);
                overridePendingTransition(0, 0);


            }
        });
        Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpTo(this, new Intent(this,
                MainActivity.class));
    }
}
