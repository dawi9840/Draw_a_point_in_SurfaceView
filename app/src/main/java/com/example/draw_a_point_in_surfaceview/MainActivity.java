package com.example.draw_a_point_in_surfaceview;
/*** Draw a point in SurfaceView on button click.***/

import androidx.appcompat.app.AppCompatActivity;

import android.animation.FloatEvaluator;
import android.annotation.SuppressLint;
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
        Paint p_red = new Paint();
        Paint p_blue = new Paint();
        Paint p_green = new Paint();
        public DrawLine(Context context) {
            super(context);
            p_red.setColor(Color.RED);
            p_red.setStyle(Paint.Style.STROKE);
            p_red.setStrokeWidth(10);
            p_red.setAntiAlias(true);

            p_blue.setColor(Color.BLUE);
            p_blue.setStyle(Paint.Style.STROKE);
            p_blue.setStrokeWidth(10);
            p_blue.setAntiAlias(true);

            p_green.setColor(Color.GREEN);
            p_green.setStyle(Paint.Style.STROKE);
            p_green.setStrokeWidth(10);
            p_green.setAntiAlias(true);
        }

        @Override
        public void onDraw(Canvas canvas) {
            float cap_w = 1088;
            float cap_h = 1088;
            float specific_frame = 300;

            @SuppressLint("DrawAllocation") DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            int screen_w = displayMetrics.widthPixels;
            int navigationBar = isHaveNavigationBar();
            int screen_h = 0;

            if(navigationBar == 1){
                getNavigationBarHeight();
                screen_h = displayMetrics.heightPixels + getNavigationBarHeight();
            }else{
                screen_h = displayMetrics.heightPixels;
            }

            System.out.println("screen_w:" + screen_w + ", screen_h:" + screen_h); // 1440, 3040

            // AiVirtualMouseProject define area.
            canvas.drawRect(specific_frame, specific_frame, cap_w-specific_frame, cap_h-specific_frame, p_green);

            // pixel_p8:(234.228104, 1070.018188), convert_p8:(69182.609375, 1597255.125000)
            @SuppressLint("DrawAllocation") List<Float> pixel_p8_L_DOWN = new ArrayList<>();
            pixel_p8_L_DOWN.add((float) 234.228104);
            pixel_p8_L_DOWN.add((float) 1070.018188);

            // pixel_p8:(218.616226, 34.274849), convert_p8:(64623.937500, 51926.074219)
            @SuppressLint("DrawAllocation") List<Float> pixel_p8_L_UP = new ArrayList<>();
            pixel_p8_L_UP.add((float) 218.616226);
            pixel_p8_L_UP.add((float) 34.274849);

            // pixel_p8:(847.106262, 19.184063), convert_p8:(248143.031250, 29410.621094)
            @SuppressLint("DrawAllocation") List<Float> pixel_p8_R_UP = new ArrayList<>();
            pixel_p8_R_UP.add((float) 847.106262);
            pixel_p8_R_UP.add((float) 19.184063);

            // pixel_p8:(863.990051, 1075.021973), convert_p8:(253073.093750, 1604720.750000)
            @SuppressLint("DrawAllocation") List<Float> pixel_p8_R_DOWN = new ArrayList<>();
            pixel_p8_R_DOWN.add((float) 863.990051);
            pixel_p8_R_DOWN.add((float) 1075.021973);

            // Mapping area.
            canvas.drawRect(pixel_p8_L_UP.get(0), pixel_p8_R_UP.get(1), pixel_p8_R_DOWN.get(0), pixel_p8_R_DOWN.get(1), p_blue);

            canvas.drawPoint(pixel_p8_L_UP.get(0), pixel_p8_L_UP.get(1), p_red);
            canvas.drawPoint(pixel_p8_L_DOWN.get(0), pixel_p8_L_DOWN.get(1), p_red);
            canvas.drawPoint(pixel_p8_R_UP.get(0), pixel_p8_R_UP.get(1), p_red);
            canvas.drawPoint(pixel_p8_R_DOWN.get(0), pixel_p8_R_DOWN.get(1), p_red);


            canvas.drawPoint((float)214.279999, (float)-1.649035, p_red);  // close, L_UP
            canvas.drawPoint((float)222.160675, (float) 5.394957, p_red); // far, L_UP

            canvas.drawPoint((float) 851.655212, (float) 13.403265, p_red); // close, R_UP
            canvas.drawPoint((float) 852.348267, (float) 18.792240, p_red); // far, R_UP

            canvas.drawPoint((float) 220.247574, (float) 1083.393188, p_red); // close, L_DOWN
            canvas.drawPoint((float) 223.997360, (float) 1081.489990, p_red); // far, L_DOWN

            canvas.drawPoint((float) 857.866272, (float) 1084.606445, p_red); // close, R_DOWN
            canvas.drawPoint((float) 860.963135, (float) 1083.215942, p_red); // far, R_DOWN

            // Convert Coordinates
            @SuppressLint("DrawAllocation") List<Float> convert_p8 = new ArrayList<>();
            @SuppressLint("DrawAllocation") FloatEvaluator f = new FloatEvaluator();
            convert_p8.add(
                    f.evaluate(
                            pixel_p8_R_DOWN.get(0), (cap_w - specific_frame), (screen_w)
                    )
            );
            convert_p8.add(
                    f.evaluate(
                            pixel_p8_R_DOWN.get(1), (cap_h - specific_frame), (screen_h)
                    )
            );

            canvas.drawPoint(convert_p8.get(0), convert_p8.get(1), p_green);

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
