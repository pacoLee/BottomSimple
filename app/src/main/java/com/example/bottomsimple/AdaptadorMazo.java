package com.example.bottomsimple;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorMazo extends BaseAdapter {

    private Context context;
    private ArrayList<Mazo> mazos;

    public AdaptadorMazo(Context context, ArrayList<Mazo> mazos) {
        this.context = context;
        this.mazos = mazos;
    }

    @Override
    public int getCount() {
        return mazos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return mazos.get(i).getIdMazo();
//                cartas.get(i).;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_deck, null);

        //----------IMAGEN-----------------------------
        int imagen = mazos.get(i).getImagen();
        ImageView imgMazo = view.findViewById(R.id.imgFondo);
        //----------------------------------------------

        TextView txtNomMazo = view.findViewById(R.id.txtNomMazo);

        //----------IMAGEN------------------

        if (imagen!=0){
            //   Picasso.get().load(imagen).into(imgMazo);
        }
        //------------------------------------
        txtNomMazo.setText(mazos.get(i).getNombreMazo());
        return view;
    }
}