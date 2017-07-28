package com.example.user.spender;


public class Amount {

    int date;
    int breakfast=0,lunch=0,dinner=0,others=0;

    public Amount(int d,int b,int l,int din,int o ){
        date=d;
        breakfast=b;
        lunch=l;
        dinner=din;
        others=o;
    }
    public Amount(){

    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public void setOthers(int others) {
        this.others = others;
    }



    public int getBreakfast() {

        return breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public int getOthers() {
        return others;
    }


}
