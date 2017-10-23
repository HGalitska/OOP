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

    Ball myLovelyBall = null; //ball, that will move on the screen
    PointF ballPosition, ballSpeed; //its position and speed

    int displayWidth, displayHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout mainView = (android.widget.FrameLayout)findViewById(R.id.main_view);

        //getting device's display and its size
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);

        displayWidth = displaySize.x;
        displayHeight = displaySize.y;

        ballPosition = new PointF(displayWidth/2, displayHeight/2); //setting ball's initial position to screen center
        ballSpeed = new PointF(0, 0); //obviously, it shouldn't move

        myLovelyBall = new Ball(this, ballPosition.x, ballPosition.y, 20); //initializing oue ball

        mainView.addView(myLovelyBall); //adding ball to view
        myLovelyBall.invalidate(); //drawing ball

        ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener( //setting listener for accelerometer
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        ballSpeed.x = -event.values[0]; //changing ball's speed due to accelerometer values
                        ballSpeed.y = event.values[1];
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {} //do nothing
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
        gameUpdate = new TimerTask() { //actually, the update loop of this app
            public void run() {

                //setting ball's position after moving it to the tilted side
                ballPosition.x += ballSpeed.x;
                ballPosition.y += ballSpeed.y;

                //if ball is out of display bounds
                if (ballPosition.x > displayWidth) ballPosition.x = 0;
                if (ballPosition.y > displayHeight) ballPosition.y = 0;
                if (ballPosition.x < 0) ballPosition.x = displayWidth;
                if (ballPosition.y < 0) ballPosition.y = displayHeight;

                //actually moving our ball
                myLovelyBall.x = ballPosition.x;
                myLovelyBall.y = ballPosition.y;


                //redrawing ball
                RedrawHandler.post(new Runnable() {
                    public void run() {
                        myLovelyBall.invalidate();
                    }});
            }};
        gameTimer.schedule(gameUpdate, 10, 10); //run update loop every 10 milliseconds after 10 milliseconds delay
        super.onResume();
    }
}
