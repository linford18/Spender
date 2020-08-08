package com.example.user.spender;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 26-07-2020.
 */
public class SyncUp extends Activity {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH)+ 1;
    int year = calendar.get(Calendar.YEAR)%2000;

    float avgMonth,avgTillMonth;
    int total=0,week1=0,week2=0,week3=0,week4=0,week5=0,week6=0,isWeek6=0,isMonthChanged=0;
    float avgWeek;
    String yearMonth,yMonthWeek;
    int weekNum=0,totalBfast=0,totalDinner=0,totalLunch=0,totalOther=0,isWeekChanged=0,totalweek,num_days_month;
    StatsWeek db_week_stats;
    StatsMonth db_month_stats;

    StatisticsDBHandler s_db = new StatisticsDBHandler(this);
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
        setContentView(R.layout.activity_syncup);
        root=(ScrollView) findViewById(R.id.stats);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            root.setBackgroundResource(R.drawable.bag_cal_dark);
        } else {

            root.setBackgroundResource(R.drawable.bag_cal);
        }
        gridLayout=(GridLayout)findViewById(R.id.statsGrid);

        setSingleEvent(gridLayout);

        DBHandler db = new DBHandler(this);

        s_db.deleteStatMonthTable();
        s_db.deleteStatWeekTable();

        List<Amount> amounts = new ArrayList<Amount>();
        amounts = db.getAllAmounts();
//        for (int i =0;i<amounts.size();i++){
//            Log.d( String.valueOf(amounts[i]), "Reading all contacts..");
//        }
        int db_year,db_month,db_day,date;
        String date_str,str_month,str_day;
        Log.d("",String.valueOf(amounts.size()));
        List<DateAmount> dateAmtList = new ArrayList<>();
        for(Amount am:amounts){
//            Log.d("",String.valueOf(am.getDate()));
            date = am.getDate();
            db_year=(date%1000+2000);
            db_month = ((date-date%1000)-(date/1000000)*1000000)/1000;
            db_day=date/1000000;
            if(db_day<10)
                str_day="0"+String.valueOf(db_day);
            else
                str_day=String.valueOf(db_day);

            if(db_month<10)
                str_month = "0"+String.valueOf(db_month);
            else
                str_month = String.valueOf(db_month);

            date_str = String.valueOf(db_year)+"-"+str_month+"-"+str_day;
//            Log.d("",date_str);
            dateAmtList.add(new DateAmount(date_str,am));

        }
//        Log.d("Reading Days Details: ", "Reading all contacts..");

        Collections.sort(dateAmtList, new SortByDate());
