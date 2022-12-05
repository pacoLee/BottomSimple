package com.example.bottomsimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListSelector extends AppCompatActivity {

    private TextView textViewSmallList;
    private TextView textViewBigList;
    private ImageButton imageButtonSmall;
    private ImageButton imageButtonBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selector);

        textViewSmallList=(TextView) findViewById(R.id.textViewSmallList);
        textViewBigList=(TextView) findViewById(R.id.textViewBigList);
        imageButtonSmall=(ImageButton)  findViewById(R.id.imageButtonSmall);
        imageButtonBig=(ImageButton) findViewById(R.id.imageButtonBig);

        imageButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSelector.this, ListaCartas.class);
                startActivity(intent);
            }
        });

        imageButtonBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSelector.this, ListaCartas.class);
                startActivity(intent);
            }
        });
    }
}