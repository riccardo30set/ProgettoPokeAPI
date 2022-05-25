package com.example.progettopokeapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

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

    String[] tipoPokemon= new String[898];

    String[] nomePokemon= new String[898];
    ListView listaPokemon;

    LAdapter lAdapter;
    ArrayList<ModelPokemon> arrayList =new ArrayList<ModelPokemon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String id;
        //this.RestCallPokemonNames();

        for(int i=1; i<899;i++){

            id="pokemon"+i;
            images[i-1]=getResources().getIdentifier(id, "drawable", getPackageName());
            if(i<10)
                tipoPokemon[i-1]="#00"+i;
            else if(i<100)
                tipoPokemon[i-1]="#0"+i;
            else
                tipoPokemon[i-1]="#"+i;
            ModelPokemon model=new ModelPokemon(null,tipoPokemon[i-1],images[i-1]);
            arrayList.add(model);
        }
        arrayList.get(0).setName("Bulbasaur");
        arrayList.get(1).setName("Ivysaur");
        arrayList.get(2).setName("Venusaur");
        arrayList.get(3).setName("Charmader");
        arrayList.get(4).setName("Charmeleon");
        arrayList.get(5).setName("Charizard");
        arrayList.get(6).setName("Squirtle");
        arrayList.get(7).setName("Wartortole");
        arrayList.get(8).setName("Blastoise");
        arrayList.get(9).setName("Caterpie");
        arrayList.get(10).setName("Metapod");
        arrayList.get(11).setName("Butterfly");
        this.RestCallPokemonNames(arrayList);

        listaPokemon=(ListView)findViewById(R.id.listPokedex);

        lAdapter=new LAdapter(this,arrayList);

        listaPokemon.setAdapter(lAdapter);

        Intent intent = new Intent(this, PokemonActivity.class);

        listaPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pkName=arrayList.get(i).getName().toString();
                intent.putExtra("pokemonName",pkName);
                String id="";
                for(int t=1; t<nomePokemon.length;t++){
                    if(nomePokemon[t-1].equals(pkName)){
                        id+=t;
                        t=nomePokemon.length;
                    }
                }
                intent.putExtra("pokemonId", id);
                startActivity(intent);
            }
        });
    }
    public void RestCallPokemonNames( ArrayList<ModelPokemon> modelPokemons){
        RequestQueue queue = Volley.newRequestQueue(ListActivity.this);
        String url = "https://pokeapi.co/api/v2/pokedex/1/";

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                for(int i=1;i<899;i++){
                                    try {
                                        modelPokemons.get(i-1).setName(response.getJSONArray("pokemon_entries").getJSONObject(i-1).getJSONObject("pokemon_species").getString("name"));
                                        modelPokemons.get(i-1).setName(modelPokemons.get(i-1).getName().substring(0,1).toUpperCase()+modelPokemons.get(i-1).getName().substring(1,modelPokemons.get(i-1).getName().length()));
                                        nomePokemon[i-1]=modelPokemons.get(i-1).getName();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        for(int i=1;i<899;i++){
                            modelPokemons.get(i-1).setName("ERRORE, VIVA NAPOLI");
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
                if(TextUtils.isEmpty(s)){
                    lAdapter.filter("");
                    listaPokemon.clearTextFilter();
                }else{
                    lAdapter.filter(s);
                }
                return false;
            }
        });
        return true;
    }
}