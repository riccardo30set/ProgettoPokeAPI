package com.example.progettopokeapi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasPokemon extends View {

    Paint paint;
    Rect rect;
    Paint cerchio;
    Paint bordoCerchio;


    public CanvasPokemon(Context context) {
        super(context);
        paint=new Paint();
        rect=new Rect();
        cerchio=new Paint();
        bordoCerchio=new Paint();

    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        cerchio.setColor(Color.WHITE);
        cerchio.setStrokeWidth(500);

        bordoCerchio.setColor(Color.BLACK);
        bordoCerchio.setStrokeWidth(50);



        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);


        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight()/2,paint);

        canvas.drawLine(1100,930,0, 930, bordoCerchio);

        canvas.drawCircle(530,1000, 360, bordoCerchio);

        canvas.drawCircle(530,1000, 300, cerchio);


    }

}
