package com.example.bottomsimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class ListSelector extends AppCompatActivity {

    private TextView textViewSmallList;
    private TextView textViewBigList;
    private ImageButton imageButtonSmall;
    private ImageButton imageButtonBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selector);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        String source = intent.getStringExtra("source");
        ArrayList<Card> listaCards = (ArrayList<Card>) args.getSerializable("ARRAYLIST");

        textViewSmallList=(TextView) findViewById(R.id.textViewSmallList);
        textViewBigList=(TextView) findViewById(R.id.textViewBigList);
        imageButtonSmall=(ImageButton)  findViewById(R.id.imageButtonSmall);
        imageButtonBig=(ImageButton) findViewById(R.id.imageButtonBig);

        imageButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaCartas.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) listaCards);
                intent.putExtra("BUNDLE", args);
                if(source.equals("deck")){
                    intent.putExtra("adapter","smallDeck");
                }else{
                    intent.putExtra("adapter","small");
                }

                startActivity(intent);
            }
        });

        imageButtonBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaCartas.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) listaCards);
                intent.putExtra("BUNDLE", args);
                if(source.equals("deck")){
                    intent.putExtra("adapter","bigDeck");
                }else{
                    intent.putExtra("adapter","big");
                }
                startActivity(intent);
            }
        });
    }
}