package com.example.bottomsimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentDetalles extends Fragment {
    TextView tvName;
    TextView tvRarity;
    TextView tvCollectorNumber;
    TextView tvMana;
    TextView tvType;
    TextView tvLegality;
    TextView tvText;
    TextView tvRulings;
    TextView tvSet;
    View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public FragmentDetalles() {

    }

    public static FragmentDetalles newInstance(String param1, String param2) {
        FragmentDetalles fragment = new FragmentDetalles();
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

        String uuid = getArguments().getString("uuid");
        Card carta=(Card) getArguments().getSerializable("card");
        view= inflater.inflate(R.layout.fragment_detalles, container, false);
        tvName=(TextView)view.findViewById(R.id.tvName);
        tvName.setText("Name: "+carta.getName());
        tvRarity=(TextView)view.findViewById(R.id.tvRarity);
        tvRarity.setText("Rarity: "+carta.getRarity());
        tvCollectorNumber=(TextView)view.findViewById(R.id.tvCollectorNumber);
        tvCollectorNumber.setText("Collector Number: "+carta.getSetNumber().substring(carta.getSetNumber().indexOf("/")+1));
        tvMana=(TextView)view.findViewById(R.id.tvMana);
        tvMana.setText("Mana Cost: "+carta.getCost());
        tvSet=(TextView)view.findViewById(R.id.tvSet);
        tvSet.setText("Set: "+carta.getSetNumber().substring(0,carta.getSetNumber().indexOf("/")));
        tvType=(TextView)view.findViewById(R.id.tvType);
        tvType.setText("Type: "+carta.getType());
        tvLegality=(TextView)view.findViewById(R.id.tvLegality);
        if (!carta.getLegality().isEmpty()){
        StringBuilder legalities=new StringBuilder();
        for (String legality: (ArrayList<String>) carta.getLegality()
             ) {legalities.append(", ");
            legalities.append(Character.toUpperCase(legality.charAt(0))+legality.substring(1));
        }
            tvLegality.setText("Legalities: "+legalities.toString().substring(2));
        }else{
            tvLegality.setText("Legalities: None");
        }
        tvText=(TextView)view.findViewById(R.id.tvText);
        tvText.setText("Oracle Text: "+carta.getText());
        tvRulings=(TextView)view.findViewById(R.id.tvRulings);
        tvRulings.setText("Empty");
        return view;
    }
}