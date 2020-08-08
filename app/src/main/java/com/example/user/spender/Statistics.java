package com.example.user.spender;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by user on 26-07-2020.
 */
public class Statistics extends Activity {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH)+ 1;
    int year = calendar.get(Calendar.YEAR)%2000;
    ScrollView root;
    GridLayout gridLayout;
    LinearLayout l1,l2,l3;
    GradientDrawable gd,gd2,gd3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.statistics);
        root=(ScrollView) findViewById(R.id.stats);
        l1=(LinearLayout) findViewById(R.id.Hcardlinear1);
        l2=(LinearLayout) findViewById(R.id.Hcardlinear2);
        l3=(LinearLayout) findViewById(R.id.Hcardlinear3);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            root.setBackgroundResource(R.drawable.bag_cal_dark);
        } else {

            root.setBackgroundResource(R.drawable.bag_cal);
        }

//        int h = l1.getHeight();
//        ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());//#7EE8FA#EEC0C6
//        mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h, new int[]{Color.parseColor("#7EE8FA"), Color.parseColor("#EEC0C6")},new float[]{0,1}, Shader.TileMode.CLAMP));
//
//        l1.setBackground(mDrawable);
        gridLayout=(GridLayout)findViewById(R.id.statsGrid);


//        GradientDrawable bgShape = (GradientDrawable)l1.getBackground();
//        bgShape.setColor(new ColorStateList(new int[]{0,1}, new int[]{Color.parseColor("#7EE8FA"), Color.parseColor("#EEC0C6")}));
//
//        int[] colors = new int[2];
//        colors[0] = Color.parseColor("#7EE8FA");
//        colors[1] = Color.parseColor("#EEC0C6");
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.LEFT_RIGHT, colors);
//
//        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
//        gd.setGradientRadius(300f);
//        gd.setCornerRadius(0f);
//        l1.setBackground(gd);

//        String[] dark_color_l = {"#009FFD","#6E72FC","#E975A8","#2876F9","#39E5B6","#F977CE",
//                "#F6FBA2", "#0CBABA", "#F7B42C", "#37D5D6", "#647DEE","#09C6F9","#FFDD00","#FC9842","#F67062"};
//        String[] dark_color_d = {"#2A2A72","#AD1DEB","#726CF8","#6D17CB","#70B2D9","#C373F2",
//                "#20DED3", "#380036","#FC575E","#36096D", "#7F53AC", "#045DE9", "#FBB034","#FE5F75","#FC5296"};
//        String[] light_color_l = {"#BDD8FE","#B3F6D8","#D3D3D3","#A1BAFE"};
//        String[] light_color_d = {"#E186B4","#52A7C1","#1DD1A1","#8D5185"};
//
////        int[] dark_gradient_l = {Color.parseColor("#009FFD")};
////        int[] dark_gradient_d = {Color.parseColor("#2A2A72")};
////        int[] light_gradient_l = {Color.parseColor("#7EE8FA")};
////        int[] light_gradient_d = {Color.parseColor("#7EE8FA")};
////        dark_gradient_l = [Color.parseColor("#7EE8FA")]
//
//        int[] dark_gradient_l = new int[dark_color_l.length];
//        int[] dark_gradient_d = new int[dark_color_d.length];
//        //int[] dark_gradient_l = new int[dark_color_l.length];
//        //int[] dark_gradient_l = new int[dark_color_l.length];
//        int[] pos_taken = {-1,-1,-1};
//
////        for (int pos=0, pos<3;pos++){
////            System.out.println(i);
////        }
//        Random rand = new Random();
//        int rand_int1,found;
//        for(int pos=0; pos<3; pos++){
//
//            while(true){
//                rand_int1 = rand.nextInt(1000)%dark_color_d.length;
//                System.out.println("Random: "+String.valueOf(rand_int1));
//                found=0;
//                for(int i=0;i<=pos;i++){
//                    if (pos_taken[i]== rand_int1) {
//                        found = 1;
//                        break;
//                    }
//                }
//                if (found==0)
//                    break;
//
//            }
//            pos_taken[pos]= rand_int1;
//            System.out.println(String.valueOf(pos_taken[pos]));
//        }
//        int[] colors = new int[2];
//        colors[0] = Color.parseColor(dark_color_l[pos_taken[0]]);
//        colors[1] = Color.parseColor(dark_color_d[pos_taken[0]]);
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.LEFT_RIGHT, colors);
//
//        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
//        gd.setGradientRadius(300f);
//        gd.setCornerRadius(0f);
//        l1.setBackground(gd);
        set_card_bg();

        setSingleEvent(gridLayout);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        set_card_bg();
