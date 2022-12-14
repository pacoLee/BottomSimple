package com.example.bottomsimple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shawnlin.numberpicker.NumberPicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaCartas extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvResultados;
    private Spinner spinnerOrdenar;
    String adapterType;
    AdaptadorSmallRecycler ad;
    AdaptadorBigRecycler ad2;
    AdaptadorSmallDeck ad3;
    AdaptadorBigDeck ad4;
    ArrayList<Card> listaCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cartas);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        adapterType = intent.getStringExtra("adapter");
        int idDeck = intent.getIntExtra("deck",0);


        listaCards = (ArrayList<Card>) args.getSerializable("ARRAYLIST");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvResultados = (TextView) findViewById(R.id.tvResultados);
        spinnerOrdenar = (Spinner) findViewById(R.id.spinnerOrdenar);
        spinnerOrdenar.setVisibility(View.VISIBLE);
        String[] arraySpinner = new String[]{"Nombre ↓", "Nombre ↑", "Mana ↓", "Mana ↑", "Power ↓", "Power ↑", "Toughness ↓", "Toughness ↑"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapter);


        tvResultados.setText("Resultados de la busqueda: " + listaCards.size() + " cartas");
        AdaptadorSmallRecycler ad = new AdaptadorSmallRecycler(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                return muestraDialog(card);
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
                return muestraDialog(card);
//                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
//                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
//
//                AlertDialog dialog;
//                NumberPicker nupNumero;
//                ListView lsvMazos;
//                ArrayList<String> nomMazos = new ArrayList<>();
//                ArrayList<Mazo> mazos = new ArrayList<>();
//
//                Cursor fila = baseDeDatos.rawQuery("SELECT ID_MAZO, NOMBRE FROM MAZO", null);
//                while (fila.moveToNext()) {
//                    Mazo m = new Mazo(fila.getInt(0), fila.getString(1));
//                    mazos.add(m);
//                }
//                baseDeDatos.close();
//
//                if (mazos.size() == 0) {
//                    Toast.makeText(ListaCartas.this, "No hay mazos a los que añadir esta carta", Toast.LENGTH_SHORT).show();
//                } else {
//                    for (int i = 0; i < mazos.size(); i++) {
//                        nomMazos.add(mazos.get(i).getNombreMazo());
//                    }
//
//                    AdaptadorAddCar adaptadorMazo = new AdaptadorAddCar(getApplicationContext(), mazos);
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(ListaCartas.this);
//                    LayoutInflater inflater = getLayoutInflater();
//                    View view = inflater.inflate(R.layout.dialog_add_carta, null);
//                    builder.setView(view);
//                    dialog = builder.create();
//                    dialog.show();
//
//                    nupNumero = view.findViewById(R.id.nupNumero);
//                    lsvMazos = view.findViewById(R.id.lsvMazos);
//
//                    lsvMazos.setAdapter(adaptadorMazo);
//                    Typeface typeface = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                        typeface = getResources().getFont(R.font.belerenitalic);
//                        nupNumero.setTypeface(typeface);
//                        nupNumero.setSelectedTypeface(typeface);
//                    }
//
//                    lsvMazos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
//                                SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
//                                ContentValues valoresCarta = new ContentValues();
//                                valoresCarta.put("ID_MAZO", mazos.get(pos).getIdMazo());
//                                valoresCarta.put("ID_CARTA", card.getUuid());
//                                valoresCarta.put("CANTIDAD", nupNumero.getValue());
//                                //POR QUÉ NO SALTA LA EXCEPCIÓN¿?
//                                try {
//                                    long result = baseDeDatos.insert("MAZO_CARTA", null, valoresCarta);
//
//                                    if (result == -1) {
//                                        Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
//                                    }
////                                    Toast.makeText(ListaCartas.this, "Insertado a mazo", Toast.LENGTH_SHORT).show();
//                                } catch (SQLiteConstraintException e) {
//                                    Toast.makeText(ListaCartas.this, "El mazo ya tiene esta carta", Toast.LENGTH_SHORT).show();
//                                }
//
//                            baseDeDatos.close();
//                        }
//                    });
//                }
//
//                return true;
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
        AdaptadorSmallDeck ad3 = new AdaptadorSmallDeck(getApplicationContext(), listaCards, new AdaptadorBigRecycler.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                return muestraDialog2(card,idDeck);
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
        AdaptadorBigDeck ad4 = new AdaptadorBigDeck(getApplicationContext(), listaCards, new AdaptadorBigDeck.LongItemClickListener() {
            @Override
            public boolean onItemLongClick(Card card) {
                return muestraDialog2(card,idDeck);
            }
        }, new AdaptadorBigDeck.ItemClickListener() {
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
        } else if(adapterType.equals("small")){
            recyclerView.setAdapter(ad);
        }else if(adapterType.equals("smallDeck")){
            recyclerView.setAdapter(ad3);
        }else {
            recyclerView.setAdapter(ad4);
        }
        spinnerOrdenar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "Nombre ↓":
                        Collections.sort(listaCards, new NameComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Nombre ↑":
                        Collections.sort(listaCards, new NameComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Mana ↓":
                        Collections.sort(listaCards, new ManaComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Mana ↑":
                        Collections.sort(listaCards, new ManaComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Power ↓":
                        Collections.sort(listaCards, new PowerComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Power ↑":
                        Collections.sort(listaCards, new PowerComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Toughness ↓":
                        Collections.sort(listaCards, new ToughnessComparator());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                    case "Toughness ↑":
                        Collections.sort(listaCards, new ToughnessComparatorReverse());
                        if (adapterType.equals("big")) {
                            ad2.notifyDataSetChanged();
                        } else if(adapterType.equals("small")){
                            ad.notifyDataSetChanged();
                        }else if(adapterType.equals("smallDeck")){
                            ad3.notifyDataSetChanged();
                        }else {
                            ad4.notifyDataSetChanged();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public boolean muestraDialog(Card card) {
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
            Collections.sort(mazos,new DeckComparator());
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
            Typeface typeface;
            //CREO QUE LO HACE CUANDO EL MÓVIL ESTA CORRIENDO API26 O +
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.belerenitalic);
                nupNumero.setTypeface(typeface);
                nupNumero.setSelectedTypeface(typeface);
            }

            lsvMazos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
                    SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
                    ContentValues valoresCarta = new ContentValues();
                    valoresCarta.put("ID_MAZO", mazos.get(pos).getIdMazo());
                    valoresCarta.put("ID_CARTA", card.getUuid());
                    valoresCarta.put("CANTIDAD", nupNumero.getValue());

                    Cursor fila = baseDeDatos.rawQuery("SELECT COUNT(*) FROM MAZO_CARTA WHERE ID_MAZO = " + mazos.get(pos).getIdMazo(), null);
                    fila.moveToNext();
                    if (fila.getInt(0) == 0) {
                        ContentValues valoresImagen = new ContentValues();
                        valoresImagen.put("IMAGEID", card.getImagenId());
                        baseDeDatos.update("MAZO", valoresImagen, "ID_MAZO = " + mazos.get(pos).getIdMazo(), null);
                    }
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
    public boolean muestraDialog2(Card card,int idDeck) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        Card cartaNueva=card;
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        layout.addView(numberPicker);
        alertDialog.setView(layout);
        alertDialog.setTitle("Quantity");
        alertDialog.setMessage("Choose a value: (0 to delete)");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int cantidad=numberPicker.getValue();
                int mazo=idDeck;
                String idCarta=card.getUuid();
                String name=card.getName();
                if(cantidad==0){
                    baseDeDatos.execSQL("DELETE FROM MAZO_CARTA WHERE ID_CARTA ='" + idCarta+ "'");
                    listaCards.remove(card);
                    Toast.makeText(ListaCartas.this, "Carta "+name+ " borrada", Toast.LENGTH_SHORT).show();
                    }else{
                    baseDeDatos.execSQL("UPDATE  MAZO_CARTA SET CANTIDAD ='" + cantidad + "' WHERE ID_CARTA ='" + idCarta+ "'");
                    cartaNueva.setCantidad(cantidad);
                    listaCards.set(listaCards.indexOf(card),cartaNueva);
                    Toast.makeText(ListaCartas.this, "Carta "+name+ " actualizada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.setNegativeButton("Cancel",null);
        alertDialog.show();
        return true;
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
    class DeckComparator implements Comparator<Mazo> {
        public int compare(Mazo m1, Mazo m2){
            return m1.getNombreMazo().compareTo(m2.getNombreMazo());
        }
    }
}