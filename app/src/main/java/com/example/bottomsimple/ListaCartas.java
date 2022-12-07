package com.example.bottomsimple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaCartas extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvResultados;
    private Spinner spinnerOrdenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cartas);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Card> listaCards = (ArrayList<Card>) args.getSerializable("ARRAYLIST");

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        tvResultados=(TextView) findViewById(R.id.tvResultados);
        spinnerOrdenar=(Spinner) findViewById(R.id.spinnerOrdenar);
        spinnerOrdenar.setVisibility(View.VISIBLE);
        String[] arraySpinner = new String[] {"Nombre ↓", "Nombre ↑", "Mana ↓", "Mana ↑", "Id ↓", "Id ↑"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapter);

                tvResultados.setText("Resultados de la busqueda: "+listaCards.size() );
        AdaptadorSmallRecycler ad = new AdaptadorSmallRecycler(getApplicationContext(), listaCards);
        AdaptadorBigRecycler ad2 = new AdaptadorBigRecycler(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                Toast.makeText(getApplicationContext(), "Funciona",
                        Toast.LENGTH_LONG).show();
                /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                ContentValues valoresCarta = new ContentValues();
                valoresCarta.put("ID_MAZO", idMazo);
                valoresCarta.put("ID_CARTA", idCarta);
                valoresCarta.put("CANTIDAD", cantidad);
                baseDeDatos.insert("MAZO_CARTA", null, valoresCarta);
                baseDeDatos.close();*/
                return true;
            }
        }, new AdaptadorBigRecycler.ItemClickListener() {
            @Override
            public void onItemClick(Card card) {
                Intent intent = new Intent(getApplicationContext(), SliderDetailed.class);
                intent.putExtra("imageId", card.getImagenId());
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) listaCards);
                intent.putExtra("BUNDLE", args);
                intent.putExtra("uuid", card.getUuid());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(ad2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}