package com.example.progettopokeapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    private final String[] tipoPokemon;
    private final String[] nomePokemon;
    private final int[] img;


    public ListAdapter(Context context, String[] tipoPokemon, String[] nomePokemon, int[] img) {
        this.context = context;
        this.tipoPokemon = tipoPokemon;
        this.nomePokemon = nomePokemon;
        this.img = img;
    }

    @Override
    public int getCount() {
        return tipoPokemon.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        final View risultato;

        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.stile_lista_pokemon, viewGroup ,false);
            viewHolder.txtNomePokemon=(TextView)view.findViewById(R.id.txtNomePokemon);
            viewHolder.txtTipoPokemon=(TextView)view.findViewById(R.id.txtTipoPokemon);
            viewHolder.imgPokemon=(ImageView)view.findViewById(R.id.imgPokemon);

            risultato=view;

            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)view.getTag();
            risultato=view;
        }
        viewHolder.txtNomePokemon.setText(nomePokemon[pos]);
        viewHolder.txtTipoPokemon.setText(tipoPokemon[pos]);
        viewHolder.imgPokemon.setImageResource(img[pos]);

        return view;
    }








    private static class ViewHolder {

        TextView  txtNomePokemon;
        TextView txtTipoPokemon;
        ImageView imgPokemon;

    }

}
