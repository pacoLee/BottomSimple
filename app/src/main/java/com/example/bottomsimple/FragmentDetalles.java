package com.example.bottomsimple;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.apache.commons.lang3.StringUtils;

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
        tvRarity.setText("Rarity: "+Character.toUpperCase(carta.getRarity().charAt(0))+carta.getRarity().substring(1));
        tvCollectorNumber=(TextView)view.findViewById(R.id.tvCollectorNumber);
        tvCollectorNumber.setText("Collector Number: "+carta.getSetNumber().substring(carta.getSetNumber().indexOf("/")+1));
        tvMana=(TextView)view.findViewById(R.id.tvMana);
        //tvMana.setText("Mana Cost: "+carta.getCost());
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



        String coste = carta.getCost();
        int empieza; // caracter desde el que se realiza el span
//        int caracteresNumero = 0;
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(coste);
        if (coste.contains("{1}")) {
            empieza = coste.indexOf("{1}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.one), empieza, empieza + 3, 0);
//            caracteresNumero = 3;
        } else if (coste.contains("{2}")) {
            empieza = coste.indexOf("{2}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.two), empieza, empieza + 3, 0);
        } else if (coste.contains("{3}")) {
            empieza = coste.indexOf("{3}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.three), empieza, empieza + 3, 0);
        } else if (coste.contains("{4}")) {
            empieza = coste.indexOf("{4}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.four), empieza, empieza + 3, 0);
        } else if (coste.contains("{5}")) {
            empieza = coste.indexOf("{5}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.five), empieza, empieza + 3, 0);
        } else if (coste.contains("{6}")) {
            empieza = coste.indexOf("{6}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.six), empieza, empieza + 3, 0);
        } else if (coste.contains("{7}")) {
            empieza = coste.indexOf("{7}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.seven), empieza, empieza + 3, 0);
        } else if (coste.contains("{8}")) {
            empieza = coste.indexOf("{8}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.eight), empieza, empieza + 3, 0);
        } else if (coste.contains("{9}")) {
            empieza = coste.indexOf("{9}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.nine), empieza, empieza + 3, 0);
        } else if (coste.contains("{10}")) {
            empieza = coste.indexOf("{10}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.ten), empieza, empieza + 4, 0);
        } else if (coste.contains("{11}")) {
            empieza = coste.indexOf("{11}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.eleven), empieza, empieza + 4, 0);
        } else if (coste.contains("{X}")) {
            empieza = coste.indexOf("{X}");
            ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.x), empieza, empieza + 3, 0);
        }

        int empiezaDesde = 0;
        int cuentaWhite = StringUtils.countMatches(ssb, "{W}");
        for (int i = 0; i < cuentaWhite; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{W}", empiezaDesde);
            if (coste.contains("{W}")) {
                ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.w), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaBlack = StringUtils.countMatches(ssb, "{B}");
        for (int i = 0; i < cuentaBlack; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{B}", empiezaDesde);
            if (coste.contains("{B}")) {
                ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.b), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaBlue = StringUtils.countMatches(ssb, "{U}");
        for (int i = 0; i < cuentaBlue; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{U}", empiezaDesde);
            if (coste.contains("{U}")) {
                ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.u), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaRed = StringUtils.countMatches(ssb, "{R}");
        for (int i = 0; i < cuentaRed; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{R}", empiezaDesde);
            if (coste.contains("{R}")) {
                ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.r), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaGreen = StringUtils.countMatches(ssb, "{G}");
        for (int i = 0; i < cuentaGreen; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{G}", empiezaDesde);
            if (coste.contains("{G}")) {
                ssb.setSpan(new ImageSpan(tvMana.getContext(), R.drawable.g), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }

        tvMana.setText(ssb);


        String text = carta.getText();
//        int caracteresNumero = 0;
        SpannableStringBuilder ssb2 = new SpannableStringBuilder();
        ssb2.append(text);
        empiezaDesde = 0;
        int cuentaW = StringUtils.countMatches(ssb2, "{W}");
        for (int i = 0; i < cuentaW; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{W}", empiezaDesde);
            if (text.contains("{W}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.w), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaU = StringUtils.countMatches(ssb2, "{U}");
        for (int i = 0; i < cuentaU; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{U}", empiezaDesde);
            if (text.contains("{U}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.u), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaB = StringUtils.countMatches(ssb2, "{B}");
        for (int i = 0; i < cuentaB; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{B}", empiezaDesde);
            if (text.contains("{B}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.b), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaR = StringUtils.countMatches(ssb2, "{R}");
        for (int i = 0; i < cuentaR; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{R}", empiezaDesde);
            if (text.contains("{R}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.r), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaG = StringUtils.countMatches(ssb2, "{G}");
        for (int i = 0; i < cuentaG; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{G}", empiezaDesde);
            if (text.contains("{G}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.g), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaC = StringUtils.countMatches(ssb2, "{C}");
        for (int i = 0; i < cuentaC; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{C}", empiezaDesde);
            if (text.contains("{C}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.c), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaTap = StringUtils.countMatches(ssb2, "{T}");
        for (int i = 0; i < cuentaTap; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{T}", empiezaDesde);
            if (text.contains("{T}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.tap), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta1 = StringUtils.countMatches(ssb2, "{1}");
        for (int i = 0; i < cuenta1; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{1}", empiezaDesde);
            if (text.contains("{1}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.one), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta2 = StringUtils.countMatches(ssb2, "{2}");
        for (int i = 0; i < cuenta2; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{2}", empiezaDesde);
            if (text.contains("{2}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.two), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta3 = StringUtils.countMatches(ssb2, "{3}");
        for (int i = 0; i < cuenta3; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{3}", empiezaDesde);
            if (text.contains("{3}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.three), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta4 = StringUtils.countMatches(ssb2, "{4}");
        for (int i = 0; i < cuenta4; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{4}", empiezaDesde);
            if (text.contains("{4}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.four), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta5 = StringUtils.countMatches(ssb2, "{5}");
        for (int i = 0; i < cuenta5; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{5}", empiezaDesde);
            if (text.contains("{5}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.five), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta6 = StringUtils.countMatches(ssb2, "{6}");
        for (int i = 0; i < cuenta6; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{6}", empiezaDesde);
            if (text.contains("{6}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.six), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta7 = StringUtils.countMatches(ssb2, "{7}");
        for (int i = 0; i < cuenta7; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{7}", empiezaDesde);
            if (text.contains("{7}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.seven), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta8 = StringUtils.countMatches(ssb2, "{8}");
        for (int i = 0; i < cuenta8; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{8}", empiezaDesde);
            if (text.contains("{8}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.eight), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta9 = StringUtils.countMatches(ssb2, "{9}");
        for (int i = 0; i < cuenta9; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{9}", empiezaDesde);
            if (text.contains("{9}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.nine), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta10 = StringUtils.countMatches(ssb2, "{10}");
        for (int i = 0; i < cuenta10; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{10}", empiezaDesde);
            if (text.contains("{10}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.ten), empieza, empieza + 4, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta11 = StringUtils.countMatches(ssb2, "{11}");
        for (int i = 0; i < cuenta11; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{11}", empiezaDesde);
            if (text.contains("{11}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.eleven), empieza, empieza + 4, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaX = StringUtils.countMatches(ssb2, "{X}");
        for (int i = 0; i < cuentaX; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{X}", empiezaDesde);
            if (text.contains("{X}")) {
                ssb2.setSpan(new ImageSpan(tvText.getContext(), R.drawable.x), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        tvText.setText(ssb2);
        return view;
    }
}