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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintSet;

public class CanvasPokemon extends View implements View.OnClickListener{

    Paint paint;
    Rect rect;
    Paint cerchio;
    Paint bordoCerchio;


    private float x,y;
    private float velx,vely;
    private Bitmap logo;
    private Bitmap pikachu;

    public CanvasPokemon(Context context) {
        super(context);
        paint=new Paint();
        rect=new Rect();
        cerchio=new Paint();
        bordoCerchio=new Paint();

        setOnClickListener(this);

        logo=BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        pikachu=BitmapFactory.decodeResource(getResources(),R.drawable.pokemon25);





    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        cerchio.setColor(Color.WHITE);
        cerchio.setStrokeWidth(500);
        cerchio.setTextAlign(Paint.Align.CENTER);

        bordoCerchio.setColor(Color.BLACK);
        bordoCerchio.setStrokeWidth(50);
        bordoCerchio.setTextAlign(Paint.Align.CENTER);


        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setTextAlign(Paint.Align.CENTER);





        canvas.drawColor(Color.WHITE);






        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight()/2,paint);

        canvas.drawLine(1200,canvas.getClipBounds().centerY(), 0, canvas.getClipBounds().centerY(), bordoCerchio);

        canvas.drawCircle(545,canvas.getClipBounds().centerY(), 360, bordoCerchio);

        canvas.drawCircle(545,canvas.getClipBounds().centerY(), 300, cerchio);

        canvas.drawBitmap(logo,canvas.getClipBounds().left+70,canvas.getClipBounds().left,null);

        canvas.drawBitmap(pikachu,400,400,null);

    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(),ListActivity.class);

        getContext().startActivity(intent);


    }

}
