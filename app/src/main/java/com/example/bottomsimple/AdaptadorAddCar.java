package com.example.bottomsimple;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorAddCar extends BaseAdapter {

    private Context context;
    private ArrayList<Mazo> mazos;

    public AdaptadorAddCar(Context context, ArrayList<Mazo> mazos) {
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
        return 0;
//                cartas.get(i).;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_lista_addcard, null);

        TextView txtMazo = view.findViewById(R.id.txtMazo);

        txtMazo.setText(mazos.get(i).getNombreMazo());
        return view;
    }
}