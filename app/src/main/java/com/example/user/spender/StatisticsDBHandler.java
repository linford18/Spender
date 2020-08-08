package com.example.user.spender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31-07-2020.
 */
public class StatisticsDBHandler extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION = 1;

    private  static final String DATABASE_NAME = "stats";

    private  static  final String TABLE_STATS_MONTH = "stats_month";

    private  static  final String KEY_YEAR_MONTH = "YearMonth";
    private  static  final String KEY_TOTAL = "Total";
    private  static  final String KEY_WEEK_1 = "Week1";
    private  static  final String KEY_WEEK_2 = "Week2";
    private  static  final String KEY_WEEK_3 = "Week3";
    private  static  final String KEY_WEEK_4 = "Week4";
    private  static  final String KEY_WEEK_5 = "Week5";
    private  static  final String KEY_WEEK_6 = "Week6";
    private  static  final String KEY_IS_WEEK_6 = "IsWeek6";
    private  static  final String KEY_AVG_MONTH = "AvgMonth";
    private  static  final String KEY_AVG_TILL_MONTH = "AvgTillMonth";
    private static final String KEY_IS_MONTHCHANGED = "IsMonthChange";

    private  static  final String TABLE_STATS_WEEK = "stats_week";

    private  static  final String KEY_WEEK_NUM = "WeekNo";
    private  static  final String KEY_Y_MONTH_WEEK = "YMonthWeek";
    private  static  final String KEY_TOTAL_BFAST = "TotalBfast";
    private  static  final String KEY_TOTAL_LUNCH = "TotalLunch";
    private  static  final String KEY_TOTAL_DINNER = "TotalDinner";
    private  static  final String KEY_TOTAL_OTHER = "TotalOther";
    private  static  final String KEY_AVG_PER_WEEK = "AvgWeek";
    private static final String KEY_IS_WEEKCHANGED = "IsWeekChange";




    public StatisticsDBHandler(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    //creating Tables


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
                +KEY_YEAR_MONTH+" TEXT PRIMARY KEY,"
                +KEY_TOTAL + " INTEGER,"
                +KEY_WEEK_1 + " INTEGER,"
                +KEY_WEEK_2 + " INTEGER,"
                +KEY_WEEK_3 + " INTEGER,"
                +KEY_WEEK_4 + " INTEGER,"
                +KEY_WEEK_5 + " INTEGER,"
                +KEY_WEEK_6 + " INTEGER,"
                +KEY_IS_WEEK_6 + " INTEGER,"
                +KEY_AVG_MONTH + " REAL,"
                +KEY_AVG_TILL_MONTH + " REAL,"
                +KEY_IS_MONTHCHANGED + " INTEGER"+ ")";
        db.execSQL(CREATE_STATS_MONTH_TABLE);
        String CREATE_STATS_WEEK_TABLE="CREATE TABLE " + TABLE_STATS_WEEK +"("
                +KEY_WEEK_NUM + " INTEGER,"
                +KEY_YEAR_MONTH+" TEXT,"
                +KEY_Y_MONTH_WEEK+" TEXT PRIMARY KEY,"
                +KEY_TOTAL_BFAST + " INTEGER,"
                +KEY_TOTAL_LUNCH + " INTEGER,"
                +KEY_TOTAL_DINNER + " INTEGER,"
                +KEY_TOTAL_OTHER + " INTEGER,"
                +KEY_AVG_PER_WEEK + " REAL,"
                +KEY_IS_WEEKCHANGED + " INTEGER"+ ")";
        db.execSQL(CREATE_STATS_WEEK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS_MONTH );

        onCreate(db);
    }

    public void deleteStatMonthTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS_MONTH);


    }
    public void deleteStatWeekTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS_WEEK);
        onCreate(db);
    }

    void addStatsMonth(StatsMonth smonth) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_YEAR_MONTH, smonth.getYearMonth());
        values.put(KEY_TOTAL,smonth.getTotal());
        values.put(KEY_WEEK_1,smonth.getWeek1());
        values.put(KEY_WEEK_2,smonth.getWeek2());
        values.put(KEY_WEEK_3,smonth.getWeek3());
        values.put(KEY_WEEK_4,smonth.getWeek4());
        values.put(KEY_WEEK_5,smonth.getWeek5());
        values.put(KEY_WEEK_6,smonth.getWeek6());
        values.put(KEY_IS_WEEK_6,smonth.getIsWeek6());
        values.put(KEY_AVG_MONTH,smonth.getAvgMonth());
        values.put(KEY_AVG_TILL_MONTH,smonth.getAvgTillMonth());
        values.put(KEY_IS_MONTHCHANGED,smonth.getIsMonthChanged());

        // Inserting Row
        db.insert(TABLE_STATS_MONTH, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addStatsWeek(StatsWeek sweek) {
        SQLiteDatabase db = this.getWritableDatabase();
//        private  static  final String KEY_WEEK_MONTH_Y = "WeekMonthY";
//        private  static  final String KEY_TOTAL_BFAST = "TotalBfast";
//        private  static  final String KEY_TOTAL_LUNCH = "TotalLunch";
//        private  static  final String KEY_TOTAL_DINNER = "TotalDinner";
//        private  static  final String KEY_TOTAL_OTHER = "TotalOther";
//        private  static  final String KEY_AVG_PER_WEEK = "AvgWeek";
//        private static final String KEY_IS_WEEKCHANGED = "IsMonthChange";

        ContentValues values = new ContentValues();

        values.put(KEY_WEEK_NUM, sweek.getWeekNum());
        values.put(KEY_YEAR_MONTH, sweek.getYearMonth());
        values.put(KEY_Y_MONTH_WEEK, sweek.getyMonthWeek());
        values.put(KEY_TOTAL_BFAST,sweek.getTotalBfast());
        values.put(KEY_TOTAL_LUNCH,sweek.getTotalLunch());
        values.put(KEY_TOTAL_DINNER,sweek.getTotalDinner());
        values.put(KEY_TOTAL_OTHER,sweek.getTotalOther());
        values.put(KEY_AVG_PER_WEEK,sweek.getAvgWeek());
        values.put(KEY_IS_WEEKCHANGED,sweek.getIsWeekChanged());

        // Inserting Row
        db.insert(TABLE_STATS_WEEK, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

//    // code to get the single day
    StatsWeek getStatsWeekDetail(String yMonthWeek) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_STATS_WEEK, null, KEY_Y_MONTH_WEEK + "=?",
                new String[] { yMonthWeek }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

//        Amount amount=new Amount(cursor.getInt(0),
//                cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
//        String CREATE_STATS_WEEK_TABLE="CREATE TABLE " + TABLE_STATS_WEEK +"("
//                +KEY_WEEK_NUM + " INTEGER,"
//                +KEY_YEAR_MONTH+" TEXT,"
//                +KEY_Y_MONTH_WEEK+" TEXT PRIMARY KEY,"
//                +KEY_TOTAL_BFAST + " INTEGER,"
//                +KEY_TOTAL_LUNCH + " INTEGER,"
//                +KEY_TOTAL_DINNER + " INTEGER,"
//                +KEY_TOTAL_OTHER + " INTEGER,"
//                +KEY_AVG_PER_WEEK + " REAL,"
//                +KEY_IS_WEEKCHANGED + " INTEGER"+ ")";

        StatsWeek statsWeek = new StatsWeek(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getFloat(7),cursor.getInt(8));
        // return amount
        cursor.close();
        return statsWeek;
    }

    StatsMonth getStatsMonthDetail(String yearMonth) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_STATS_MONTH, null, KEY_YEAR_MONTH + "=?",
                new String[] { yearMonth }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

//        Amount amount=new Amount(cursor.getInt(0),
//                cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
//        String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
//                +KEY_YEAR_MONTH+" TEXT PRIMARY KEY,"
//                +KEY_TOTAL + " INTEGER,"
//                +KEY_WEEK_1 + " INTEGER,"
//                +KEY_WEEK_2 + " INTEGER,"
//                +KEY_WEEK_3 + " INTEGER,"
//                +KEY_WEEK_4 + " INTEGER,"
//                +KEY_WEEK_5 + " INTEGER,"
//                +KEY_WEEK_6 + " INTEGER,"
//                +KEY_IS_WEEK_6 + " INTEGER,"
//                +KEY_AVG_MONTH + " REAL,"
//                +KEY_AVG_TILL_MONTH + " REAL,"
//                +KEY_IS_MONTHCHANGED + " INTEGER"+ ")";

        StatsMonth statsMonth = new StatsMonth(cursor.getString(0),cursor.getInt(1),cursor.getInt(2),
                cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8),
                cursor.getFloat(9),cursor.getFloat(10),cursor.getInt(11));
        cursor.close();
        // return amount
        return statsMonth;
    }