//    }

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
    private void set_card_bg(){
        String[] dark_color_l = {"#009FFD","#6E72FC","#E975A8","#2876F9","#39E5B6","#F977CE",
                "#0CBABA", "#F7B42C", "#37D5D6", "#647DEE","#09C6F9","#FFDD00","#FC9842","#F67062",
                "#ffc200","#009FFD","#ca71f1"};
        String[] dark_color_d = {"#2A2A72","#AD1DEB","#726CF8","#6D17CB","#70B2D9","#C373F2",
                "#380036","#FC575E","#36096D", "#7F53AC", "#045DE9", "#FBB034","#FE5F75","#FC5296",
                "#fca10b","#2A2A72","#8903c3"};
        String[] light_color_l = {"#BDD8FE","#B3F6D8","#D3D3D3","#A1BAFE","#F6FBA2","#EEC0C6","#EEC0C6",
        "#EEC0C6","#7CFFCB","#FFAC81","#F9C1B1","#FAD0C4","#F8CEEC","#D5ADC8","#F9D976","#77EED8","#DAD2F3",
        "#F9EA8F","#7DDFF8","#B9D1EB"};
        String[] light_color_d = {"#E186B4","#52A7C1","#1DD1A1","#8D5185","#20DED3","#7EE8FA","#D387AB",
        "#E58C8A","#74F2CE","#FF928B","#FB8085","#F1A7F1","#A88BEB","#FF8489","#F39F86","#9EABE4","#FBA8A4",
        "#AFF1DA","#B1ADE2","#F876DE"};

//        int[] dark_gradient_l = {Color.parseColor("#009FFD")};
//        int[] dark_gradient_d = {Color.parseColor("#2A2A72")};
//        int[] light_gradient_l = {Color.parseColor("#7EE8FA")};
//        int[] light_gradient_d = {Color.parseColor("#7EE8FA")};
//        dark_gradient_l = [Color.parseColor("#7EE8FA")]

//        int[] dark_gradient_l = new int[dark_color_l.length];
//        int[] dark_gradient_d = new int[dark_color_d.length];
//        //int[] dark_gradient_l = new int[dark_color_l.length];
//        //int[] dark_gradient_l = new int[dark_color_l.length];
        int[] pos_taken = {-1,-1,-1};

//        for (int pos=0, pos<3;pos++){
//            System.out.println(i);
//        }
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            Random rand = new Random();
            int rand_int1,found;
            for(int pos=0; pos<3; pos++){

                while(true){
                    rand_int1 = rand.nextInt(1000)%dark_color_d.length;
                    System.out.println("Random: "+String.valueOf(rand_int1));
                    found=0;
                    for(int i=0;i<=pos;i++){
                        if (pos_taken[i]== rand_int1) {
                            found = 1;
                            break;
                        }
                    }
                    if (found==0)
                        break;

                }
                pos_taken[pos]= rand_int1;
                System.out.println(String.valueOf(pos_taken[pos]));
            }
            int[] colors = new int[2];
            colors[0] = Color.parseColor(dark_color_l[pos_taken[0]]);
            colors[1] = Color.parseColor(dark_color_d[pos_taken[0]]);
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors);

            int[] colors2 = new int[2];
            colors2[0] = Color.parseColor(dark_color_l[pos_taken[1]]);
            colors2[1] = Color.parseColor(dark_color_d[pos_taken[1]]);
            gd2 = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors2);

            int[] colors3 = new int[2];
            colors3[0] = Color.parseColor(dark_color_l[pos_taken[2]]);
            colors3[1] = Color.parseColor(dark_color_d[pos_taken[2]]);
            gd3 = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors3);
        } else {

            Random rand = new Random();
            int rand_int1,found;
            for(int pos=0; pos<3; pos++){

                while(true){
                    rand_int1 = rand.nextInt(1000)%light_color_d.length;
                    System.out.println("Random: "+String.valueOf(rand_int1));
                    found=0;
                    for(int i=0;i<=pos;i++){
                        if (pos_taken[i]== rand_int1) {
                            found = 1;
                            break;
                        }
                    }
                    if (found==0)
                        break;

                }
                pos_taken[pos]= rand_int1;
                System.out.println(String.valueOf(pos_taken[pos]));
            }
            int[] colors = new int[2];
            colors[0] = Color.parseColor(light_color_l[pos_taken[0]]);
            colors[1] = Color.parseColor(light_color_d[pos_taken[0]]);
            gd = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors);

            int[] colors2 = new int[2];
            colors2[0] = Color.parseColor(light_color_l[pos_taken[1]]);
            colors2[1] = Color.parseColor(light_color_d[pos_taken[1]]);
            gd2 = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors2);

            int[] colors3 = new int[2];
            colors3[0] = Color.parseColor(light_color_l[pos_taken[2]]);
            colors3[1] = Color.parseColor(light_color_d[pos_taken[2]]);
            gd3 = new GradientDrawable(
                    GradientDrawable.Orientation.LEFT_RIGHT, colors3);
        }


        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientRadius(300f);
        gd.setCornerRadius(0f);
        l1.setBackground(gd);

        gd2.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd2.setGradientRadius(300f);
        gd2.setCornerRadius(0f);
        l2.setBackground(gd2);

        gd3.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd3.setGradientRadius(300f);
        gd3.setCornerRadius(0f);
        l3.setBackground(gd3);
    }

}
