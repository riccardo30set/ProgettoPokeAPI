package com.example.progettopokeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;

public class CanvasPokemon extends View{

    Paint paint;
    Rect rect;
    Paint cerchio;
    Paint bordoCerchio;

    private float x,y;
    private float velx,vely;
    private Bitmap b;



    public CanvasPokemon(Context context) {
        super(context);
        paint=new Paint();
        rect=new Rect();
        cerchio=new Paint();
        bordoCerchio=new Paint();

        b=BitmapFactory.decodeResource(getResources(),R.drawable.logo);


        x=90;
        y=130;

    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        cerchio.setColor(Color.WHITE);
        cerchio.setStrokeWidth(500);

        bordoCerchio.setColor(Color.BLACK);
        bordoCerchio.setStrokeWidth(50);



        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);

        canvas.drawColor(Color.WHITE);





        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight()/2,paint);

        canvas.drawLine(1200,1000,0, 1000, bordoCerchio);

        canvas.drawCircle(545,1000, 360, bordoCerchio);

        canvas.drawCircle(545,1000, 300, cerchio);

        canvas.drawBitmap(b,x,y,null);












    }

}