//
//    // code to get all amounts in a list view
//    public List<Amount> getAllAmounts() {
//        List<Amount> AmountList = new ArrayList<Amount>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_AMOUNT_DETAIL;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Amount amount = new Amount();
//                amount.setDate(cursor.getInt(0));
//
//                amount.setBreakfast(cursor.getInt(1));
//                amount.setLunch(cursor.getInt(2));
//                amount.setDinner(cursor.getInt(2));
//                amount.setOthers(cursor.getInt(2));
//                // Adding amounts to list
//                AmountList.add(amount);
//            } while (cursor.moveToNext());
//        }
//
//        // return amount list
//        return AmountList;
//    }
//
//    public int getDetail(int date,int pos){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
//                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
//                new String[] { String.valueOf(date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        return cursor.getInt(pos);
//    }
//
//
// /*   public String getDetails(int date,int pos) {
//        List<Amount> AmountList = new ArrayList<Amount>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_AMOUNT_DETAIL;
//        int num=-1;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                num++;
//                if(num == pos){
//                    break;
//                }
//
//            } while (cursor.moveToNext());
//        }
//
//        // return details list
//        return cursor.getString(2);
//    }*/
//
//
//    // code to update the single amount detail
    public int updateStatsMonth(StatsMonth smonth) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
//                +KEY_YEAR_MONTH+" TEXT PRIMARY KEY,"
//                +KEY_TOTAL + " INTEGER,"
//                +KEY_WEEK_1 + " INTEGER,"
//                +KEY_WEEK_2 + " INTEGER,"
//                +KEY_WEEK_3 + " INTEGER,"
//                +KEY_WEEK_4 + " INTEGER,"
//                +KEY_WEEK_5 + " INTEGER,"
//                +KEY_WEEK_6 + " INTEGER,"
//                +KEY_IS_WEEK_6 + " INTEGER,"
//                +KEY_AVG_MONTH + " REAL,"
//                +KEY_AVG_TILL_MONTH + " REAL,"
//                +KEY_IS_MONTHCHANGED + " INTEGER"+ ")";

