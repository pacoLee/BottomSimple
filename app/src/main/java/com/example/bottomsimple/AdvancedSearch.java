package com.example.bottomsimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdvancedSearch extends AppCompatActivity {

    FloatingActionButton fab;
    Spinner spinnerPower;
    Spinner spinnerToughness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_search);


        String[] arraySpinner = new String[] {">=", ">", "=", "<", "<="};
        spinnerPower = (Spinner) findViewById(R.id.spinnerPower);
        spinnerToughness = (Spinner) findViewById(R.id.spinnerToughness);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPower.setAdapter(adapter);
        spinnerToughness.setAdapter(adapter);

        fab=(FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdvancedSearch.this, ListSelector.class);
                startActivity(intent);
            }
        });
    }
}
