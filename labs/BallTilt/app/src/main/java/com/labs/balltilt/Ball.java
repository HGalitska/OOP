package com.labs.balltilt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by helen on 10/20/17.
 */

public class Ball extends View {

    public float x; // x position of Ball
    public float y; // y position of Ball
    private final int r; // ball radius

    private final Paint ballPaint = new Paint();

    public Ball(Context context, float x, float y, int r) {
        super(context);

        ballPaint.setColor(0xFFFFDFE5);
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x, y, r, ballPaint);
    }
}