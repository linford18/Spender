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

    private  static  final String KEY_MONTH_YEAR = "MonthYear";
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

    private  static  final String KEY_WEEK_MONTH_Y = "WeekMonthY";
    private  static  final String KEY_TOTAL_BFAST = "TotalBfast";
    private  static  final String KEY_TOTAL_LUNCH = "TotalLunch";
    private  static  final String KEY_TOTAL_DINNER = "TotalDinner";
    private  static  final String KEY_TOTAL_OTHER = "TotalOther";
    private  static  final String KEY_AVG_PER_WEEK = "AvgWeek";
    private static final String KEY_IS_WEEKCHANGED = "IsMonthChange";




    public StatisticsDBHandler(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    //creating Tables


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
                +KEY_MONTH_YEAR+" TEXT PRIMARY KEY,"
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
                +KEY_MONTH_YEAR+" TEXT,"
                +KEY_WEEK_MONTH_Y+" TEXT PRIMARY KEY,"
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

        db.execSQL("DROP TABLE IF EXITS " + TABLE_STATS_MONTH );

        onCreate(db);
    }
    void addStatsMonth(Amount amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MONTH_YEAR, amount.getDate());
        values.put(KEY_TOTAL,amount.getBreakfast());
        values.put(KEY_WEEK_1,amount.getDinner());
        values.put(KEY_WEEK_2,amount.getLunch());
        values.put(KEY_WEEK_3,amount.getOthers());
        values.put(KEY_WEEK_4,amount.getOthers());
        values.put(KEY_WEEK_5,amount.getOthers());
        values.put(KEY_WEEK_6,amount.getOthers());
        values.put(KEY_IS_WEEK_6,amount.getOthers());
        values.put(KEY_AVG_MONTH,amount.getOthers());
        values.put(KEY_AVG_TILL_MONTH,amount.getOthers());
        values.put(KEY_IS_MONTHCHANGED,amount.getOthers());

        // Inserting Row
        db.insert(TABLE_STATS_MONTH, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    void addStatsWeek(Amount amount) {
        SQLiteDatabase db = this.getWritableDatabase();
//        private  static  final String KEY_WEEK_MONTH_Y = "WeekMonthY";
//        private  static  final String KEY_TOTAL_BFAST = "TotalBfast";
//        private  static  final String KEY_TOTAL_LUNCH = "TotalLunch";
//        private  static  final String KEY_TOTAL_DINNER = "TotalDinner";
//        private  static  final String KEY_TOTAL_OTHER = "TotalOther";
//        private  static  final String KEY_AVG_PER_WEEK = "AvgWeek";
//        private static final String KEY_IS_WEEKCHANGED = "IsMonthChange";

        ContentValues values = new ContentValues();

        values.put(KEY_MONTH_YEAR, amount.getDate());
        values.put(KEY_WEEK_MONTH_Y, amount.getDate());
        values.put(KEY_TOTAL_BFAST,amount.getBreakfast());
        values.put(KEY_TOTAL_LUNCH,amount.getDinner());
        values.put(KEY_TOTAL_DINNER,amount.getLunch());
        values.put(KEY_TOTAL_OTHER,amount.getOthers());
        values.put(KEY_AVG_PER_WEEK,amount.getOthers());
        values.put(KEY_IS_WEEKCHANGED,amount.getOthers());

        // Inserting Row
        db.insert(TABLE_STATS_WEEK, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

//    // code to get the single day
//    Amount getAmountDet(int date) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//
//        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
//                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
//                new String[] { String.valueOf(date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Amount amount=new Amount(cursor.getInt(0),
//                cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
//        // return amount
//        return amount;
//    }
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
//    public int updateAmount(Amount amount) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
//                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
//                new String[] { String.valueOf(amount.date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        int bfast=cursor.getInt(1);
//        int lnch=cursor.getInt(2);
//        int dinnr=cursor.getInt(3);
//        int othr=cursor.getInt(4);
//        ContentValues values = new ContentValues();
//        values.put(KEY_BREAKFAST, amount.getBreakfast()+bfast);
//        values.put(KEY_LUNCH, amount.getLunch()+lnch);
//        values.put(KEY_DINNER, amount.getDinner()+dinnr);
//        values.put(KEY_OTHER, amount.getOthers()+othr);
//
//
//
//
//        // updating row
//        return db.update(TABLE_AMOUNT_DETAIL, values, KEY_DATE+ " = ?",
//                new String[] { String.valueOf(amount.getDate()) });
//    }
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