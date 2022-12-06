package com.example.bottomsimple;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PruebaFragment extends Fragment {

    FloatingActionButton fab;
    View view;
    AlertDialog.Builder builder;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PruebaFragment() {
    }

    public static PruebaFragment newInstance(String param1, String param2) {
        PruebaFragment fragment = new PruebaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        builder=new AlertDialog.Builder(view.getContext());
        fab=(FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                ContentValues valoresMazo = new ContentValues();
                valoresMazo.put("NOMBRE", idCarta);
                valoresMazo.put("IMAGEID", "");
                baseDeDatos.insert("MAZOS", null, valoresMazo);
                baseDeDatos.close();
            }
        });
        return inflater.inflate(R.layout.fragment_prueba, container, false);
    }
}