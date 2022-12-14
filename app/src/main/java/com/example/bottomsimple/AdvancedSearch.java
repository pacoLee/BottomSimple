package com.example.bottomsimple;

import static com.jayway.jsonpath.Filter.filter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class AdvancedSearch extends AppCompatActivity {


    private ArrayList<Card> listaCards;
    //protected ProgressDialog mProgressDialog;
    EditText etName;
    EditText etText;
    EditText etManaValue;
    Spinner spAvailability;
    CheckBox colorWhite;
    CheckBox colorBlue;
    CheckBox colorBlack;
    CheckBox colorRed;
    CheckBox colorGreen;
    CheckBox colorLess;
    CheckBox colorIdentityWhite;
    CheckBox colorIdentityBlue;
    CheckBox colorIdentityBlack;
    CheckBox colorIdentityRed;
    CheckBox colorIdentityGreen;
    CheckBox colorIdentityLess;
    Switch isOnline;
    Switch isFullArt;
    Switch isExtendedArt;
    Switch isBorderless;
    Switch isReprint;
    Switch isReserved;
    EditText etKeywords;
    Spinner spLanguage;
    Spinner spLeadershipSkills;
    Spinner spLegalities;
    Spinner spinnerPower;
    EditText etPower;
    Spinner spinnerToughness;
    EditText etToughness;
    CheckBox rarityCommon;
    CheckBox rarityUncommon;
    CheckBox rarityRare;
    CheckBox rarityMythic;
    EditText setCode;
    EditText etType;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_search);
        String[] arraySpinner = new String[] {">=", ">", "=", "<", "<="};
        String[] arrayLanguage = new String[] {"","Ancient Greek", "Arabic", "Chinese Simplified", "Chinese Traditional", "English", "French", "German", "Hebrew", "Italian", "Japanese", "Korean", "Latin", "Phyrexian", "Portuguese (Brazil)", "Russian", "Sanskrit", "Spanish"};
        String[] arrayAvailability = new String[] {"","arena", "dreamcast", "mtgo", "paper", "shandalar"};
        String[] arrayLeadership = new String[] {"","brawl","commander","oathbreaker"};
        String[] arrayLegalities = new String[] {"","brawl","commander","duel","future","frontier","gladiator","historic","historicbrawl","legacy","modern","oldschool","pauper","paupercommander","penny","pioneer","premodern","standard","vintage"};

        spinnerPower = (Spinner) findViewById(R.id.spinnerPower);
        spinnerToughness = (Spinner) findViewById(R.id.spinnerToughness);
        spLanguage= (Spinner) findViewById(R.id.spLanguage);
        spAvailability=(Spinner) findViewById(R.id.spAvailability);
        spLeadershipSkills=(Spinner) findViewById(R.id.spLeadershipSkills);
        spLegalities=(Spinner) findViewById(R.id.spLegalities);
        etName=(EditText) findViewById(R.id.etName);
        etText=(EditText) findViewById(R.id.etText);
        etManaValue=(EditText) findViewById(R.id.etManaValue);
        colorWhite=(CheckBox)findViewById(R.id.colorWhite);
        colorBlue=(CheckBox)findViewById(R.id.colorBlue);
        colorBlack=(CheckBox)findViewById(R.id.colorBlack);
        colorRed=(CheckBox)findViewById(R.id.colorRed);
        colorGreen=(CheckBox)findViewById(R.id.colorGreen);
        colorLess=(CheckBox)findViewById(R.id.colorLess);
        colorIdentityWhite=(CheckBox)findViewById(R.id.colorIdentityWhite);
        colorIdentityBlue=(CheckBox)findViewById(R.id.colorIdentityBlue);
        colorIdentityBlack=(CheckBox)findViewById(R.id.colorIdentityBlack);
        colorIdentityRed=(CheckBox)findViewById(R.id.colorIdentityRed);
        colorIdentityGreen=(CheckBox)findViewById(R.id.colorIdentityGreen);
        colorIdentityLess=(CheckBox)findViewById(R.id.colorIdentityLess);
        isOnline=(Switch) findViewById(R.id.isOnline);
        isFullArt=(Switch) findViewById(R.id.isFullArt);
        isExtendedArt=(Switch) findViewById(R.id.isExtendedArt);
        isBorderless=(Switch) findViewById(R.id.isBorderless);
        isReprint=(Switch) findViewById(R.id.isReprint);
        isReserved=(Switch) findViewById(R.id.isReserved);
        etKeywords =(EditText) findViewById(R.id.etKeywords);
        etPower =(EditText) findViewById(R.id.etPower);
        etToughness =(EditText) findViewById(R.id.etToughness);
        rarityCommon=(CheckBox) findViewById(R.id.rarityCommon);
        rarityUncommon=(CheckBox) findViewById(R.id.rarityUncommon);
        rarityRare=(CheckBox) findViewById(R.id.rarityRare);
        rarityMythic=(CheckBox) findViewById(R.id.rarityMythic);
        setCode =(EditText) findViewById(R.id.setCode);
        etType =(EditText) findViewById(R.id.etType);


        colorWhite.setButtonDrawable(R.drawable.white_checkbox);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPower.setAdapter(adapter);
        spinnerToughness.setAdapter(adapter);
        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayLanguage);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLanguage.setAdapter(adapterLanguage);
        ArrayAdapter<String> adapterAvailability = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayAvailability);
        adapterAvailability.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAvailability.setAdapter(adapterAvailability);
        ArrayAdapter<String> adapterLeadership = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayLeadership);
        adapterLeadership.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLeadershipSkills.setAdapter(adapterLeadership);
        ArrayAdapter<String> adapterLegalities = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayLegalities);
        adapterLegalities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLegalities.setAdapter(adapterLegalities);

        fab=(FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(AdvancedSearch.this, ListSelector.class);
                //startActivity(intent);
                populateTable();
            }
        });
    }
    private void populateTable() {
        //mProgressDialog = ProgressDialog.show(this, "Please wait","Long operation starts...", true);
        /*new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                try {*/
                      runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void run() {
                            // mProgressDialog.dismiss();

                            listaCards = new ArrayList<>();

                            String json = null;
                            try {
                                json = Fichero.abrir_fichero("/data/data/com.example.bottomsimple/files/standard.json");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //   Filter cheapFictionFilter = filter(where("artist").is(resultado)).or(where("uuid").is(resultado));

                            Predicate cardswithFullArt = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("isFullArt") && ctx.item(Map.class).containsValue(isFullArt.isChecked())) {
                                        return ctx.item(Map.class).containsKey("isFullArt");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithName = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("name") && ctx.item(Map.class).get("name").toString().toLowerCase(Locale.ROOT).contains(etName.getText().toString().toLowerCase(Locale.ROOT))) && !etName.getText().toString().equals("")) {
                                        return ctx.item(Map.class).containsKey("name");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithText = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (((ctx.item(Map.class).containsKey("text") && ctx.item(Map.class).get("text").toString().toLowerCase(Locale.ROOT).contains(etText.getText().toString().toLowerCase(Locale.ROOT))) && !etText.getText().toString().equals(""))) {
                                        return ctx.item(Map.class).containsKey("text");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithMana = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("manaValue") && !etManaValue.getText().toString().equals("") && Double.parseDouble(ctx.item(Map.class).get("manaValue").toString()) == Double.parseDouble(etManaValue.getText().toString())) {
                                        return ctx.item(Map.class).containsKey("manaValue");
                                    }
                                    return false;
                                }
                            };

                            Predicate cardswithRarity = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (rarityCommon.isChecked()) {
                                        if (ctx.item(Map.class).containsKey("rarity")&& ctx.item(Map.class).get("rarity").toString().equals("common")) {
                                            return ctx.item(Map.class).containsKey("rarity");
                                        }
                                    }
                                    if (rarityUncommon.isChecked()) {
                                        if (ctx.item(Map.class).containsKey("rarity")&& ctx.item(Map.class).get("rarity").toString().equals("uncommon")) {
                                            return ctx.item(Map.class).containsKey("rarity");
                                        }
                                    }
                                    if (rarityRare.isChecked()) {
                                        if (ctx.item(Map.class).containsKey("rarity")&& ctx.item(Map.class).get("rarity").toString().equals("rare")) {
                                            return ctx.item(Map.class).containsKey("rarity");
                                        }
                                    }
                                    if (rarityMythic.isChecked()) {
                                        if (ctx.item(Map.class).containsKey("rarity")&& ctx.item(Map.class).get("rarity").toString().equals("mythic")) {
                                            return ctx.item(Map.class).containsKey("rarity");
                                        }
                                    }
                                    return false;
                                }
                            };

                            Predicate cardswithColor = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("colors") && (colorWhite.isChecked() || colorBlack.isChecked() || colorBlue.isChecked() || colorGreen.isChecked() || colorRed.isChecked() || colorLess.isChecked())) {
                                        ArrayList<String> listdata = new ArrayList<String>();
                                        net.minidev.json.JSONArray jArray = (net.minidev.json.JSONArray) ctx.item(Map.class).get("colors");
                                        if (jArray == null && colorLess.isChecked()) {
                                            return ctx.item(Map.class).containsKey("colors");
                                        }
                                        if (jArray != null) {
                                            for (int i = 0; i < jArray.size(); i++) {
                                                listdata.add(jArray.get(i).toString());
                                            }
                                            if(colorWhite.isChecked()&&listdata.contains("W")){
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                            if(colorBlue.isChecked()&&listdata.contains("U")){
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                            if(colorBlack.isChecked()&&listdata.contains("B")){
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                            if(colorRed.isChecked()&&listdata.contains("R")){
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                            if(colorGreen.isChecked()&&listdata.contains("G")){
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                        }
                                        /*
                                        for (int i = 0; i < listdata.size(); i++) {
                                            String w = null;
                                            String b = null;
                                            String u = null;
                                            String g = null;
                                            String r = null;
                                            int j = 0;
                                            if (colorWhite.isChecked()) {
                                                w = "W";
                                                j++;
                                            }
                                            if (colorBlack.isChecked()) {
                                                b = "B";
                                                j++;
                                            }
                                            if (colorBlue.isChecked()) {
                                                u = "U";
                                                j++;
                                            }
                                            if (colorGreen.isChecked()) {
                                                g = "G";
                                                j++;
                                            }
                                            if (colorRed.isChecked()) {
                                                r = "R";
                                                j++;
                                            }
                                            //--------------------TODAS-----------------------------------
                                            if (listdata.contains(w) && listdata.contains(b) && listdata.contains(u) && listdata.contains(g) && listdata.contains(r)) {
                                                return ctx.item(Map.class).containsKey("colors");
                                            }
                                            if (j == 3) {
                                                //--------------------TRES-----------------------------------
                                                if (listdata.contains(w) && listdata.contains(b) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(g) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(u) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(g) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(u) && listdata.contains(g)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(g) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(g) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(g) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(g) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                            }
                                            if (j == 2) {
                                                //--------------------DOS-----------------------------------
                                                if (listdata.contains(w) && listdata.contains(b)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(g)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(w) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(u)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(g)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(b) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(u) && listdata.contains(g)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                                if (listdata.contains(u) && listdata.contains(r)) {
                                                    return ctx.item(Map.class).containsKey("colors");
                                                }
                                            }
                                        }*/
                                        return false;
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithLanguage = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsValue(spLanguage.getSelectedItem().toString()) && ctx.item(Map.class).containsKey("language") && !spLanguage.getSelectedItem().toString().equals("")) {
                                        return ctx.item(Map.class).containsKey("language");
                                    }
                                    return false;
                                }
                            };
                            /*Predicate cardswithKeywords2 = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("keywords")) {
                                        ArrayList<String> listdata = new ArrayList<String>();
                                        net.minidev.json.JSONArray jArray = (net.minidev.json.JSONArray) ctx.item(Map.class).get("keywords");
                                        if (jArray != null) {
                                            for (int i = 0; i < jArray.size(); i++) {
                                                listdata.add(jArray.get(i).toString());
                                            }
                                        }
                                        for (int i = 0; i < listdata.size(); i++) {
                                            if (listdata.contains(etKeywords.getText().toString())) {
                                                return ctx.item(Map.class).containsKey("keywords");
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                            };*/
                            Predicate cardswithKeywords = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("keywords") && !etKeywords.getText().toString().equals("")) {
                                        ArrayList<String> listdata = new ArrayList<String>();
                                        net.minidev.json.JSONArray jArray = (net.minidev.json.JSONArray) ctx.item(Map.class).get("keywords");
                                        if (jArray != null) {
                                            for (int i = 0; i < jArray.size(); i++) {
                                                listdata.add(jArray.get(i).toString().toLowerCase(Locale.ROOT));
                                            }
                                        }
                                        for (int i = 0; i < listdata.size(); i++) {
                                            if (listdata.contains(etKeywords.getText().toString().toLowerCase(Locale.ROOT))) {
                                                return ctx.item(Map.class).containsKey("keywords");
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                            };

                            Predicate cardswithToughness = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    //TOUGHNESS
                                    ArrayList<String> listaToughness = new ArrayList<String>();
                                    if (ctx.item(Map.class).containsKey("toughness") && !etToughness.getText().toString().equals("")) {
                                        if (ctx.item(Map.class).getOrDefault("toughness", "").toString().equals("*") && etToughness.getText().toString().equals("*")) {
                                            return ctx.item(Map.class).containsKey("toughness");
                                        }
                                        listaToughness = new ArrayList<String>();
                                        if (!ctx.item(Map.class).getOrDefault("toughness", "").toString().equals("*")) {
                                            listaToughness.add(ctx.item(Map.class).getOrDefault("toughness", "").toString());
                                        }
                                    }
                                    try {
                                        for (int i = 0; i < listaToughness.size(); i++) {
                                            // MAYOR O IGUAL QUE
                                            if (spinnerToughness.getSelectedItem().toString().equals(">=")) {
                                                if (Integer.parseInt(listaToughness.get(i).toString()) >= Integer.parseInt(etToughness.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("toughness");
                                                }
                                            }
                                            // MENOR O IGUAL QUE
                                            if (spinnerToughness.getSelectedItem().toString().equals("<=")) {

                                                if (Integer.parseInt(listaToughness.get(i).toString()) <= Integer.parseInt(etToughness.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("toughness");
                                                }
                                            }
                                            // IGUAL QUE
                                            if (spinnerToughness.getSelectedItem().toString().equals("=")) {
                                                if (Integer.parseInt(listaToughness.get(i).toString()) == Integer.parseInt(etToughness.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("toughness");
                                                }
                                            }
                                            // MAYOR QUE
                                            if (spinnerToughness.getSelectedItem().toString().equals(">")) {
                                                if (Integer.parseInt(listaToughness.get(i).toString()) > Integer.parseInt(etToughness.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("toughness");
                                                }
                                            }
                                            // MAYOR QUE
                                            if (spinnerToughness.getSelectedItem().toString().equals("<")) {
                                                if (Integer.parseInt(listaToughness.get(i).toString()) < Integer.parseInt(etToughness.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("toughness");
                                                }
                                            }
                                        }
                                        return false;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithPower = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    //POWER
                                    ArrayList<String> listaPower = new ArrayList<String>();
                                    if (ctx.item(Map.class).containsKey("power") && !etPower.getText().toString().equals("")) {
                                        if (ctx.item(Map.class).getOrDefault("power", "").toString().equals("*") && etPower.getText().toString().equals("*")) {
                                            return ctx.item(Map.class).containsKey("toughness");
                                        }
                                        listaPower = new ArrayList<String>();
                                        if (!ctx.item(Map.class).getOrDefault("power", "").toString().equals("*")) {
                                            listaPower.add(ctx.item(Map.class).getOrDefault("power", "").toString());
                                        }
                                    }
                                    try {
                                        for (int i = 0; i < listaPower.size(); i++) {
                                            // MAYOR O IGUAL QUE
                                            if (spinnerPower.getSelectedItem().toString().equals(">=")) {
                                                if (Integer.parseInt(listaPower.get(i).toString()) >= Integer.parseInt(etPower.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("power");
                                                }
                                            }
                                            // MENOR O IGUAL QUE
                                            if (spinnerPower.getSelectedItem().toString().equals("<=")) {
                                                if (Integer.parseInt(listaPower.get(i).toString()) <= Integer.parseInt(etPower.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("power");
                                                }
                                            }
                                            // IGUAL QUE
                                            if (spinnerPower.getSelectedItem().toString().equals("=")) {
                                                if (Integer.parseInt(listaPower.get(i).toString()) == Integer.parseInt(etPower.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("power");
                                                }
                                            }
                                            // MAYOR QUE
                                            if (spinnerPower.getSelectedItem().toString().equals(">")) {
                                                if (Integer.parseInt(listaPower.get(i).toString()) > Integer.parseInt(etPower.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("power");
                                                }
                                            }
                                            // MAYOR QUE
                                            if (spinnerPower.getSelectedItem().toString().equals("<")) {
                                                if (Integer.parseInt(listaPower.get(i).toString()) < Integer.parseInt(etPower.getText().toString())) {
                                                    return ctx.item(Map.class).containsKey("power");
                                                }
                                            }
                                        }
                                        return false;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithType = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("type") && ctx.item(Map.class).get("type").toString().toLowerCase(Locale.ROOT).contains(etType.getText().toString().toLowerCase(Locale.ROOT))) && !etType.getText().toString().equals("")) {
                                        return ctx.item(Map.class).containsKey("type");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithSet = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("setCode") && ctx.item(Map.class).get("setCode").toString().toLowerCase(Locale.ROOT).contains(setCode.getText().toString().toLowerCase(Locale.ROOT))) && !setCode.getText().toString().equals("")) {
                                        return ctx.item(Map.class).containsKey("setCode");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithAvailability = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("availability") && !spAvailability.getSelectedItem().toString().equals("")) {
                                        ArrayList<String> listdata = new ArrayList<String>();
                                        net.minidev.json.JSONArray jArray = (net.minidev.json.JSONArray) ctx.item(Map.class).get("availability");
                                        if (jArray != null) {
                                            for (int i = 0; i < jArray.size(); i++) {
                                                listdata.add(jArray.get(i).toString());
                                            }
                                        }
                                        for (int i = 0; i < listdata.size(); i++) {
                                            if (listdata.contains(spAvailability.getSelectedItem().toString())) {
                                                return ctx.item(Map.class).containsKey("availability");
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithOnline = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("isOnlineOnly") && ctx.item(Map.class).getOrDefault("isOnlineOnly",false).equals(true) && isOnline.isChecked())) {
                                        return ctx.item(Map.class).containsKey("isOnlineOnly");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithExtended = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if (ctx.item(Map.class).containsKey("frameEffects") && isExtendedArt.isChecked()) {
                                        ArrayList<String> listdata = new ArrayList<String>();
                                        net.minidev.json.JSONArray jArray = (net.minidev.json.JSONArray) ctx.item(Map.class).get("frameEffects");
                                        if (jArray != null) {
                                            for (int i = 0; i < jArray.size(); i++) {
                                                listdata.add(jArray.get(i).toString());
                                            }
                                        }
                                        for (int i = 0; i < listdata.size(); i++) {
                                            if (listdata.contains("extendedart")) {
                                                return ctx.item(Map.class).containsKey("frameEffects");
                                            }
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithBorderless = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("borderColor") && ctx.item(Map.class).get("borderColor").toString().equals("borderless") && isBorderless.isChecked())) {
                                        return ctx.item(Map.class).containsKey("borderColor");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithReprint = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("isReprint") && ctx.item(Map.class).getOrDefault("isReprint",false).equals(true) && isReprint.isChecked())) {
                                        return ctx.item(Map.class).containsKey("isReprint");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithReserved = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    if ((ctx.item(Map.class).containsKey("isReserved") && ctx.item(Map.class).getOrDefault("isReserved",false).equals(true) && isReserved.isChecked())) {
                                        return ctx.item(Map.class).containsKey("isReserved");
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithLeadership = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    String leadership=spLeadershipSkills.getSelectedItem().toString();
                                    if (!spLeadershipSkills.getSelectedItem().toString().equals("")&&(ctx.item(Map.class).containsKey(leadership) && ctx.item(Map.class).getOrDefault(leadership,false).equals(true))) {
                                        return ctx.item(Map.class).containsKey(leadership);
                                    }
                                    return false;
                                }
                            };
                            Predicate cardswithLegality = new Predicate() {
                                @Override
                                public boolean apply(Predicate.PredicateContext ctx) {
                                    String legalities=spLegalities.getSelectedItem().toString();
                                    if (!spLegalities.getSelectedItem().toString().equals("")&&(ctx.item(Map.class).containsKey(legalities) && ctx.item(Map.class).getOrDefault(legalities,"Illegal").equals("Legal"))) {
                                        return ctx.item(Map.class).containsKey(legalities);
                                    }
                                    return false;
                                }
                            };





                            Filter nameFilter = filter(cardswithName);
                            Filter textFilter = filter(cardswithText);
                            Filter manaFilter = filter(cardswithMana);
                            Filter availabilityFilter= filter(cardswithAvailability);
                            Filter fullArtFilter = filter(cardswithFullArt);
                            Filter rarityFilter = filter(cardswithRarity);
                            Filter colorFilter = filter(cardswithColor);
                            Filter languageFilter = filter(cardswithLanguage);
                            Filter keywordsFilter = filter(cardswithKeywords);
                            Filter toughnessFilter = filter(cardswithToughness);
                            Filter powerFilter = filter(cardswithPower);
                            Filter typeFilter = filter(cardswithType);
                            Filter setFilter = filter(cardswithSet);
                            Filter isOnlineFilter = filter(cardswithOnline);
                            Filter isExtendedArtFilter=filter(cardswithExtended);
                            Filter isBorderlessFilter=filter(cardswithBorderless);
                            Filter isReprintFilter=filter(cardswithReprint);
                            Filter isReservedFilter=filter(cardswithReserved);
                            Filter isLeadershipFilter=filter(cardswithLeadership);
                            Filter legalitiesFilter=filter(cardswithLegality);






                            //List<Map<String, String>> cards = JsonPath.parse(json).read("$..cards[?]",nameFilter,fullArtFilter,rarityFilter,colorFilter,languageFilter,keywordsFilter,toughnessFilter,powerFilter,textFilter);
                            //List<Map<String, Double>> cardsDouble = JsonPath.parse(json).read("$..cards[?]",nameFilter,fullArtFilter,rarityFilter,colorFilter,languageFilter,keywordsFilter,toughnessFilter,powerFilter,textFilter);
                            //List<Map<String, Map<String, String>>> cartas = JsonPath.parse(json).read("$..cards[?]",nameFilter,fullArtFilter,rarityFilter,colorFilter,languageFilter,keywordsFilter,toughnessFilter,powerFilter,textFilter);
                            ArrayList<List<Map<String, String>>> cardsFilter = new ArrayList<>();
                            ArrayList<List<Map<String, Double>>> cardsDoubleFilter = new ArrayList<>();
                            ArrayList<List<Map<String, Map<String, String>>>> cartasFilter = new ArrayList<>();

                            if (!etName.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", nameFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", nameFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", nameFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etText.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", textFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", textFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", textFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etManaValue.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", manaFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", manaFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", manaFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etType.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", typeFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", typeFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", typeFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!setCode.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", setFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", setFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", setFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!spAvailability.getSelectedItem().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", availabilityFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", availabilityFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", availabilityFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isOnline.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isOnlineFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isOnlineFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isOnlineFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (rarityCommon.isChecked()||rarityUncommon.isChecked()||rarityRare.isChecked()||rarityMythic.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", rarityFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", rarityFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", rarityFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (colorWhite.isChecked()||colorBlue.isChecked()||colorBlack.isChecked()||colorRed.isChecked()||colorGreen.isChecked()||colorLess.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", colorFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", colorFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", colorFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isFullArt.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", fullArtFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", fullArtFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", fullArtFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isExtendedArt.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isExtendedArtFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isExtendedArtFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isExtendedArtFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isReprint.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isReprintFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isReprintFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isReprintFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isBorderless.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isBorderlessFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isBorderlessFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isBorderlessFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (isReserved.isChecked()) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isReservedFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isReservedFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isReservedFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etKeywords.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", keywordsFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", keywordsFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", keywordsFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!spLanguage.getSelectedItem().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", languageFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", languageFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", languageFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!spLeadershipSkills.getSelectedItem().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", isLeadershipFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", isLeadershipFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", isLeadershipFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!spLegalities.getSelectedItem().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", legalitiesFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", legalitiesFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", legalitiesFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etPower.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", powerFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", powerFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", powerFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }
                            if (!etToughness.getText().toString().equals("")) {
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]", toughnessFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]", toughnessFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]", toughnessFilter);
                                cardsFilter.add(cardsAux);
                                cardsDoubleFilter.add(cardsDoubleAux);
                                cartasFilter.add(cartasAux);
                            }




                            for (int i = 1; i < cardsFilter.size(); i++) {
                                cardsFilter.get(0).retainAll(cardsFilter.get(i));
                                cardsDoubleFilter.get(0).retainAll(cardsDoubleFilter.get(i));
                                cartasFilter.get(0).retainAll(cartasFilter.get(i));
                            }


                            List<Map<String, String>> cards = new ArrayList<>();
                            List<Map<String, Double>> cardsDouble = new ArrayList<>();
                            List<Map<String, Map<String, String>>> cartas = new ArrayList<>();

                            cards = cardsFilter.get(0);
                            cardsDouble = cardsDoubleFilter.get(0);
                            cartas = cartasFilter.get(0);



                            if (cards.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "No hay cartas con esas caracteristicas",
                                        Toast.LENGTH_LONG).show();
                            } else {
                            /*
                            if(!etName.getText().toString().equals("")){
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]",nameFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]",nameFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]",nameFilter);
                                cards.addAll(cardsAux);
                                cardsDouble.addAll(cardsDoubleAux);
                                cartas.addAll(cartasAux);
                            }
                            if(!etText.getText().toString().equals("")){
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]",textFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]",textFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]",textFilter);
                                cards.addAll(cardsAux);
                                cardsDouble.addAll(cardsDoubleAux);
                                cartas.addAll(cartasAux);
                            }
                            if(!etManaValue.getText().toString().equals("")){
                                List<Map<String, String>> cardsAux = JsonPath.parse(json).read("$..cards[?]",manaFilter);
                                List<Map<String, Double>> cardsDoubleAux = JsonPath.parse(json).read("$..cards[?]",manaFilter);
                                List<Map<String, Map<String, String>>> cartasAux = JsonPath.parse(json).read("$..cards[?]",manaFilter);
                                cards.addAll(cardsAux);
                                cardsDouble.addAll(cardsDoubleAux);
                                cartas.addAll(cartasAux);
                            }
                            //List<Map<String, String>> cards = JsonPath.parse(json).read("$..cards[?]",textFilter,nameFilter);
                            //List<Map<String, Double>> cardsDouble = JsonPath.parse(json).read("$..cards[?]",textFilter,nameFilter);
                            //List<Map<String, Map<String, String>>> cartas = JsonPath.parse(json).read("$..cards[?]",textFilter,nameFilter);
                            //cards=removeDuplicates(cards);
                            //cardsDouble=removeDuplicates(cardsDouble);
                            //
                            // cartas=removeDuplicates(cartas);
                            */

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
                                for (int i = 0; i < cards.size(); i++) {
                                    //    System.out.println(cards.get(i));
                                    name = cards.get(i).getOrDefault("name", "");
                                    setCode = cards.get(i).getOrDefault("setCode", "");
                                    setNumber = cards.get(i).getOrDefault("number", "");
                                    cost = cards.get(i).getOrDefault("manaCost", "");
                                    type = cards.get(i).getOrDefault("type", "");
                                    text = cards.get(i).getOrDefault("text", "");
                                    uuid = cards.get(i).getOrDefault("uuid", "");
                                    power = cards.get(i).getOrDefault("power", "0");
                                    toughness = cards.get(i).getOrDefault("toughness", "0");
                                    rarity = cards.get(i).getOrDefault("rarity", "");
                                    manaValue = cardsDouble.get(i).getOrDefault("manaValue", 0d);
                                    identifiers = cartas.get(i).get("identifiers");
                                    legality = cartas.get(i).get("legalities");
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
                                    listaCards.add(c);
                                }
                                if (!listaCards.isEmpty()) {
                                    cards.clear();
                                    cardsDouble.clear();
                                    cartas.clear();
                                    Intent i = new Intent(getApplicationContext(), ListSelector.class);
                                    //i.putExtra("list",listaCards);
                                    Bundle args = new Bundle();
                                    args.putSerializable("ARRAYLIST", (Serializable) listaCards);
                                    i.putExtra("BUNDLE", args);
                                    i.putExtra("source","search");
                                    startActivity(i);
                                    //ad = new AdaptadorSmall(getApplicationContext(), listaCards);
                                    //lvCards.setAdapter(ad);
                                } else {
                                    Toast.makeText(getApplicationContext(), "No hay cartas con ese nombre o texto",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
               /* } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                }
            }
        }.start();*/
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
        {
            // Create a new ArrayList
            ArrayList<T> newList = new ArrayList<T>();
            // Traverse through the first list
            for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
            newList.add(element);
            }
            }
            // return the new list
        return newList;
        }
    public static <T> ArrayList<T> seekEquals(ArrayList<T> list)
    {
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        // Traverse through the first list
        for (T element : list) {
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }
    public Card consultaUuid(String uuidCard){
        String json = null;
        try {
            json = Fichero.abrir_fichero("/data/data/com.example.bottomsimple/files/standard.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> carta = JsonPath.parse(json)
                .read("$..cards[?('"+uuidCard+"' in @['uuid'])]");
        Map<String, Double> carta2 = JsonPath.parse(json)
                .read("$..cards[?('"+uuidCard+"' in @['uuid'])]");
        Map<String, Map<String, String>> carta3 = JsonPath.parse(json)
                .read("$..cards[?('"+uuidCard+"' in @['uuid'])]");

        Map<String, String> ruling;
        ArrayList<String> legalities = new ArrayList<>();
        ArrayList<String> rulings = new ArrayList<>();
        Card c = new Card();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

        String name = carta.getOrDefault("name", "");
        String setCode = carta.getOrDefault("setCode", "");
        String setNumber = carta.getOrDefault("number", "");
        String cost = carta.getOrDefault("manaCost", "");
        String type = carta.getOrDefault("type", "");
        String text = carta.getOrDefault("text", "");
        String uuid = carta.getOrDefault("uuid", "");
        String power = carta.getOrDefault("power", "0");
        String toughness = carta.getOrDefault("toughness", "0");
        String rarity = carta.getOrDefault("rarity", "");
        Double manaValue = carta2.getOrDefault("manaValue", 0d);
        Map<String, String> identifiers = carta3.get("identifiers");
        Map<String, String> legality = carta3.get("legalities");
        for (String key : legality.keySet()
        ) {
            if (legality.get(key).equals("Legal") && !legalities.contains(key)) {
                legalities.add(key);
            }
        }
        //ruling=cartas.get(i).get("rulings");
        String imagenId = identifiers.getOrDefault("scryfallId", "");

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
        }
        return c;
    }
}
