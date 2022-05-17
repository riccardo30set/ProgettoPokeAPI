package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    int[] images=new int[898];

    String[] nome= new String[898];

    String[] tipoPokemon= new String[898];

    ListView listaPokemon;

    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String id;

        this.RestCallPokemonNames();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ATTENZIONE, questi nomi sono qui perche la rest call viene eseguita su un thread a parte, quindi capita che l'adapter carichi l'array prima che venga modificato.
        //niente fadda è gay e li ha tolti


        for(int i=1; i<899;i++){

            id="pokemon"+i;
            images[i-1]=getResources().getIdentifier(id, "drawable", getPackageName());
            if(i<10)
                tipoPokemon[i-1]="#00"+i;
            else if(i<100)
                tipoPokemon[i-1]="#0"+i;
            else
                tipoPokemon[i-1]="#"+i;
        }
        listaPokemon=(ListView)findViewById(R.id.listPokedex);

        lAdapter=new com.example.progettopokeapi.ListAdapter(this,tipoPokemon,nome,images);

        listaPokemon.setAdapter(lAdapter);

        Intent intent = new Intent(this, PokemonActivity.class);

        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("pokemonId", (listaPokemon.getItemAtPosition(i+1).toString()));
                intent.putExtra("pokemonName",(nome[i].toString()));
                startActivity(intent);
            }
        });

    }
    public void RestCallPokemonNames(){
        RequestQueue queue = Volley.newRequestQueue(ListActivity.this);
        String url = "https://pokeapi.co/api/v2/pokedex/1/";

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                for(int i=1;i<899;i++){
                                    try {
                                        nome[i-1]=response.getJSONArray("pokemon_entries").getJSONObject(i-1).getJSONObject("pokemon_species").getString("name");
                                        nome[i-1]=nome[i-1].substring(0,1).toUpperCase()+nome[i-1].substring(1,nome[i-1].length());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        for(int i=1;i<899;i++){
                            nome[i-1]="ERRORE, VIVA NAPOLI";
                        }
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public boolean onCreateOptionsMenu(Menu menu){  getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Cerca il Pokemon");
        searchView.animate();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {






                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}