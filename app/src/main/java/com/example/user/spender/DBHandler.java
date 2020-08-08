package com.example.user.spender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION = 1;

    private  static final String DATABASE_NAME = "amount";

    private  static  final String TABLE_AMOUNT_DETAIL = "amountdetails";

    private  static  final String KEY_DATE = "Date";
    private  static  final String KEY_LUNCH = "Lunch";
    private  static  final String KEY_BREAKFAST = "Breakfast";
    private  static  final String KEY_DINNER = "Dinner";
    private  static  final String KEY_OTHER = "Others";



    public DBHandler(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    //creating Tables


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_AMOUNT_DETAIL_TABLE="CREATE TABLE " + TABLE_AMOUNT_DETAIL +"("
                +KEY_DATE+" INTEGER PRIMARY KEY,"
                +KEY_BREAKFAST + " INTEGER,"
                +KEY_LUNCH + " INTEGER,"
                +KEY_DINNER + " INTEGER,"
                +KEY_OTHER + " INTEGER"+ ")";
        db.execSQL(CREATE_AMOUNT_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXITS " + TABLE_AMOUNT_DETAIL );

        onCreate(db);
    }
    void addAmount(Amount amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_DATE, amount.getDate());
        values.put(KEY_BREAKFAST,amount.getBreakfast());
        values.put(KEY_DINNER,amount.getDinner());
        values.put(KEY_LUNCH,amount.getLunch());
        values.put(KEY_OTHER,amount.getOthers());

        // Inserting Row
        db.insert(TABLE_AMOUNT_DETAIL, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single day
    Amount getAmountDet(int date) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Amount amount=new Amount(cursor.getInt(0),
                cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
        // return amount
        return amount;
    }

    // code to get all amounts in a list view
    public List<Amount> getAllAmounts() {
        List<Amount> AmountList = new ArrayList<Amount>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_AMOUNT_DETAIL +" ORDER BY "+ KEY_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Amount amount = new Amount();
                amount.setDate(cursor.getInt(0));

                amount.setBreakfast(cursor.getInt(1));
                amount.setLunch(cursor.getInt(2));
                amount.setDinner(cursor.getInt(2));
                amount.setOthers(cursor.getInt(2));
                // Adding amounts to list
                AmountList.add(amount);
            } while (cursor.moveToNext());
        }
        db.close(); // Closing database connection
        // return amount list
        return AmountList;
    }

    public int getDetail(int date,int pos){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(pos);
    }


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


    // code to update the single amount detail
    public int updateAmount(Amount amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
                new String[] { String.valueOf(amount.date) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        int bfast=cursor.getInt(1);
        int lnch=cursor.getInt(2);
        int dinnr=cursor.getInt(3);
        int othr=cursor.getInt(4);
        ContentValues values = new ContentValues();
        values.put(KEY_BREAKFAST, amount.getBreakfast()+bfast);
        values.put(KEY_LUNCH, amount.getLunch()+lnch);
        values.put(KEY_DINNER, amount.getDinner()+dinnr);
        values.put(KEY_OTHER, amount.getOthers()+othr);




        // updating row
        return db.update(TABLE_AMOUNT_DETAIL, values, KEY_DATE+ " = ?",
                new String[] { String.valueOf(amount.getDate()) });
    }
    public  void setAmount(int date,int pos,int bfast,int lnch,int dinner,int others){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_AMOUNT_DETAIL, new String[] { KEY_DATE,
                        KEY_BREAKFAST,KEY_LUNCH,KEY_DINNER,KEY_OTHER }, KEY_DATE + "=?",
                new String[] { String.valueOf(date) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ContentValues values = new ContentValues();
        switch (pos){
            case 1:values.put(KEY_BREAKFAST,bfast);
                break;
            case 2:values.put(KEY_LUNCH,lnch);
                break;
            case 3: values.put(KEY_DINNER,dinner);
                 break;
            case 4:values.put(KEY_OTHER,others);
                break;

        }
         db.update(TABLE_AMOUNT_DETAIL, values, KEY_DATE+ " = ?",
                new String[] { String.valueOf(date) });



    }

    // Deleting single amount entry
    public void deleteAmount(int date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AMOUNT_DETAIL, KEY_DATE + " = ?",
                new String[] { String.valueOf(date) });
        db.close();
    }



}