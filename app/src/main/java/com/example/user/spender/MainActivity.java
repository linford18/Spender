package com.example.user.spender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH)+ 1;
    int year = calendar.get(Calendar.YEAR)%2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void calender(View view) {
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("Calendar")){
            Intent intent = new Intent(this,TheCalendar.class);
            startActivity(intent);
        }
    }

    public void todays_amt(View view) {
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("View Todays Amount")){
            Intent intent = new Intent(this,TodaysAmt.class);
            startActivity(intent);
        }
    }



    public void add_amount(View view) {
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("Add Amount Spent")){
            Intent intent = new Intent(this,AddAmount.class);
            intent.putExtra("Day",day);
            intent.putExtra("Month",(month));
            intent.putExtra("Year",year);
            startActivity(intent);
        }
    }
}
