package com.example.draw_a_point_in_surfaceview;
/*** Draw a point in SurfaceView on button click.***/

import androidx.appcompat.app.AppCompatActivity;

import android.animation.FloatEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DrawLine drawLine = new DrawLine(this);
//        drawLine.setBackgroundColor(Color.WHITE);


        drawLine.setBackgroundColor(Color.TRANSPARENT);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(drawLine);
            }
        });

    }

    class DrawLine extends View {
        Paint paint = new Paint();
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        public DrawLine(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);

            paint1.setColor(Color.BLUE);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setStrokeWidth(10);
            paint1.setAntiAlias(true);

            paint2.setColor(Color.GREEN);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(10);
            paint2.setAntiAlias(true);
        }

        @Override
        public void onDraw(Canvas canvas) {
            float cap_w = 1088;
            float cap_h = 2280;
            float specific_frame = 300;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            int screen_w = displayMetrics.widthPixels;
            int navigationBar = isHaveNavigationBar();
            int screen_h = 0;

            if(navigationBar == 1){
                getNavigationBarHeight();
                screen_h = displayMetrics.heightPixels + getNavigationBarHeight(); // 2280
            }else{
                screen_h = displayMetrics.heightPixels; //2069
            }


            canvas.drawLine(specific_frame, specific_frame, specific_frame, cap_h-specific_frame, paint2);
            canvas.drawLine(cap_w-specific_frame, specific_frame, cap_w-specific_frame, cap_h-specific_frame, paint2);
            canvas.drawLine(specific_frame, specific_frame, cap_w-specific_frame, specific_frame, paint2);
            canvas.drawLine(specific_frame, cap_h-specific_frame, cap_w-specific_frame, cap_h-specific_frame, paint2);

//            canvas.drawLine(specific_frame, specific_frame, specific_frame, screen_h-specific_frame, paint);
//            canvas.drawLine(screen_w-specific_frame, specific_frame, screen_w-specific_frame, screen_h-specific_frame, paint);
//            canvas.drawLine(specific_frame, specific_frame, screen_w-specific_frame, specific_frame, paint);
//            canvas.drawLine(specific_frame, screen_h-specific_frame, screen_w-specific_frame, screen_h-specific_frame, paint);

            float add_value = 50.0F;

            // pixel_p8:(234.228104, 1070.018188), convert_p8:(69182.609375, 1597255.125000)
            List<Float> pixel_p8_L_DOWN = new ArrayList<>();
            pixel_p8_L_DOWN.add((float) 234.228104);
            pixel_p8_L_DOWN.add((float) 1070.018188);
            canvas.drawLine(pixel_p8_L_DOWN.get(0)-add_value, pixel_p8_L_DOWN.get(1), pixel_p8_L_DOWN.get(0)+add_value, pixel_p8_L_DOWN.get(1), paint1);
            canvas.drawLine(pixel_p8_L_DOWN.get(0), pixel_p8_L_DOWN.get(1)-add_value, pixel_p8_L_DOWN.get(0), pixel_p8_L_DOWN.get(1)+add_value, paint1);

            // pixel_p8:(218.616226, 34.274849), convert_p8:(64623.937500, 51926.074219)
            List<Float> pixel_p8_L_UP = new ArrayList<>();
            pixel_p8_L_UP.add((float) 218.616226);
            pixel_p8_L_UP.add((float) 34.274849);
            canvas.drawLine(pixel_p8_L_UP.get(0)-add_value, pixel_p8_L_UP.get(1), pixel_p8_L_UP.get(0)+add_value, pixel_p8_L_UP.get(1), paint1);
            canvas.drawLine(pixel_p8_L_UP.get(0), pixel_p8_L_UP.get(1)-add_value, pixel_p8_L_UP.get(0), pixel_p8_L_UP.get(1)+add_value, paint1);

            // pixel_p8:(847.106262, 19.184063), convert_p8:(248143.031250, 29410.621094)
            List<Float> pixel_p8_R_UP = new ArrayList<>();
            pixel_p8_R_UP.add((float) 847.106262);
            pixel_p8_R_UP.add((float) 19.184063);
            canvas.drawLine(pixel_p8_R_UP.get(0)-add_value, pixel_p8_R_UP.get(1), pixel_p8_R_UP.get(0)+add_value, pixel_p8_R_UP.get(1), paint1);
            canvas.drawLine(pixel_p8_R_UP.get(0), pixel_p8_R_UP.get(1)-add_value, pixel_p8_R_UP.get(0), pixel_p8_R_UP.get(1)+add_value, paint1);

            // pixel_p8:(863.990051, 1075.021973), convert_p8:(253073.093750, 1604720.750000)
            List<Float> pixel_p8_R_DOWN = new ArrayList<>();
            pixel_p8_R_DOWN.add((float) 863.990051);
            pixel_p8_R_DOWN.add((float) 1075.021973);
            canvas.drawLine(pixel_p8_R_DOWN.get(0)-add_value, pixel_p8_R_DOWN.get(1), pixel_p8_R_DOWN.get(0)+add_value, pixel_p8_R_DOWN.get(1), paint1);
            canvas.drawLine(pixel_p8_R_DOWN.get(0), pixel_p8_R_DOWN.get(1)-add_value, pixel_p8_R_DOWN.get(0), pixel_p8_R_DOWN.get(1)+add_value, paint1);

            // Mapping area.
            canvas.drawLine(pixel_p8_L_UP.get(0), pixel_p8_R_UP.get(1), pixel_p8_R_UP.get(0), pixel_p8_R_UP.get(1), paint);
            canvas.drawLine(pixel_p8_L_UP.get(0), pixel_p8_R_DOWN.get(1), pixel_p8_R_UP.get(0), pixel_p8_R_DOWN.get(1), paint);
            canvas.drawLine(pixel_p8_L_UP.get(0), pixel_p8_R_UP.get(1), pixel_p8_L_UP.get(0), pixel_p8_R_DOWN.get(1), paint);
            canvas.drawLine(pixel_p8_R_UP.get(0), pixel_p8_R_UP.get(1), pixel_p8_R_UP.get(0), pixel_p8_R_DOWN.get(1), paint);
        }

        private int isHaveNavigationBar(){
            // Check have navigation bar.
            int id = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
            if(id > 0){
                return 1;
            }else {
                return 0;
            }
        }

        private int getNavigationBarHeight() {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }

    }



}