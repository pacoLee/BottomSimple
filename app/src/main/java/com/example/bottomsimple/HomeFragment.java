package com.example.bottomsimple;

import static com.jayway.jsonpath.Filter.filter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment{
    Button buttonBusqueda;
    Button buttonAvanzada;
    View view;
    private EditText etBusqueda;
    private ArrayList<Card> listaCards;
    protected ProgressDialog mProgressDialog;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        buttonBusqueda=(Button) view.findViewById(R.id.buttonBusqueda);
        buttonAvanzada=(Button) view.findViewById(R.id.buttonAvanzada);
        etBusqueda=(EditText) view.findViewById(R.id.etBusqueda);
        buttonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateTable();
            }
        });
        buttonAvanzada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdvancedSearch.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void populateTable() {
        mProgressDialog = ProgressDialog.show(this.getContext(), "Please wait","Long operation starts...", true);
        new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();

                            listaCards = new ArrayList<>();

                            String json = null;
                            try {
                                json = Fichero.abrir_fichero("/data/data/com.example.bottomsimple/files/Standard.json");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String resultado =  etBusqueda.getText().toString();
                            //   Filter cheapFictionFilter = filter(where("artist").is(resultado)).or(where("uuid").is(resultado));

                            Predicate cardswithName = new Predicate() {
                                @Override
                                public boolean apply(PredicateContext ctx) {
                                    if(ctx.item(Map.class).containsKey("name") && ctx.item(Map.class).containsValue(resultado) ) {

                                        return ctx.item(Map.class).containsKey("name");
                                    }
                                    return false;
                                }
                            };

                            Filter cheapFictionFilter = filter(cardswithName);

                            List<Map<String, String>> cards = JsonPath.parse(json).read("$..cards[?]",cheapFictionFilter);
                            List<Map<String, Map<String, String>>> cartas = JsonPath.parse(json).read("$..cards[?]",cheapFictionFilter);
                            String name = "";
                            String rarity = "";
                            String setCode="";
                            String setNumber="";
                            String cost="";
                            String type="";
                            String text="";
                            Map<String, String> identifiers;
                            String imagenId="";
                            for (int i = 0; i < cards.size(); i++) {
                                //    System.out.println(cards.get(i));

                                name =  cards.get(i).getOrDefault("name", "");
                                setCode=cards.get(i).getOrDefault("setCode","");
                                setNumber=cards.get(i).getOrDefault("number","");
                                cost=cards.get(i).getOrDefault("manaCost","");
                                type=cards.get(i).getOrDefault("type","");
                                text=cards.get(i).getOrDefault("text","");
                                rarity =  cards.get(i).getOrDefault("rarity", "");
                                identifiers=cartas.get(i).get("identifiers");
                                imagenId=identifiers.getOrDefault("scryfallId","");


                                //  listaCards.add(cards.get(i));
                                Card c = new Card();
                                c.setSetNumber(setCode+"/"+setNumber);
                                c.setName(name);
                                c.setCost(cost);
                                c.setType(type);
                                c.setText(text);
                                c.setRarity(rarity);
                                c.setImagenId(imagenId);
                                listaCards.add(c);
                            }

                            Intent i = new Intent(getActivity(), ListaCartas.class);
                            //i.putExtra("list",listaCards);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST",(Serializable)listaCards);
                            i.putExtra("BUNDLE",args);
                            startActivity(i);
                            //ad = new AdaptadorSmall(getApplicationContext(), listaCards);
                            //lvCards.setAdapter(ad);

                        }
                    });
                } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                }
            }
        }.start();

    }
}