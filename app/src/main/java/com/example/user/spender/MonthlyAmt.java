package com.example.user.spender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by user on 31-12-2017.
 */
public class MonthlyAmt extends Activity {
    TextView tTotal;
    TextView tBreakfast;
    TextView tLunch;
    TextView tDinner;
    TextView tOthers;
    TextView tDate;
    Amount days_amt;
    Button next,previous;
    DBHandler db = new DBHandler(this);
    int day,month,year,date,today;
    int total=0,bfast=0,lunch=0,dinner=0,other=0;
    String monthString;
    boolean daysdetails = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);

        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.monthly_details);


        Intent intent=getIntent();
        year=intent.getIntExtra("Year",-1);
        month=intent.getIntExtra("Month",-1);
        today=intent.getIntExtra("Day",-1);
        day=1;
        next=(Button)findViewById(R.id.nextButton);
        previous=(Button)findViewById(R.id.previousButton);

        displaydetails(today,month,year);
        exportDB();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month += 1;
                if(month==13){
                    month=1;
                    year=year+1;
                }
                today=31;
                displaydetails(today, month, year);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month -= 1;
                if(month==0){
                    month=12;
                    year=year-1;
                }
                today=31;
                displaydetails(today, month, year);
            }
        });



    }



    public void displaydetails(int today, int month, int year){
        Log.d("Days Details: Day:",String.valueOf(day));
        Log.d("Month",String.valueOf(month));
        Log.d("Year:",String.valueOf(year));
        //date = day*1000000+month*1000+year%2000;
        //Log.d("Date:",String.valueOf(date));
        tDate=(TextView) findViewById(R.id.date_text2View2);
        tTotal=(TextView) findViewById(R.id.total_text2View2);
        tBreakfast = (TextView) findViewById(R.id.bfast_text2View2);
        tLunch = (TextView) findViewById(R.id.lunch_text2View2);
        tDinner =(TextView) findViewById(R.id.dinner_text2View2);
        tOthers =(TextView) findViewById(R.id.others_text2View2);
        total=0;bfast=0;lunch=0;dinner=0;other=0;


            for (day=1;day<=today;day++) {
                daysdetails = true;
                date = day*1000000+month*1000+year%2000;
                try {
                    days_amt = db.getAmountDet(date);
                }
                catch (RuntimeException e){
                    daysdetails= false;
                }
                if (daysdetails) {
                    bfast += days_amt.getBreakfast();
                    lunch += days_amt.getLunch();
                    dinner += days_amt.getDinner();
                    other += days_amt.getOthers();
                }

            }




        total+=bfast+lunch+dinner+other;
        switch (month) {
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
        tDate.setText(monthString+"-"+String.valueOf(year));
        tBreakfast.setText(String.valueOf(bfast));
        tLunch.setText(String.valueOf(lunch));
        tDinner.setText(String.valueOf(dinner));
        tOthers.setText(String.valueOf(other));
        tTotal.setText(String.valueOf(total));

        Log.d("Reading Days Details: ", "Reading all contacts..");
        // List<Amount> detail = db.getAllAmounts();

       /* for (Amount cn : detail) {
            String log = "Date: "+cn.getDate()+" ,Breakfast: " + cn.getBreakfast() + " ,Lunch: " +
                    cn.getLunch() + " Dinner:"+cn.getDinner()+" , Others:"+cn.getOthers();
            // Writing Details to log
            Log.d("Name: ", log);
        }*/
    }
    private void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String SAMPLE_DB_NAME = "stats";
        String currentDBPath = "/data/"+ "com.example.user.spender" +"/databases/"+SAMPLE_DB_NAME;
        String backupDBPath = SAMPLE_DB_NAME;
        File currentDB = new File(data, currentDBPath);
        Log.d("",sd.getPath());

        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
