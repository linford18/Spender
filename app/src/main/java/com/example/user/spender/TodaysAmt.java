package com.example.user.spender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.Calendar;


public class TodaysAmt extends Activity implements OnClickListener {
    TextView tTotal;
    TextView tBreakfast;
    TextView tLunch;
    TextView tDinner;
    TextView tOthers;
    Amount tdays_amt;
    Button update;
    Button delete;
    DBHandler db = new DBHandler(this);
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR) % 2000;
    int date = day * 1000000 + month * 1000 + year;
    boolean detailsadded = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.todays_amount);

        try {
            tdays_amt = db.getAmountDet(date);
        } catch (RuntimeException r) {
            Toast.makeText(this, "Today's details were not added.", Toast.LENGTH_LONG).show();
            detailsadded = false;
        }
        if (detailsadded == true) {

            setContentView(R.layout.todays_amount);
            tTotal = (TextView) findViewById(R.id.total_textView);
            tBreakfast = (TextView) findViewById(R.id.bfast_textView);
            tLunch = (TextView) findViewById(R.id.lunch_textView);
            tDinner = (TextView) findViewById(R.id.dinner_textView);
            tOthers = (TextView) findViewById(R.id.others_textView);
            tBreakfast.setText(String.valueOf(tdays_amt.getBreakfast()));
            tLunch.setText(String.valueOf(tdays_amt.getLunch()));
            tDinner.setText(String.valueOf(tdays_amt.getDinner()));
            tOthers.setText(String.valueOf(tdays_amt.getOthers()));
            tTotal.setText(String.valueOf(tdays_amt.getLunch() + tdays_amt.getOthers() + tdays_amt.getDinner() + tdays_amt.getBreakfast()));
            update = (Button) findViewById(R.id.update);
            update.setOnClickListener(this);
            delete = (Button) findViewById(R.id.delete);
            delete.setOnClickListener(this);



        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.update:Intent intent = new Intent(this, AddAmount.class);
                intent.putExtra("Day",day);
                intent.putExtra("Month",(month));
                intent.putExtra("Year",year);
                startActivity(intent);


                // do something
                break;
            case R.id.delete: AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Delete!");
                alert.setMessage("Are you sure you want to do this? This action cannot be undone.");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //db.updateAmount(new Amount(date,detail,amount));
                        db.deleteAmount(date);
                        Toast.makeText(getApplicationContext(), "Details Deleted.", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                alert.show();

                // do something else
                break;
        }
    }



    @Override
            protected void onResume () {
                super.onResume();
                try {
                    tdays_amt = db.getAmountDet(date);

                }catch (RuntimeException r) {
                    Toast.makeText(this, "Today's details were not added.", Toast.LENGTH_SHORT).show();
                    detailsadded = false;
                }
            if (detailsadded == true) {

            tBreakfast.setText(String.valueOf(tdays_amt.getBreakfast()));
            tLunch.setText(String.valueOf(tdays_amt.getLunch()));
            tDinner.setText(String.valueOf(tdays_amt.getDinner()));
            tOthers.setText(String.valueOf(tdays_amt.getOthers()));
            tTotal.setText(String.valueOf(tdays_amt.getLunch() + tdays_amt.getOthers() + tdays_amt.getDinner() + tdays_amt.getBreakfast()));
            }
            }
        }

