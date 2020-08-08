package com.example.user.spender;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH)+ 1;
    int year = calendar.get(Calendar.YEAR)%2000;
    GridLayout gridLayout;
    ScrollView root;

    SettingsDBHandler setting_db = new SettingsDBHandler(this);
    SettingClass theme;
    int theme_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.row);
        root=(ScrollView) findViewById(R.id.MainBgScroll);


//        try{
//            theme = setting_db.getSettingDet("Theme");
//            theme_value=theme.getSettingValue();
////            db.addAmount(new Amount(date,breakfast,lunch,dinner,others));
//            Toast.makeText(this,String.valueOf(theme_value)+ " got from db", Toast.LENGTH_LONG).show();
//        }
//        catch (CursorIndexOutOfBoundsException s){
////            db.updateAmount(new Amount(date,breakfast,lunch,dinner,others));
//            setting_db.addSettingDet(new SettingClass("Theme",0));
//            theme_value = 0;
//            Toast.makeText(this, "Setting first time value to 0", Toast.LENGTH_LONG).show();
//        }
//        if (theme_value==0){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//        else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }

        Util u = new Util();
        u.set_theme(this);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            root.setBackgroundResource(R.drawable.bag_cal_dark);
        } else {

            root.setBackgroundResource(R.drawable.bag_cal);
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //new
        gridLayout=(GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(gridLayout);

        ImageButton setting = (ImageButton)findViewById(R.id.imageButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Settings.class);
                    intent.putExtra("Day",day);
                    intent.putExtra("Month",(month));
                    intent.putExtra("Year",year);
                    startActivity(intent);

            }

        });


    }


    //new
    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI){
                        case 0:Intent intent = new Intent(view.getContext(), AddAmount.class);
                            intent.putExtra("Day",day);
                            intent.putExtra("Month",(month));
                            intent.putExtra("Year",year);
                            startActivity(intent);
                            break;
                        case 1:Intent intent1 = new Intent(view.getContext(),TheCalendar.class);
                            intent1.putExtra("Day",day);
                            intent1.putExtra("Month",(month));
                            intent1.putExtra("Year",year);
                            startActivity(intent1);
                            break;
                        case 2:Intent intent2 = new Intent(view.getContext(),TodaysAmt.class);
                            startActivity(intent2);
                            break;
                        case 3:Intent intent3 = new Intent(view.getContext(),MonthlyAmt.class);
                            intent3.putExtra("Day",day);
                            intent3.putExtra("Month",(month));
                            intent3.putExtra("Year",year);
                            startActivity(intent3);
                            break;
                        case 4:Intent intent4 = new Intent(view.getContext(),Statistics.class);
                            intent4.putExtra("Day",day);
                            intent4.putExtra("Month",(month));
                            intent4.putExtra("Year",year);
                            startActivity(intent4);
                            break;
                        case 5:Intent intent5 = new Intent(view.getContext(),SyncUp.class);
                            intent5.putExtra("Day",day);
                            intent5.putExtra("Month",(month));
                            intent5.putExtra("Year",year);
                            startActivity(intent5);
                            break;
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.settings){
            Intent intent = new Intent(this,Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    public void calender(View view) {
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("Calendar")){
            Intent intent = new Intent(this,TheCalendar.class);
            intent.putExtra("Day",day);
            intent.putExtra("Month",(month));
            intent.putExtra("Year",year);
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
    public void monthly_amt(View view){
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("View monthly details")){
            Intent intent = new Intent(this,MonthlyAmt.class);
            intent.putExtra("Day",day);
            intent.putExtra("Month",(month));
            intent.putExtra("Year",year);
            startActivity(intent);
        }

    }

    public void tryout(View view) {
        String ButtonTxt;
        ButtonTxt=((Button) view).getText().toString();
        if (ButtonTxt.equals("Try")){
            Intent intent = new Intent(this,Tryout.class);
            intent.putExtra("Day",day);
            intent.putExtra("Month",(month));
            intent.putExtra("Year",year);
            startActivity(intent);
        }
    }

    public void syncup(View view) {

            Intent intent = new Intent(this,SyncUp.class);
            intent.putExtra("Day",day);
            intent.putExtra("Month",(month));
            intent.putExtra("Year",year);
            startActivity(intent);
        }


}
