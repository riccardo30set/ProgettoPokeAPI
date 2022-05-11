package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CanvasPokemon canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas=new CanvasPokemon(this);

        setContentView(canvas);

        Intent intent =new Intent(this,ListActivity.class);

        startActivity(intent);
    }
}