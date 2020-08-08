package com.example.user.spender;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;

/**
 * Created by user on 05-08-2020.
 */
public class Util {
    public void set_theme(Context c){
        SettingsDBHandler setting_db = new SettingsDBHandler(c);
        SettingClass theme;
        int theme_value;

        try{
            theme = setting_db.getSettingDet("Theme");
            theme_value=theme.getSettingValue();
//            Toast.makeText(c ,String.valueOf(theme_value)+ " got from db", Toast.LENGTH_LONG).show();
        }
        catch (CursorIndexOutOfBoundsException s){
            setting_db.addSettingDet(new SettingClass("Theme",0));
            theme_value = 0;
//            Toast.makeText(c , "Setting first time value to 0", Toast.LENGTH_LONG).show();
        }

        if (theme_value==0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
