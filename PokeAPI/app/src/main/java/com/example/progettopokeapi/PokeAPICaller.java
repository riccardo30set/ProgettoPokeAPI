/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.progettopokeapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author USER
 */
public class PokeAPICaller {
    private String urlPokemonSpecies; 
    private String urlPokemonList; //lista di tutti i pokemon
    private String urlPokemon; //ottieni le statistiche , peso e altezza del pokemon, immagini,nome,id,tipo
    
    
    public PokeAPICaller(){
        this.urlPokemonList="https://pokeapi.co/api/v2/pokemon/?offset=0&limit=1126";
        this.urlPokemonSpecies="https://pokeapi.co/api/v2/pokemon-species/";
        this.urlPokemon="https://pokeapi.co/api/v2/pokemon/";
    }
    public String restCall(String link) throws MalformedURLException, IOException{ // method that through the link make a rest call
        URL url = new URL(link); // take the url
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
        conn.setRequestMethod("GET"); // set the type of the call, in this case "get"
        conn.setRequestProperty("Accept", "application/json"); // set the properties 
        if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode()); 
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()))); // put the result of the response into the buffered reader object
        // result string
        String output=br.readLine();
        if(output != null) {
            conn.disconnect(); 
        }
        return output;
    }
    protected String getPokemonSpecies(String pokemon) throws IOException{
        String link=this.urlPokemonSpecies+pokemon;
        return this.restCall(link);
    }
    protected String getPokemonSpecies(int pokemon) throws IOException{
        String link=this.urlPokemonSpecies+pokemon;
        return this.restCall(link);
    }
    public String getPokemonList() throws IOException{
        return this.restCall(this.urlPokemonList);
    }
    
    public String getPokemon(String pokemon) throws IOException{
        String link=this.urlPokemon+pokemon;
        return this.restCall(link);
    }
    public String getPokemon(int pokemon) throws IOException{
        String link=this.urlPokemon+pokemon;
        return this.restCall(link);
    }
    

   
}