//        Cursor cursor = db.query(TABLE_STATS_MONTH, new String[] { KEY_YEAR_MONTH,
//                        KEY_TOTAL, KEY_WEEK_1,KEY_WEEK_2,KEY_WEEK_3,KEY_WEEK_4,KEY_WEEK_5,KEY_WEEK_6,
//                KEY_IS_WEEK_6,KEY_AVG_MONTH,KEY_AVG_TILL_MONTH,KEY_IS_MONTHCHANGED }, KEY_YEAR_MONTH + "=?",
//                new String[] { String.valueOf(smonth.getYearMonth()) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();

//        int bfast=cursor.getInt(1);
//        int lnch=cursor.getInt(2);
//        int dinnr=cursor.getInt(3);
//        int othr=cursor.getInt(4);
        ContentValues values = new ContentValues();
        values.put(KEY_TOTAL, smonth.getTotal());
        values.put(KEY_WEEK_1, smonth.getWeek1());
        values.put(KEY_WEEK_2, smonth.getWeek2());
        values.put(KEY_WEEK_3, smonth.getWeek3());
        values.put(KEY_WEEK_4, smonth.getWeek4());
        values.put(KEY_WEEK_5, smonth.getWeek5());
        values.put(KEY_WEEK_6, smonth.getWeek6());
        values.put(KEY_IS_WEEK_6, smonth.getIsWeek6());
        values.put(KEY_AVG_MONTH,smonth.getAvgMonth());



