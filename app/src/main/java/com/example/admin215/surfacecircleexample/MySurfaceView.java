package com.example.admin215.surfacecircleexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by Admin215 on 11.01.2018.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    int x=-1000, y=-1000, r=0;

    public MySurfaceView(Context context) {
        super(context);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                Paint paint = new Paint();
                long nowMillis, nextMillis, ellapsed;
                nowMillis = System.currentTimeMillis();
                while (true){
                    nextMillis = System.currentTimeMillis();
                    ellapsed = nextMillis - nowMillis;
                    if(ellapsed>50) {
                        Canvas canvas = getHolder().lockCanvas();
                        if (canvas != null) {
                            canvas.drawColor(Color.BLUE);
                            paint.setColor(Color.YELLOW);
                            canvas.drawCircle(x, y, r, paint);
                            r += 5;
                            nowMillis = nextMillis;
                        }
                        getHolder().unlockCanvasAndPost(canvas);
                    }

                }
            }
        };
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            x = (int)event.getX();
            y = (int) event.getY();
            r = 0;
        }
        return true;
    }
}
