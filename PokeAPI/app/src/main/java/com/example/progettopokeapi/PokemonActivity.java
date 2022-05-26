



package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class PokemonActivity extends AppCompatActivity {

    String pokemonId;
    Resources res;
    int frontResID;
    int currentImageViewID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        TextView heightTextView=findViewById(R.id.heightTextView);
        TextView weightTextView=findViewById(R.id.weightTextView);
        ImageView pokemonImageView=findViewById(R.id.pokemonImageView);
        ProgressBar hpProgressBar=findViewById(R.id.hpProgressBar);
        ProgressBar attackProgressBar=findViewById(R.id.attackProgressBar);
        ProgressBar defenseProgressBar=findViewById(R.id.defenseProgressBar);
        ProgressBar specialAttackProgressBar=findViewById(R.id.specialAttackprogressBar);
        ProgressBar specialDefenseProgressBar=findViewById(R.id.specialDefenseprogressBar);
        ProgressBar speedProgressBar=findViewById(R.id.speedProgressBar);
        TextView descriptionText=findViewById(R.id.descriptionText);
        TextView typeText=findViewById(R.id.typeText);
        TextView hpText=findViewById(R.id.hpText);
        TextView attackText=findViewById(R.id.attackText);
        TextView specialAttackText=findViewById(R.id.specialAttackText);
        TextView defenseText=findViewById(R.id.defenseText);
        TextView specialDefenseText=findViewById(R.id.specialDefenseText);
        TextView speedText=findViewById(R.id.speedText);

        RequestQueue queue = Volley.newRequestQueue(PokemonActivity.this);
        Intent intent = getIntent();
        String pokemonId=intent.getStringExtra("pokemonId");

        String url = "https://pokeapi.co/api/v2/pokemon/"+pokemonId;

        res = getResources();
        String imageFrontName = "pokemon"+pokemonId;
        frontResID = res.getIdentifier(imageFrontName , "drawable", getPackageName());
        pokemonImageView.setImageResource(frontResID);
        currentImageViewID=frontResID;
        String pokemonName=intent.getStringExtra("pokemonName");

        setTitle(pokemonName);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Float height=Float.parseFloat(response.getString("height"))/10;
                                    heightTextView.setText(height.toString()+"m");
                                    Float weight=Float.parseFloat(response.getString("weight"))/10;
                                    weightTextView.setText(weight.toString()+"kg");

                                    int[] arrayStats=new int[6];
                                    int indexHp=0,indexAttack=1,indexDefense=2,indexSpecialAttack=3,indexSpecialDefense=4,indexSpeed=5;

                                    for (int i=0;i<arrayStats.length;i++){
                                        arrayStats[i]=response.getJSONArray("stats").getJSONObject(i).getInt("base_stat");
                                    }

                                    hpText.setText(hpText.getText().toString()+arrayStats[indexHp]);
                                    hpProgressBar.setProgress(arrayStats[indexHp]);
                                    attackText.setText(attackText.getText().toString()+arrayStats[indexAttack]);
                                    attackProgressBar.setProgress(arrayStats[indexAttack]);
                                    defenseText.setText(defenseText.getText().toString()+arrayStats[indexDefense]);
                                    defenseProgressBar.setProgress(arrayStats[indexDefense]);
                                    specialAttackText.setText(specialAttackText.getText().toString()+arrayStats[indexSpecialAttack]);
                                    specialAttackProgressBar.setProgress(arrayStats[indexSpecialAttack]);
                                    specialDefenseProgressBar.setProgress(arrayStats[indexSpecialDefense]);
                                    specialDefenseText.setText(specialDefenseText.getText().toString()+arrayStats[indexSpecialDefense]);
                                    speedText.setText(speedText.getText().toString()+arrayStats[indexSpeed]);
                                    speedProgressBar.setProgress(arrayStats[indexSpeed]);

                                    JSONArray arrayTypes=response.getJSONArray("types");
                                    String types="";
                                    for (int i=0;i<arrayTypes.length();i++){
                                        String type=arrayTypes.getJSONObject(i).getJSONObject("type").getString("name");
                                        types+=type+",";

                                    }
                                    types=types.substring(0,types.length()-1);
                                    typeText.setText(types);

                                    //scherzo fadda ti amo uwuwuwuwu

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setTitle("error");
                    }

                });
        queue.add(jsonObjectRequest);
        String urlDescription="https://pokeapi.co/api/v2/pokemon-species/"+pokemonId;
        JsonObjectRequest jsonObjectRequestDescription= new JsonObjectRequest
                (Request.Method.GET, urlDescription,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //['flavor_text_entries'][variabile]['version']['name'] versione gioco "sword"
                                    //['flavor_text_entries'][variabile]['language']['name'] lingua "en"



                                    boolean correctDescription=false;
                                    int i=0;
                                    String secondDescription="";
                                   while(!correctDescription && i<response.getJSONArray("flavor_text_entries").length() ){
                                       String value= String.valueOf(i);
                                       Log.d("giro", value);
                                       String gen=response.getJSONArray("flavor_text_entries").getJSONObject(i).getJSONObject("version").getString("name");
                                        String lang=response.getJSONArray("flavor_text_entries").getJSONObject(i).getJSONObject("language").getString("name");
                                        Log.d("lang",lang);
                                        Log.d("gen",gen);
                                        if (lang.equals("en")){
                                            secondDescription=response.getJSONArray("flavor_text_entries").getJSONObject(i).getString("flavor_text");
                                            if ((gen.equals("shield") || gen.equals("sword")) ){
                                                correctDescription=true;
                                                descriptionText.setText(response.getJSONArray("flavor_text_entries").getJSONObject(i).getString("flavor_text"));
                                            }
                                        }
                                        i++;
                                    }
                                   if (descriptionText.getText().equals("...")){
                                       descriptionText.setText(secondDescription);
                                   }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    descriptionText.setText("error");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        descriptionText.setText("error");
                    }

                });
        queue.add(jsonObjectRequestDescription);


    }



    public void onClickImage(View view){
        ImageView imgPokemon=(ImageView) view;
        String pokemonId=getIntent().getStringExtra("pokemonId");
        String imageBackName = "pokemonretro"+pokemonId;
        int backResID = res.getIdentifier(imageBackName , "drawable", getPackageName());
        if(backResID!=0){
            if (currentImageViewID==frontResID){
                imgPokemon.setImageResource(backResID);
                currentImageViewID=backResID;

            }else if(currentImageViewID==backResID){
                imgPokemon.setImageResource(frontResID);
                currentImageViewID=frontResID;
            }
        }


    }

}