package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonActivity extends AppCompatActivity {

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
        RequestQueue queue = Volley.newRequestQueue(PokemonActivity.this);
        Intent intent = getIntent();
        String pokemonId=intent.getStringExtra("pokemonId");
        String url = "https://pokeapi.co/api/v2/pokemon/"+pokemonId;

        Resources res = getResources();
        String mDrawableName = "pokemon"+pokemonId;
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        pokemonImageView.setImageResource(resID);
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

                                    hpProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(0).getInt("base_stat"));
                                    attackProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(1).getInt("base_stat"));
                                    defenseProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(2).getInt("base_stat"));
                                    specialAttackProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(3).getInt("base_stat"));
                                    specialDefenseProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(4).getInt("base_stat"));
                                    speedProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(5).getInt("base_stat"));
                                    JSONArray arrayTypes=response.getJSONArray("types");
                                    String types="";
                                    for (int i=0;i<arrayTypes.length();i++){
                                        String type=arrayTypes.getJSONObject(i).getJSONObject("type").getString("name");
                                        types+="|"+type+"| ";

                                    }
                                    typeText.setText(types);
                                    descriptionText.setText(response.getJSONArray("flavor_text_entries").getJSONObject(81).getString("flavor_text"));





                                    String imageUrl= response.getJSONObject("sprites").getString("font_default");
                                    URL url = new URL(imageUrl);
                                    //scherzo fadda ti amo uwuwuwuwu
                                    HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
                                    InputStream is = connection.getInputStream();
                                    Bitmap img = BitmapFactory.decodeStream(is);
                                    pokemonImageView.setImageBitmap(img );
                                } catch (JSONException | IOException e) {
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
                                    descriptionText.setText(response.getJSONArray("flavor_text_entries").getJSONObject(81).getString("flavor_text"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
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



  /*  public void onClickImage(View view){
        ImageView imgPokemon=(ImageView) view;
        if (imgPokemon.get)
    }
*/
  }