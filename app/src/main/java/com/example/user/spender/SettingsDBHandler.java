package com.example.user.spender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05-08-2020.
 */
public class SettingsDBHandler extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION = 1;

    private  static final String DATABASE_NAME = "settings";

    private  static  final String TABLE_SETTING_BOOL = "setting";

    private  static  final String KEY_setting_type = "setting_type";
    private  static  final String KEY_setting_value = "setting_value";



    public SettingsDBHandler(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    //creating Tables


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_AMOUNT_DETAIL_TABLE="CREATE TABLE " + TABLE_SETTING_BOOL +"("
                +KEY_setting_type+" TEXT PRIMARY KEY,"
                +KEY_setting_value + " INTEGER"+ ")";
        db.execSQL(CREATE_AMOUNT_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXITS " + TABLE_SETTING_BOOL );

        onCreate(db);
    }

    // Add new setting
    void addSettingDet(SettingClass setting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_setting_type, setting.getSettingType());
        values.put(KEY_setting_value,setting.getSettingValue());

        // Inserting Row
        db.insert(TABLE_SETTING_BOOL, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get a setting detail
    SettingClass getSettingDet(String set_type) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SETTING_BOOL, new String[] { KEY_setting_type,
                        KEY_setting_value }, KEY_setting_type + "=?",
                new String[] { set_type }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SettingClass setting = new SettingClass(cursor.getString(0),cursor.getInt(1));

//        Amount amount=new Amount(cursor.getInt(0),
//                cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
        // return amount
        return setting;
    }

    // code to get all amounts in a list view
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


 /*   public String getDetails(int date,int pos) {
        List<Amount> AmountList = new ArrayList<Amount>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_AMOUNT_DETAIL;
        int num=-1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                num++;
                if(num == pos){
                    break;
                }

            } while (cursor.moveToNext());
        }

        // return details list
        return cursor.getString(2);
    }*/


    // code to update a setting detail
    public int updateSettingDet(SettingClass setting) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SETTING_BOOL, new String[] { KEY_setting_type,
                        KEY_setting_value }, KEY_setting_type + "=?",
                new String[] { setting.s_type }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

//        int value=cursor.getInt(1);
        ContentValues values = new ContentValues();
        values.put(KEY_setting_value, setting.getSettingValue());


        // updating row
        return db.update(TABLE_SETTING_BOOL, values, KEY_setting_type+ " = ?",
                new String[] { setting.s_type });
    }

//    public  void setSetting(String stype,int val){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(TABLE_SETTING_BOOL, new String[] { KEY_setting_type,
//                        KEY_setting_value }, KEY_setting_type + "=?",
//                new String[] { stype }, null, null, null, null);
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

    // Deleting single amount entry
//    public void deleteAmount(int date) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_AMOUNT_DETAIL, KEY_DATE + " = ?",
//                new String[] { String.valueOf(date) });
//        db.close();
//    }



}