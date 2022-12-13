package com.example.bottomsimple;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
    String idCarta;
    int cantidad = 0;

    ArrayList<Card> listaCards = new ArrayList<>();


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
        listaMazos.clear();
        builder=new AlertDialog.Builder(view.getContext());
        fab=(FloatingActionButton) view.findViewById(R.id.fab);
        lvDecks=(ListView) view.findViewById(R.id.lvDecks);
        cargarVista();


        lvDecks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> uuids = new ArrayList<>();
                ArrayList<Integer> cantidades = new ArrayList<Integer>();
                listaCards.clear();
                int idMazo=(int) adMazo.getItemId(i);
                Cursor fila = baseDeDatos.rawQuery("select  ID_CARTA , CANTIDAD from MAZO_CARTA  where ID_MAZO = " + idMazo, null);
                if (fila.moveToFirst()) {
                    do {
                        idCarta = fila.getString(0);
                        cantidad = fila.getInt(1);
                        uuids.add(idCarta);
                        cantidades.add(cantidad);
                        Toast.makeText(getContext(), "Se han encontrado " + fila.getCount() + " cartas", Toast.LENGTH_SHORT).show();
                    } while (fila.moveToNext());
                    Ingresar(uuids,cantidades,idMazo);

                } else {
                    Toast.makeText(getContext(), "No hay ninguna carta añadida", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvDecks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idMazo=(int)adMazo.getItemId(i);
                cargarAlertDialogModificarBorrar(idMazo);
                return true;
            }
        });

        //baseDeDatos.close();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarAlertDialogInsercion();
            }
        });
        return view;
    }

    private void cargarVista(){

        listaMazos.clear();
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
                //Toast.makeText(getContext(), "Se ha encontrado un registro", Toast.LENGTH_SHORT).show();
            } while (fila.moveToNext());
        } else {
            Toast.makeText(getContext(), "No existe ningún registro", Toast.LENGTH_SHORT).show();
        }
        Collections.sort(listaMazos,new DeckComparator());
        adMazo = new AdaptadorMazo(getContext(), listaMazos);
        lvDecks.setAdapter(adMazo);
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

    private void cargarAlertDialogModificarBorrar(int pos) {
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        alertDialog.setTitle("Cambiar el nombre del mazo");
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        int posicion=pos+1;
        final EditText etNombre = new EditText(getContext());
        layout.addView(etNombre);

        alertDialog.setView(layout);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                baseDeDatos.execSQL("UPDATE  MAZO SET NOMBRE ='" + etNombre.getText().toString() + "' WHERE ID_MAZO ='" + pos+ "'");
                Toast.makeText(getContext(), "Nombre de mazo actualizado", Toast.LENGTH_LONG).show();
                cargarVista();
            }
        });
        alertDialog.setNegativeButton("Cancelar", null);
        alertDialog.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                baseDeDatos.execSQL("DELETE FROM MAZO WHERE ID_MAZO ='" + pos+ "'");
                Toast.makeText(getContext(), "Mazo borrado", Toast.LENGTH_LONG).show();
                cargarVista();
            }
        });
        alertDialog.show();
    }

    class DeckComparator implements Comparator<Mazo> {
        public int compare(Mazo m1, Mazo m2){
            return m1.getNombreMazo().compareTo(m2.getNombreMazo());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Ingresar(ArrayList<String> uuids,ArrayList<Integer> cantidades,int idMazo){
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            String json = null;
                            try {
                                json = Fichero.abrir_fichero("/data/data/com.example.bottomsimple/files/standard.json");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            for (int j = 0; j < uuids.size(); j++) {
                                Filter expensiveFilter = Filter.filter(Criteria.where("uuid").eq(uuids.get(j)));

                                List<Map<String, String>> cards = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
                                List<Map<String, Double>> cardsDouble = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
                                List<Map<String, Map<String, String>>> cartas = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
                                String name = "";
                                String rarity = "";
                                String setCode = "";
                                String setNumber = "";
                                String cost = "";
                                String type = "";
                                String text = "";
                                String uuid = "";
                                String power = "";
                                String toughness = "";
                                Double manaValue;
                                Map<String, String> identifiers;
                                Map<String, String> legality;
                                Map<String, String> ruling;
                                ArrayList<String> legalities = new ArrayList<>();
                                ArrayList<String> rulings = new ArrayList<>();
                                String imagenId = "";
                                int cantidadCarta;
                                for (int k = 0; k < cards.size(); k++) {
                                    //    System.out.println(cards.get(i));

                                    name = cards.get(k).getOrDefault("name", "");
                                    setCode = cards.get(k).getOrDefault("setCode", "");
                                    setNumber = cards.get(k).getOrDefault("number", "");
                                    cost = cards.get(k).getOrDefault("manaCost", "");
                                    type = cards.get(k).getOrDefault("type", "");
                                    text = cards.get(k).getOrDefault("text", "");
                                    uuid = cards.get(k).getOrDefault("uuid", "");
                                    power = cards.get(k).getOrDefault("power", "0");
                                    toughness = cards.get(k).getOrDefault("toughness", "0");
                                    rarity = cards.get(k).getOrDefault("rarity", "");
                                    manaValue = cardsDouble.get(k).get("manaValue");
                                    identifiers = cartas.get(k).get("identifiers");
                                    legality = cartas.get(k).get("legalities");
                                    for (String key : legality.keySet()
                                    ) {
                                        if (legality.get(key).equals("Legal") && !legalities.contains(key)) {
                                            legalities.add(key);
                                        }
                                    }
                                    //ruling=cartas.get(i).get("rulings");
                                    imagenId = identifiers.getOrDefault("scryfallId", "");
                                    cantidadCarta=cantidades.get(j);

                                    //  listaCards.add(cards.get(i));
                                    Card c = new Card();
                                    c.setSetNumber(setCode + "/" + setNumber);
                                    c.setName(name);
                                    c.setCost(cost);
                                    c.setType(type);
                                    c.setText(text);
                                    c.setUuid(uuid);
                                    c.setPower(power);
                                    c.setToughness(toughness);
                                    c.setRarity(rarity);
                                    c.setManaValue(manaValue);
                                    c.setImagenId(imagenId);
                                    c.setLegality(legalities);
                                    c.setRulings(rulings);
                                    c.setCantidad(cantidadCarta);
                                    listaCards.add(c);
                                }
                            }


                            if (!listaCards.isEmpty()) {
                                Intent i = new Intent(getActivity(), ListSelector.class);
                                //i.putExtra("list",listaCards);
                                Bundle args = new Bundle();
                                args.putSerializable("ARRAYLIST", (Serializable) listaCards);
                                i.putExtra("BUNDLE", args);
                                i.putExtra("source","deck");
                                i.putExtra("mazo",idMazo);
                                startActivity(i);
                                //listaCards.clear();
                                //ad = new AdaptadorSmall(getApplicationContext(), listaCards);
                                //lvCards.setAdapter(ad);
                            } else {
                                Toast.makeText(getActivity(), "No hay cartas con ese nombre o texto",
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                } catch (final Exception ex) {
                    Log.i("---", "Exception in thread");
                }
            }
        }.start();

    }
}



/*
    String json = null;
                            try {
        json = Fichero.abrir_fichero("/data/data/com.example.bottomsimple/files/standard.json");
    } catch (IOException e) {
        e.printStackTrace();
    }
                            for (int j = 0; j < uuids.size(); j++) {
        Filter expensiveFilter = Filter.filter(Criteria.where("uuid").gt(uuids.get(j)));

        List<Map<String, String>> cards = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
        List<Map<String, Double>> cardsDouble = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
        List<Map<String, Map<String, String>>> cartas = JsonPath.parse(json).read("$..cards[?]", expensiveFilter);
        String name = "";
        String rarity = "";
        String setCode = "";
        String setNumber = "";
        String cost = "";
        String type = "";
        String text = "";
        String uuid = "";
        String power = "";
        String toughness = "";
        Double manaValue;
        Map<String, String> identifiers;
        Map<String, String> legality;
        Map<String, String> ruling;
        ArrayList<String> legalities = new ArrayList<>();
        ArrayList<String> rulings = new ArrayList<>();
        String imagenId = "";
        for (int k = 0; k < cards.size(); k++) {
            //    System.out.println(cards.get(i));

            name = cards.get(k).getOrDefault("name", "");
            setCode = cards.get(k).getOrDefault("setCode", "");
            setNumber = cards.get(k).getOrDefault("number", "");
            cost = cards.get(k).getOrDefault("manaCost", "");
            type = cards.get(k).getOrDefault("type", "");
            text = cards.get(k).getOrDefault("text", "");
            uuid = cards.get(k).getOrDefault("uuid", "");
            power = cards.get(k).getOrDefault("power", "0");
            toughness = cards.get(k).getOrDefault("toughness", "0");
            rarity = cards.get(k).getOrDefault("rarity", "");
            manaValue = cardsDouble.get(k).get("manaValue");
            identifiers = cartas.get(k).get("identifiers");
            legality = cartas.get(k).get("legalities");
            for (String key : legality.keySet()
            ) {
                if (legality.get(key).equals("Legal") && !legalities.contains(key)) {
                    legalities.add(key);
                }
            }
            //ruling=cartas.get(i).get("rulings");
            imagenId = identifiers.getOrDefault("scryfallId", "");


            //  listaCards.add(cards.get(i));
            Card c = new Card();
            c.setSetNumber(setCode + "/" + setNumber);
            c.setName(name);
            c.setCost(cost);
            c.setType(type);
            c.setText(text);
            c.setUuid(uuid);
            c.setPower(power);
            c.setToughness(toughness);
            c.setRarity(rarity);
            c.setManaValue(manaValue);
            c.setImagenId(imagenId);
            c.setLegality(legalities);
            c.setRulings(rulings);
            c.setCantidad(cantidad);
            listaCards.add(c);
        }
    }


                            if (!listaCards.isEmpty()) {
        Intent i = new Intent(getActivity(), ListSelector.class);
        //i.putExtra("list",listaCards);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) listaCards);
        i.putExtra("BUNDLE", args);
        startActivity(i);
        //ad = new AdaptadorSmall(getApplicationContext(), listaCards);
        //lvCards.setAdapter(ad);
    } else {
        Toast.makeText(getActivity(), "No hay cartas con ese nombre o texto",
                Toast.LENGTH_LONG).show();
    }

 */