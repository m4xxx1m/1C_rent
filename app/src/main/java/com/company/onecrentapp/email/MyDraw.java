package com.company.onecrentapp.email;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MyDraw extends View {
    public MyDraw (Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int y = -canvas.getHeight();
        int x = 0;
        canvas.rotate(45, 0, 0);
        while (y < canvas.getHeight()) {
            canvas.drawLine(0, y, this.getWidth() * 2, y, paint);
            y += 30;
        }
        canvas.rotate(-45, 0, 0);
    }
}