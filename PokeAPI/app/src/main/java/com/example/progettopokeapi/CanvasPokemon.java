package com.example.progettopokeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.TranslateAnimation;


public class CanvasPokemon extends View implements View.OnClickListener, View.OnLongClickListener{

    Paint paint;
    Rect rect;
    Paint cerchio;
    Paint bordoCerchio;
    Matrix matrix;


    private float x,y;
    private float velx,vely;
    private Bitmap logo;
    private Bitmap pikachu;


    private MediaPlayer Easter_EGG;

    public CanvasPokemon(Context context) {
        super(context);
        paint=new Paint();
        rect=new Rect();
        cerchio=new Paint();
        bordoCerchio=new Paint();
        matrix=new Matrix();

        setOnClickListener(this);
        setOnLongClickListener(this);

        logo=BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        pikachu=BitmapFactory.decodeResource(getResources(),R.drawable.pokemon25);

        Easter_EGG=MediaPlayer.create(context,R.raw.easter);

        x=2;
        y=2;
        velx=2;
        vely=3;



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


        matrix.reset();
        matrix.postTranslate(canvas.getWidth()/90,-canvas.getHeight()/50);
        matrix.postRotate(-10);




        canvas.drawColor(Color.WHITE);






        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight()/2,paint);

        canvas.drawLine(1200,canvas.getClipBounds().centerY(), 0, canvas.getClipBounds().centerY(), bordoCerchio);

        canvas.drawCircle(545,canvas.getClipBounds().centerY(), 360, bordoCerchio);

        canvas.drawCircle(545,canvas.getClipBounds().centerY(), 300, cerchio);

        canvas.drawBitmap(logo,canvas.getClipBounds().left+70,canvas.getClipBounds().left,null);


        canvas.drawBitmap(pikachu, x, y, null);
        x+=velx;
        y+=vely;
        if(x+pikachu.getWidth()>canvas.getWidth() || x<0){
            velx=-velx;
        }
        if(y+pikachu.getHeight()>canvas.getHeight() || y<0){
            vely=-vely;
        }

        invalidate();












    }


    @Override
    public void onClick(View view) {








    }


    @Override
    public boolean onLongClick(View view) {

        Easter_EGG.start();


        return false;
    }
}
