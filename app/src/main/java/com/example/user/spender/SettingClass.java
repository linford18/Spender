package com.example.user.spender;

/**
 * Created by user on 05-08-2020.
 */
public class SettingClass {

    String s_type;
    int value=0;

    public SettingClass(String s,int v ){
        s_type=s;
        value=v;
    }
    public SettingClass(){

    }

    public String getSettingType() {
        return s_type;
    }

    public void setSettingType(String s_type) {
        this.s_type = s_type;
    }

    public void setSettingValue(int value) {
        this.value = value;
    }

    public int getSettingValue() {

        return value;
    }


}
