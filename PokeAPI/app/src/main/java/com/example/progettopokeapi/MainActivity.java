package com.example.progettopokeapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class
MainActivity extends AppCompatActivity {

    CanvasPokemon canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        canvas=new CanvasPokemon(this);


        setContentView(canvas);

    }


}