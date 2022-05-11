package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    int[] images= {R.drawable.pokemon1};

    String[] nome= {"Bulbasaur"};

    String[] tipoPokemon= {"Erba-Veleno"};

    ListView listaPokemon;

    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaPokemon=(ListView)findViewById(R.id.listPokedex);

        lAdapter=new com.example.progettopokeapi.ListAdapter(this,tipoPokemon,nome,images);

        listaPokemon.setAdapter(lAdapter);

        Intent intent = new Intent(this, PokemonActivity.class);

        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(ListActivity.this, "prova", Toast.LENGTH_SHORT).show();





            }
        });
    }
}