package com.example.user.spender;

/**
 * Created by user on 08-08-2020.
 */
public class StatsMonth {
    float avgMonth,avgTillMonth;
    String yearMonth;
    int total=0,week1=0,week2=0,week3=0,week4=0,week5=0,week6=0,isWeek6=0,isMonthChanged=0;

//    String CREATE_STATS_MONTH_TABLE="CREATE TABLE " + TABLE_STATS_MONTH +"("
//            +KEY_YEAR_MONTH+" + PRIMARY KEY,"
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
//
//    public StatsMonth(float avgMonth, float avgTillMonth, String yearMonth, int total, int week1,
//                      int week2, int week3, int week4, int week5, int week6, int isWeek6, int isMonthChanged)
    public StatsMonth(String yearMonth,int total,int week1,
                      int week2, int week3, int week4, int week5, int week6, int isWeek6,
                      float avgMonth, float avgTillMonth,int isMonthChanged){
        this.avgMonth = avgMonth;
        this.avgTillMonth = avgTillMonth;
        this.yearMonth = yearMonth;
        this.total = total;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.week6 = week6;
        this.isWeek6 = isWeek6;
        this.isMonthChanged = isMonthChanged;
    }
    public StatsMonth(){

    }

    public float getAvgMonth() {
        return avgMonth;
    }

    public float getAvgTillMonth() {
        return avgTillMonth;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public int getTotal() {
        return total;
    }

    public int getWeek1() {
        return week1;
    }

    public int getWeek2() {
        return week2;
    }

    public int getWeek3() {
        return week3;
    }

    public int getWeek4() {
        return week4;
    }

    public int getWeek5() {
        return week5;
    }

    public int getWeek6() {
        return week6;
    }

    public int getIsWeek6() {
        return isWeek6;
    }

    public int getIsMonthChanged() {
        return isMonthChanged;
    }

    public void setAvgMonth(float avgMonth) {
        this.avgMonth = avgMonth;
    }

    public void setAvgTillMonth(float avgTillMonth) {
        this.avgTillMonth = avgTillMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setWeek1(int week1) {
        this.week1 = week1;
    }

    public void setWeek2(int week2) {
        this.week2 = week2;
    }

    public void setWeek3(int week3) {
        this.week3 = week3;
    }

    public void setWeek4(int week4) {
        this.week4 = week4;
    }

    public void setWeek5(int week5) {
        this.week5 = week5;
    }

    public void setWeek6(int week6) {
        this.week6 = week6;
    }

    public void setIsWeek6(int isWeek6) {
        this.isWeek6 = isWeek6;
    }

    public void setIsMonthChanged(int isMonthChanged) {
        this.isMonthChanged = isMonthChanged;
    }
}
