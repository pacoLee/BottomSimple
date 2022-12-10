package com.example.bottomsimple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        String adapterType = intent.getStringExtra("adapter");
        ArrayList<Card> listaCards = (ArrayList<Card>) args.getSerializable("ARRAYLIST");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvResultados = (TextView) findViewById(R.id.tvResultados);
        spinnerOrdenar = (Spinner) findViewById(R.id.spinnerOrdenar);
        spinnerOrdenar.setVisibility(View.VISIBLE);
        String[] arraySpinner = new String[]{"Nombre ↓", "Nombre ↑", "Mana ↓", "Mana ↑", "Power ↓", "Power ↑", "Toughness ↓", "Toughness ↑"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapter);


        tvResultados.setText("Resultados de la busqueda: " + listaCards.size() + " cartas");
        //ADAPTADOR BIG RECYCLER EN SMALL RECYCLER
        AdaptadorSmallRecycler ad = new AdaptadorSmallRecycler(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

                AlertDialog dialog;
                NumberPicker nupNumero;
                ListView lsvMazos;
                ArrayList<String> nomMazos = new ArrayList<>();
                ArrayList<Mazo> mazos = new ArrayList<>();

                Cursor fila = baseDeDatos.rawQuery("SELECT ID_MAZO, NOMBRE FROM MAZO", null);
                while (fila.moveToNext()) {
                    Mazo m = new Mazo(fila.getInt(0), fila.getString(1));
                    mazos.add(m);
                }
                baseDeDatos.close();

                if (mazos.size() == 0) {
                    Toast.makeText(ListaCartas.this, "No hay mazos a los que añadir esta carta", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mazos.size(); i++) {
                        nomMazos.add(mazos.get(i).getNombreMazo());
                    }

                    AdaptadorAddCar adaptadorMazo = new AdaptadorAddCar(getApplicationContext(), mazos);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListaCartas.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_add_carta, null);
                    builder.setView(view);
                    dialog = builder.create();
                    dialog.show();

                    nupNumero = view.findViewById(R.id.nupNumero);
                    lsvMazos = view.findViewById(R.id.lsvMazos);

                    lsvMazos.setAdapter(adaptadorMazo);
                    nupNumero.setMaxValue(20);
                    nupNumero.setMinValue(1);

                    lsvMazos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                            ContentValues valoresCarta = new ContentValues();
                            valoresCarta.put("ID_MAZO", mazos.get(pos).getIdMazo());
                            valoresCarta.put("ID_CARTA", card.getUuid());
                            valoresCarta.put("CANTIDAD", nupNumero.getValue());
                            //POR QUÉ NO SALTA LA EXCEPCIÓN¿?
                            try {
                                long result = baseDeDatos.insert("MAZO_CARTA", null, valoresCarta);

                                if (result == -1) {
                                    Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
                                }
//                                    Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
                            } catch (SQLiteConstraintException e) {
                                Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
                            }

                            baseDeDatos.close();
                        }
                    });
                }

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
        AdaptadorBigRecycler ad2 = new AdaptadorBigRecycler(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

                AlertDialog dialog;
                NumberPicker nupNumero;
                ListView lsvMazos;
                ArrayList<String> nomMazos = new ArrayList<>();
                ArrayList<Mazo> mazos = new ArrayList<>();

                Cursor fila = baseDeDatos.rawQuery("SELECT ID_MAZO, NOMBRE FROM MAZO", null);
                while (fila.moveToNext()) {
                    Mazo m = new Mazo(fila.getInt(0), fila.getString(1));
                    mazos.add(m);
                }
                baseDeDatos.close();

                if (mazos.size() == 0) {
                    Toast.makeText(ListaCartas.this, "No hay mazos a los que añadir esta carta", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mazos.size(); i++) {
                        nomMazos.add(mazos.get(i).getNombreMazo());
                    }

                    AdaptadorAddCar adaptadorMazo = new AdaptadorAddCar(getApplicationContext(), mazos);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListaCartas.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_add_carta, null);
                    builder.setView(view);
                    dialog = builder.create();
                    dialog.show();

                    nupNumero = view.findViewById(R.id.nupNumero);
                    lsvMazos = view.findViewById(R.id.lsvMazos);

                    lsvMazos.setAdapter(adaptadorMazo);
                    nupNumero.setMaxValue(20);
                    nupNumero.setMinValue(1);

                    lsvMazos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                                ContentValues valoresCarta = new ContentValues();
                                valoresCarta.put("ID_MAZO", mazos.get(pos).getIdMazo());
                                valoresCarta.put("ID_CARTA", card.getUuid());
                                valoresCarta.put("CANTIDAD", nupNumero.getValue());
                                //POR QUÉ NO SALTA LA EXCEPCIÓN¿?
                                try {
                                    long result = baseDeDatos.insert("MAZO_CARTA", null, valoresCarta);

                                    if (result == -1) {
                                        Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
                                    }
//                                    Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
                                } catch (SQLiteConstraintException e) {
                                    Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
                                }

                            baseDeDatos.close();
                        }
                    });
                }

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
        AdaptadorBigRecycler ad3 = new AdaptadorBigRecycler(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
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

        if (adapterType.equals("big")) {
            recyclerView.setAdapter(ad2);
        } else if (adapterType.equals("small")){
            recyclerView.setAdapter(ad);
        }else{
            recyclerView.setAdapter(ad3);
        }
        spinnerOrdenar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "Nombre ↓":
                        Collections.sort(listaCards, new NameComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Nombre ↑":
                        Collections.sort(listaCards, new NameComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Mana ↓":
                        Collections.sort(listaCards, new ManaComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Mana ↑":
                        Collections.sort(listaCards, new ManaComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Power ↓":
                        Collections.sort(listaCards, new PowerComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Power ↑":
                        Collections.sort(listaCards, new PowerComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Toughness ↓":
                        Collections.sort(listaCards, new ToughnessComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                    case "Toughness ↑":
                        Collections.sort(listaCards, new ToughnessComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else {
                            ad.notifyDataSetChanged();
                        }
                        ad.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    class NameComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            return c1.getName().compareTo(c2.getName());
        }
    }

    class NameComparatorReverse implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            return c2.getName().compareTo(c1.getName());
        }
    }

    class ManaComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            return Double.compare(c1.getManaValue(), c2.getManaValue());
        }
    }

    class ManaComparatorReverse implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            return (Double.compare(c1.getManaValue(), c2.getManaValue()) * (-1));
        }
    }

    class PowerComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if (c1.getPower().equals("*") && c2.getPower().equals("*")) {
                return 0;
            } else if (c1.getPower().equals("*") && !c2.getPower().equals("*")) {
                return -1;
            } else if (c2.getPower().equals("*") && !c1.getPower().equals("*")) {
                return 1;
            }
            if (Integer.parseInt(c1.getPower()) == Integer.parseInt(c2.getPower())) {
                return 0;
            } else if (Integer.parseInt(c1.getPower()) > Integer.parseInt(c2.getPower())) {
                return 1;
            } else return -1;
        }
    }

    class PowerComparatorReverse implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if (c1.getPower().equals("*") && c2.getPower().equals("*")) {
                return 0;
            } else if (c1.getPower().equals("*") && !c2.getPower().equals("*")) {
                return 1;
            } else if (c2.getPower().equals("*") && !c1.getPower().equals("*")) {
                return -1;
            }
            if (Integer.parseInt(c1.getPower()) == Integer.parseInt(c2.getPower())) {
                return 0;
            } else if (Integer.parseInt(c1.getPower()) < Integer.parseInt(c2.getPower())) {
                return 1;
            } else return -1;
        }
    }

    class ToughnessComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if (c1.getToughness().equals("*") && c2.getToughness().equals("*")) {
                return 0;
            } else if (c1.getToughness().equals("*") && !c2.getToughness().equals("*")) {
                return -1;
            } else if (c2.getToughness().equals("*") && !c1.getToughness().equals("*")) {
                return 1;
            }
            if (Integer.parseInt(c1.getToughness()) == Integer.parseInt(c2.getToughness())) {
                return 0;
            } else if (Integer.parseInt(c1.getToughness()) > Integer.parseInt(c2.getToughness())) {
                return 1;
            } else return -1;
        }
    }

    class ToughnessComparatorReverse implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            if (c1.getToughness().equals("*") && c2.getToughness().equals("*")) {
                return 0;
            } else if (c1.getToughness().equals("*") && !c2.getToughness().equals("*")) {
                return 1;
            } else if (c2.getToughness().equals("*") && !c1.getToughness().equals("*")) {
                return -1;
            }
            if (Integer.parseInt(c1.getToughness()) == Integer.parseInt(c2.getToughness())) {
                return 0;
            } else if (Integer.parseInt(c1.getToughness()) < Integer.parseInt(c2.getToughness())) {
                return 1;
            } else return -1;
        }
    }
}