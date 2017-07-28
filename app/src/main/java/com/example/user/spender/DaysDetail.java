package com.example.user.spender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class DaysDetail extends Activity implements View.OnClickListener {
    TextView tTotal;
    TextView tBreakfast;
    TextView tLunch;
    TextView tDinner;
    TextView tOthers;
    TextView tDate;
    Amount days_amt;
    Button edit;
    Button delete;
    DBHandler db = new DBHandler(this);
    int day,month,year,date;
    Boolean detailsadded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_detail);
        Intent intent=getIntent();
        year=intent.getIntExtra("Year",-1);
        month=intent.getIntExtra("Month",-1);
        day=intent.getIntExtra("Day",-1);
        Log.d("Days Details: Day:",String.valueOf(day));
        Log.d("Month",String.valueOf(month));
        Log.d("Year:",String.valueOf(year));
        date = day*1000000+month*1000+year%2000;
        Log.d("Date:",String.valueOf(date));
        tDate=(TextView) findViewById(R.id.date_textView2);
        tTotal=(TextView) findViewById(R.id.total_textView2);
        tBreakfast = (TextView) findViewById(R.id.bfast_textView2);
        tLunch = (TextView) findViewById(R.id.lunch_textView2);
        tDinner =(TextView) findViewById(R.id.dinner_textView2);
        tOthers =(TextView) findViewById(R.id.others_textView2);
        try {
            days_amt=db.getAmountDet(date);
            tDate.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            tBreakfast.setText(String.valueOf(days_amt.getBreakfast()));
            tLunch.setText(String.valueOf(days_amt.getLunch()));
            tDinner.setText(String.valueOf(days_amt.getDinner()));
            tOthers.setText(String.valueOf(days_amt.getOthers()));
            tTotal.setText(String.valueOf(days_amt.getLunch()+days_amt.getOthers()+days_amt.getDinner()+days_amt.getBreakfast()));

        }catch (RuntimeException e){
            Toast.makeText(this,"No details were added on this day",Toast.LENGTH_SHORT).show();
        }
        Log.d("Reading Days Details: ", "Reading all contacts..");
       // List<Amount> detail = db.getAllAmounts();

       /* for (Amount cn : detail) {
            String log = "Date: "+cn.getDate()+" ,Breakfast: " + cn.getBreakfast() + " ,Lunch: " +
                    cn.getLunch() + " Dinner:"+cn.getDinner()+" , Others:"+cn.getOthers();
            // Writing Details to log
            Log.d("Name: ", log);
        }*/
        edit=(Button) findViewById(R.id.edit_button);
        edit.setOnClickListener(this);
        delete=(Button) findViewById(R.id.del_button);
        delete.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_button:Intent intent = new Intent(getApplicationContext(),AddAmount.class);
                intent.putExtra("Day",day);
                intent.putExtra("Month",(month));
                intent.putExtra("Year",year);
                startActivity(intent);
                break;
            case  R.id.del_button:AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Delete!");
                alert.setMessage("Are you sure you want to do this? This action cannot be undone.");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

                break;
        }
    }
    @Override
    protected void onResume () {
        super.onResume();
        detailsadded = true;
        try {
            days_amt = db.getAmountDet(date);

        }catch (RuntimeException r) {
            Toast.makeText(this, "The day's details were not added.", Toast.LENGTH_SHORT).show();
            detailsadded = false;
        }
        if (detailsadded) {
            tDate.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            tBreakfast.setText(String.valueOf(days_amt.getBreakfast()));
            tLunch.setText(String.valueOf(days_amt.getLunch()));
            tDinner.setText(String.valueOf(days_amt.getDinner()));
            tOthers.setText(String.valueOf(days_amt.getOthers()));
            tTotal.setText(String.valueOf(days_amt.getLunch() + days_amt.getOthers() + days_amt.getDinner() + days_amt.getBreakfast()));
        }
    }

}

