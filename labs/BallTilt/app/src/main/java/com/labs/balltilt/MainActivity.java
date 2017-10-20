package com.labs.balltilt;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    Ball myLovelyBall = null;
    PointF ballPosition, ballSpeed;

    int displayWidth, displayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout mainView = (android.widget.FrameLayout)findViewById(R.id.main_view);

        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);

        displayWidth = displaySize.x;
        displayHeight = displaySize.y;

        ballPosition = new PointF(displayWidth/2, displayHeight/2);
        ballSpeed = new PointF(0, 0);

        myLovelyBall = new Ball(this, ballPosition.x, ballPosition.y, 20);

        mainView.addView(myLovelyBall);
        myLovelyBall.invalidate();

        ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        ballSpeed.x = -event.values[0];
                        ballSpeed.y = event.values[1];
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
                },
                ((SensorManager)getSystemService(Context.SENSOR_SERVICE))
                        .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    Handler RedrawHandler = new Handler();
    Timer gameTimer = null;
    TimerTask gameUpdate = null;

    @Override
    public void onResume() //app starts
    {
        gameTimer = new Timer();
        gameUpdate = new TimerTask() {
            public void run() {

                ballPosition.x += ballSpeed.x;
                ballPosition.y += ballSpeed.y;

                if (ballPosition.x > displayWidth) ballPosition.x=0;
                if (ballPosition.y > displayHeight) ballPosition.y=0;
                if (ballPosition.x < 0) ballPosition.x=displayWidth;
                if (ballPosition.y < 0) ballPosition.y=displayHeight;

                myLovelyBall.x = ballPosition.x;
                myLovelyBall.y = ballPosition.y;

                RedrawHandler.post(new Runnable() {
                    public void run() {
                        myLovelyBall.invalidate();
                    }});
            }};
        gameTimer.schedule(gameUpdate, 10, 10);
        super.onResume();
    }
}
