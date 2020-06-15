package com.example.mso_lab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;


public class DrawView  extends View {
    private Paint paint;
    private Path path = new Path();

    public DrawView(Context context){
        super(context);
        Init();
    }

    private void Init(){
        paint = new Paint();
        paint.setARGB(255,255, 0, 195);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas)  {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                break;
        }
        postInvalidate();
        return true;
    }
}
