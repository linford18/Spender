package com.example.user.spender;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class AddAmount extends Activity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    EditText mEdit;
    Button submit;
    String detail;
    int year,month,day;


    int date ;

    DBHandler db = new DBHandler(this);
    int amount = 0;
    int breakfast=0,lunch=0,dinner=0,others=0;
    int num=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_spent);
        Intent intent=getIntent();
        year=intent.getIntExtra("Year",-1);
        month=intent.getIntExtra("Month",-1);
        day=intent.getIntExtra("Day",-1);
        date = day*1000000+month*1000+year;     //to create a unique date
        Log.d("Add amount:month",String.valueOf(month));
        spinner =(Spinner) findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(  this ,R.array.Details,   android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                detail=(String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                detail = "Breakfast";

            }
        });
        mEdit=(EditText) findViewById(R.id.amount);
        submit=(Button) findViewById(R.id.submit);
        try{
            db.addAmount(new Amount(date,breakfast,lunch,dinner,others));
        }
        catch (Error s){
            db.updateAmount(new Amount(date,breakfast,lunch,dinner,others));
        }





        submit.setOnClickListener(new  View.OnClickListener(){
                                      @Override
                                      public void onClick(View v) {
                                          if (detail.equals("Lunch")){
                                              lunch=Integer.parseInt(mEdit.getText().toString());
                                              num=2;
                                          }
                                          else
                                          if (detail.equals("Breakfast")){
                                              breakfast=Integer.parseInt(mEdit.getText().toString());
                                              num=1;
                                          }
                                          else
                                          if (detail.equals("Dinner")){
                                              dinner=Integer.parseInt(mEdit.getText().toString());
                                              num=3;

                                          }
                                          else
                                          if (detail.equals("Other")){
                                              others=Integer.parseInt(mEdit.getText().toString());
                                              num=4;

                                          }
                                          //amount=Integer.parseInt(mEdit.getText().toString());
                                          String log="Date "+date+" detail "+detail+" bfast: "+breakfast+" Lunch: "+lunch+" dinner "+dinner+" others "+others;
                                          Log.d( "Add Amount :Log: ",log);
                                          amount=db.getDetail(date,num);
                                          Log.d("Amount", String.valueOf(amount));
                                          if (amount != 0) {

                                              AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                                              alert.setTitle("Amount Exists!");
                                              alert.setMessage("An amount is already present in this field. Do you want to add the current amount to it or set current amount as new amount? ");
                                              alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      //db.updateAmount(new Amount(date,detail,amount));
                                                      db.updateAmount(new Amount(date, breakfast, lunch, dinner, others));
                                                      Toast.makeText(getApplicationContext(), "Details Added", Toast.LENGTH_SHORT).show();

                                                  }
                                              });
                                              alert.setNegativeButton("Set", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      db.setAmount(date,num,breakfast,lunch,dinner,others);
                                                      Toast.makeText(getApplicationContext(), "New Details Set", Toast.LENGTH_SHORT).show();

                                                  }
                                              });
                                              alert.show();
                                          }
                                          else {
                                              db.updateAmount(new Amount(date, breakfast, lunch, dinner, others));
                                              Toast.makeText(getApplicationContext(), "Details Added", Toast.LENGTH_SHORT).show();

                                          }



                                      }
                                  }
        );
    }
}