//        cursor.close();
        // updating row
        return db.update(TABLE_STATS_MONTH, values, KEY_YEAR_MONTH+ " = ?",
                new String[] { String.valueOf(smonth.getYearMonth()) });
    }

    public int updateStatsWeek(StatsWeek statsWeek) {
        SQLiteDatabase db = this.getWritableDatabase();

//        Cursor cursor = db.query(TABLE_STATS_WEEK, new String[] { KEY_DATE,
//                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
//                new String[] { String.valueOf(amount.date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        int bfast=cursor.getInt(1);
//        int lnch=cursor.getInt(2);
//        int dinnr=cursor.getInt(3);
//        int othr=cursor.getInt(4);

//        String CREATE_STATS_WEEK_TABLE="CREATE TABLE " + TABLE_STATS_WEEK +"("
//                +KEY_WEEK_NUM + " INTEGER,"
//                +KEY_YEAR_MONTH+" TEXT,"
//                +KEY_Y_MONTH_WEEK+" TEXT PRIMARY KEY,"
//                +KEY_TOTAL_BFAST + " INTEGER,"
//                +KEY_TOTAL_LUNCH + " INTEGER,"
//                +KEY_TOTAL_DINNER + " INTEGER,"
//                +KEY_TOTAL_OTHER + " INTEGER,"
//                +KEY_AVG_PER_WEEK + " REAL,"
//                +KEY_IS_WEEKCHANGED + " INTEGER"+ ")";
        ContentValues values = new ContentValues();
        values.put(KEY_TOTAL_BFAST, statsWeek.getTotalBfast());
        values.put(KEY_TOTAL_LUNCH,statsWeek.getTotalLunch());
        values.put(KEY_TOTAL_DINNER,statsWeek.getTotalDinner());
        values.put(KEY_TOTAL_OTHER,statsWeek.getTotalOther());
        values.put(KEY_AVG_PER_WEEK, statsWeek.getAvgWeek());




        // updating row
        return db.update(TABLE_STATS_WEEK, values, KEY_Y_MONTH_WEEK+ " = ?",
                new String[] { String.valueOf(statsWeek.getyMonthWeek()) });
    }

    public boolean CheckDataIfPresentInTable(String TableName,
                                                      String dbfield, String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
//        Cursor cursor = db.rawQuery(Query, null);
        Cursor cursor = db.query(TABLE_STATS_MONTH, null, KEY_YEAR_MONTH + "=?",
                new String[] { fieldValue }, null, null, null, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean CheckDataIfPresentInTableWeek(String TableName,
                                             String dbfield, String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
//        Cursor cursor = db.rawQuery(Query, null);
        Cursor cursor = db.query(TABLE_STATS_WEEK, null, KEY_Y_MONTH_WEEK + "=?",
                new String[] { fieldValue }, null, null, null, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
//    public  void setAmount(int date,int pos,int bfast,int lnch,int dinner,int others){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
//                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
//                new String[] { String.valueOf(date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        ContentValues values = new ContentValues();
//        switch (pos){
//            case 1:values.put(KEY_BREAKFAST,bfast);
//                break;
//            case 2:values.put(KEY_LUNCH,lnch);
//                break;
//            case 3: values.put(KEY_DINNER,dinner);
//                break;
//            case 4:values.put(KEY_OTHER,others);
//                break;
//
//        }
//        db.update(TABLE_AMOUNT_DETAIL, values, KEY_DATE+ " = ?",
//                new String[] { String.valueOf(date) });
//
//
//
//    }
//
//    // Deleting single amount entry
//    public void deleteAmount(int date) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_AMOUNT_DETAIL, KEY_DATE + " = ?",
//                new String[] { String.valueOf(date) });
//        db.close();
//    }



}