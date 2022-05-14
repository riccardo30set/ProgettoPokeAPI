package com.example.progettopokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
        String url = "https://pokeapi.co/api/v2/pokemon/1";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest
                (Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String pokemonName=response.getString("name");
                                    setTitle(pokemonName);
                                    heightTextView.setText(response.getString("height"));
                                    weightTextView.setText(response.getString("weight"));
                                    hpProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(0).getInt("base_stat"));
                                    attackProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(1).getInt("base_stat"));
                                    defenseProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(2).getInt("base_stat"));
                                    specialAttackProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(3).getInt("base_stat"));
                                    specialDefenseProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(4).getInt("base_stat"));
                                    speedProgressBar.setProgress(response.getJSONArray("stats").getJSONObject(5).getInt("base_stat"));
                                    String types="";
                                    JSONArray arrayTypes=response.getJSONArray("types");
                                    for (int i=0;i<arrayTypes.length();i++){
                                        String type=arrayTypes.getJSONObject(i).getJSONObject("type").getString("name");
                                        types+="|"+type+"| ";
                                    }
                                    typeText.setText(types);
                                    //['sprites']['front_default']
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
                        setTitle("no");
                    }
                });
        queue.add(jsonObjectRequest);

    }

  }