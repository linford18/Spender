package com.example.user.spender;

/**
 * Created by user on 08-08-2020.
 */
public class StatsWeek {
    float avgWeek;
    String yearMonth,yMonthWeek;
    int weekNum=0,totalBfast=0,totalDinner=0,totalLunch=0,totalOther=0,isWeekChanged=0;

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
//    db.execSQL(CREATE_STATS_WEEK_TABLE);

    public StatsWeek(int week_num, String year_month, String y_month_week,int total_bfast,
                     int total_lunch, int total_dinner, int total_other, float avg_week,
                     int is_week_changed ){
        weekNum=week_num;
        yearMonth = year_month;
        yMonthWeek = y_month_week;
        totalBfast = total_bfast;
        totalLunch = total_lunch;
        totalDinner = total_dinner;
        totalOther = total_other;
        avgWeek = avg_week;
        isWeekChanged = is_week_changed;


    }
    public StatsWeek(){

    }

    public float getAvgWeek() {
        return avgWeek;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public String getyMonthWeek() {
        return yMonthWeek;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public int getTotalBfast() {
        return totalBfast;
    }

    public int getTotalDinner() {
        return totalDinner;
    }

    public int getTotalLunch() {
        return totalLunch;
    }

    public int getTotalOther() {
        return totalOther;
    }

    public int getIsWeekChanged() {
        return isWeekChanged;
    }

    public void setAvgWeek(float avgWeek) {
        this.avgWeek = avgWeek;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public void setyMonthWeek(String yMonthWeek) {
        this.yMonthWeek = yMonthWeek;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setTotalBfast(int totalBfast) {
        this.totalBfast = totalBfast;
    }

    public void setTotalDinner(int totalDinner) {
        this.totalDinner = totalDinner;
    }

    public void setTotalLunch(int totalLunch) {
        this.totalLunch = totalLunch;
    }

    public void setTotalOther(int totalOther) {
        this.totalOther = totalOther;
    }

    public void setIsWeekChanged(int isWeekChanged) {
        this.isWeekChanged = isWeekChanged;
    }
}