//        dateAmtList.forEach(date -> {
//            System.out.println(date.datetime);
//        });
        Calendar cal = Calendar.getInstance();
        int week_of_month;
        for (DateAmount dm:dateAmtList){
//            Log.d("",dm.date);
            date = dm.amounts.getDate();
            db_year=(date%1000+2000);
            db_month = ((date-date%1000)-(date/1000000)*1000000)/1000;
            db_day=date/1000000;
            cal.set(Calendar.YEAR, db_year);
            cal.set(Calendar.MONTH, db_month-1);
            cal.set(Calendar.DAY_OF_MONTH, db_day);
            week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
//            Log.d("",String.valueOf(week_of_month));
            yearMonth = String.valueOf(db_year)+"-"+String.valueOf(db_month);
            yMonthWeek = String.valueOf(db_year)+"-"+String.valueOf(db_month)+"-"+String.valueOf(week_of_month);
            totalBfast = dm.amounts.getBreakfast();
            totalLunch = dm.amounts.getLunch();
            totalDinner = dm.amounts.getDinner();
            totalOther = dm.amounts.getOthers();
            totalweek = (totalBfast+totalLunch+totalDinner+totalOther);
            avgWeek = totalweek/7;
            isWeekChanged=0;
            // Add details for the week
            week1=0;week2=0;week3=0;week4=0;week5=0;week6=0;isWeek6=0;
            num_days_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            avgMonth=totalweek/num_days_month;
            avgTillMonth=0;
            switch (week_of_month){
                case 1:week1=totalweek;
                    break;
                case 2:week2=totalweek;
                    break;
                case 3:week3=totalweek;
                    break;
                case 4:week4=totalweek;
                    break;
                case 5:week5=totalweek;
                    break;
                case 6:week6=totalweek;
                    isWeek6=1;
                    break;
            }
            Log.d("",yMonthWeek+String.valueOf(s_db.CheckDataIfPresentInTable("stats_week","YMonthWeek",yMonthWeek)));
            Log.d("",yearMonth+String.valueOf( s_db.CheckDataIfPresentInTable("stats_month","YearMonth",yearMonth)));
            if (! s_db.CheckDataIfPresentInTableWeek("stats_week","YMonthWeek",yMonthWeek)){
                    s_db.addStatsWeek(new StatsWeek(week_of_month,yearMonth,yMonthWeek,totalBfast,
                            totalLunch,totalDinner,totalOther,avgWeek,isWeekChanged));
//                public StatsWeek(int week_num, String year_month, String y_month_week,int total_bfast,
//                int total_lunch, int total_dinner, int total_other, float avg_week,
//                int is_week_changed ){
//                db.addAmount(new Amount(date,breakfast,lunch,dinner,others));
                }
            else {
                    db_week_stats = s_db.getStatsWeekDetail(yMonthWeek);
                    totalweek= totalweek + db_week_stats.getTotalBfast()+db_week_stats.getTotalLunch()
                            +db_week_stats.getTotalDinner()+db_week_stats.getTotalOther();
                    avgWeek = totalweek /7;
                    s_db.updateStatsWeek(new StatsWeek(week_of_month,yearMonth,yMonthWeek,
                            totalBfast+db_week_stats.getTotalBfast(),
                            totalLunch+db_week_stats.getTotalLunch(),
                            totalDinner+db_week_stats.getTotalDinner(),
                            totalOther+db_week_stats.getTotalOther(),avgWeek,isWeekChanged));
//                db.updateAmount(new Amount(date,breakfast,lunch,dinner,others));
                }

//            try{
//                s_db.addStatsWeek(new StatsWeek(week_of_month,yearMonth,yMonthWeek,totalBfast,
//                        totalLunch,totalDinner,totalOther,avgWeek,isWeekChanged));
////                public StatsWeek(int week_num, String year_month, String y_month_week,int total_bfast,
////                int total_lunch, int total_dinner, int total_other, float avg_week,
////                int is_week_changed ){
////                db.addAmount(new Amount(date,breakfast,lunch,dinner,others));
//            }
//            catch (SQLiteConstraintException e){
//                db_week_stats = s_db.getStatsWeekDetail(yMonthWeek);
//                totalweek= totalweek + db_week_stats.getTotalBfast()+db_week_stats.getTotalLunch()
//                        +db_week_stats.getTotalDinner()+db_week_stats.getTotalOther();
//                avgWeek = totalweek /7;
//                s_db.updateStatsWeek(new StatsWeek(week_of_month,yearMonth,yMonthWeek,
//                        totalBfast+db_week_stats.getTotalBfast(),
//                        totalLunch+db_week_stats.getTotalLunch(),
//                        totalDinner+db_week_stats.getTotalDinner(),
//                        totalOther+db_week_stats.getTotalOther(),avgWeek,isWeekChanged));
////                db.updateAmount(new Amount(date,breakfast,lunch,dinner,others));
//            }
            if (! s_db.CheckDataIfPresentInTable("stats_month","YearMonth",yearMonth)){
                s_db.addStatsMonth(new StatsMonth(yearMonth,totalweek,week1,week2,week3,week4,week5,
                        week6,isWeek6,avgMonth,avgTillMonth,isMonthChanged));
//                public StatsMonth(String yearMonth,int total,int week1,
//                int week2, int week3, int week4, int week5, int week6, int isWeek6,
//                float avgMonth, float avgTillMonth,int isMonthChanged)
            }
            else {
                db_month_stats = s_db.getStatsMonthDetail(yearMonth);
                total = db_month_stats.getTotal()+totalweek;
                switch (week_of_month) {
                    case 1:
                        week1 = totalweek + db_month_stats.getWeek1();
                        break;
                    case 2:
                        week2 = totalweek + db_month_stats.getWeek2();
                        break;
                    case 3:
                        week3 = totalweek + db_month_stats.getWeek3();
                        break;
                    case 4:
                        week4 = totalweek + db_month_stats.getWeek4();
                        break;
                    case 5:
                        week5 = totalweek + db_month_stats.getWeek5();
                        break;
                    case 6:
                        week6 = totalweek + db_month_stats.getWeek6();

                        break;
                }
                avgMonth = total/num_days_month;
                s_db.updateStatsMonth(new StatsMonth(yearMonth,total,week1,week2,week3,week4,week5,
                        week6,isWeek6,avgMonth,avgTillMonth,isMonthChanged));
            }

            // Add details for the month
//            try{
//                s_db.addStatsMonth(new StatsMonth(yearMonth,totalweek,week1,week2,week3,week4,week5,
//                        week6,isWeek6,avgMonth,avgTillMonth,isMonthChanged));
////                public StatsMonth(String yearMonth,int total,int week1,
////                int week2, int week3, int week4, int week5, int week6, int isWeek6,
////                float avgMonth, float avgTillMonth,int isMonthChanged)
//            }
//            catch (SQLiteConstraintException e){
//                db_month_stats = s_db.getStatsMonthDetail(yearMonth);
//                total = db_month_stats.getTotal()+totalweek;
//                switch (week_of_month) {
//                    case 1:
//                        week1 = totalweek + db_month_stats.getWeek1();
//                        break;
//                    case 2:
//                        week2 = totalweek + db_month_stats.getWeek2();
//                        break;
//                    case 3:
//                        week3 = totalweek + db_month_stats.getWeek3();
//                        break;
//                    case 4:
//                        week4 = totalweek + db_month_stats.getWeek4();
//                        break;
//                    case 5:
//                        week5 = totalweek + db_month_stats.getWeek5();
//                        break;
//                    case 6:
//                        week6 = totalweek + db_month_stats.getWeek6();
//
//                        break;
//                }
//                avgMonth = total/num_days_month;
//                s_db.updateStatsMonth(new StatsMonth(yearMonth,total,week1,week2,week3,week4,week5,
//                        week6,isWeek6,avgMonth,avgTillMonth,isMonthChanged));
//            }
        }

    }

