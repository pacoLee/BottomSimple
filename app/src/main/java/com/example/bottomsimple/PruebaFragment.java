package com.example.bottomsimple;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PruebaFragment extends Fragment {

    FloatingActionButton fab;
    View view;
    AlertDialog.Builder builder;
    private ListView lvDecks;
    private ArrayList<Mazo> listaMazos =new ArrayList<>();
    private AdaptadorMazo adMazo;
    SQLiteDatabase baseDeDatos;
    Integer id = 0;
    String nombre = "";
    Integer imageId= 0;
    Mazo m;

    public PruebaFragment() {
    }

    public static PruebaFragment newInstance(String param1, String param2) {
        PruebaFragment fragment = new PruebaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_prueba, container, false);
        builder=new AlertDialog.Builder(view.getContext());
        fab=(FloatingActionButton) view.findViewById(R.id.fab);
        lvDecks=(ListView) view.findViewById(R.id.lvDecks);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
        baseDeDatos = admin.getWritableDatabase();

        Cursor fila = baseDeDatos.rawQuery("select ID_MAZO , NOMBRE , IMAGEID from MAZO",null);
        if (fila.moveToFirst()) {
            do {
                id = fila.getInt(0);
                nombre = fila.getString(1);
                imageId = fila.getInt(2);
                m = new Mazo(id, imageId, nombre);
                listaMazos.add(m);
                Toast.makeText(getContext(), "Se ha encontrado un registro", Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());
        } else {
            Toast.makeText(getContext(), "No existe ningún registro", Toast.LENGTH_SHORT).show();
        }
        //baseDeDatos.close();
        Collections.sort(listaMazos,new DeckComparator());
        adMazo = new AdaptadorMazo(getContext(), listaMazos);
        lvDecks.setAdapter(adMazo);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarAlertDialogInsercion();
            }
        });
        return view;
    }


    private void cargarAlertDialogInsercion() {
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        alertDialog.setTitle("Introduzca nombre del mazo");
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText etNombre = new EditText(getContext());
        layout.addView(etNombre);

        alertDialog.setView(layout);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                Cursor fila = baseDeDatos.rawQuery("select MAX(ID_MAZO)  from MAZO",null);
                if (fila.moveToFirst()) {
                    do {

                        id = fila.getInt(0);
                        id++;
                         m = new Mazo(id, 0, etNombre.getText().toString());
                        //Toast.makeText(getContext(), "Se ha encontrado un registro", Toast.LENGTH_SHORT).show();
                    } while (fila.moveToNext());
                } else {
                    id= 1;
                     m = new Mazo(id, 0, etNombre.getText().toString());
                    Toast.makeText(getContext(), "No existe ningún registro", Toast.LENGTH_SHORT).show();
                }

                listaMazos.add(m);
                adMazo = new AdaptadorMazo(getContext(), listaMazos);
                lvDecks.setAdapter(adMazo);

                baseDeDatos.execSQL("INSERT INTO MAZO (NOMBRE) VALUES('" + etNombre.getText().toString() + "')");
                Toast.makeText(getContext(), "Mazo insertado", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setNegativeButton("Cancelar", null);
        alertDialog.show();
    }
    class DeckComparator implements Comparator<Mazo> {
        public int compare(Mazo m1, Mazo m2){
            return m1.getNombreMazo().compareTo(m2.getNombreMazo());
        }
    }

}