//    String CREATE_STATS_WEEK_TABLE="CREATE TABLE " + TABLE_STATS_WEEK +"("
//            +KEY_WEEK_NUM + " INTEGER,"
//            +KEY_YEAR_MONTH+" TEXT,"
//            +KEY_Y_MONTH_WEEK+" TEXT PRIMARY KEY,"
//            +KEY_TOTAL_BFAST + " INTEGER,"
//            +KEY_TOTAL_LUNCH + " INTEGER,"
//            +KEY_TOTAL_DINNER + " INTEGER,"
//            +KEY_TOTAL_OTHER + " INTEGER,"
//            +KEY_AVG_PER_WEEK + " REAL,"
//            +KEY_IS_WEEKCHANGED + " INTEGER"+ ")";
//    String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
//            +KEY_YEAR_MONTH+" TEXT PRIMARY KEY,"
//            +KEY_TOTAL + " INTEGER,"
//            +KEY_WEEK_1 + " INTEGER,"
//            +KEY_WEEK_2 + " INTEGER,"
//            +KEY_WEEK_3 + " INTEGER,"
//            +KEY_WEEK_4 + " INTEGER,"
//            +KEY_WEEK_5 + " INTEGER,"
//            +KEY_WEEK_6 + " INTEGER,"
//            +KEY_IS_WEEK_6 + " INTEGER,"
//            +KEY_AVG_MONTH + " REAL,"
//            +KEY_AVG_TILL_MONTH + " REAL,"
//            +KEY_IS_MONTHCHANGED + " INTEGER"+ ")";
//    db.execSQL(CREATE_STATS_MONTH_TABLE);


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
    static class DateItem {
        String datetime;

        DateItem(String date) {
            this.datetime = date;
        }
    }
    static class DateAmount {
        String date;
        Amount amounts;
        DateAmount(String date, Amount amount){
            this.date = date;
            this.amounts=amount;
        }
    }
    static class SortByDate implements Comparator<DateAmount> {
        @Override
        public int compare(DateAmount a, DateAmount b) {
            return a.date.compareTo(b.date);
        }
    }

}
