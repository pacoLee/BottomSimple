package com.example.bottomsimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


public class FragmentImagen extends Fragment {

    ImageView imgvLarge;
    View view;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentImagen() {
        // Required empty public constructor
    }

    public static FragmentImagen newInstance(String param1, String param2) {
        FragmentImagen fragment = new FragmentImagen();
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
        String imageId = getArguments().getString("imageId");
        view= inflater.inflate(R.layout.fragment_home, container, false);
        imgvLarge=(ImageView) view.findViewById(R.id.imgvLarge);
        char primerCaracter = imageId.charAt(0);
        char segundoCaracter = imageId.charAt(1);
        //Picasso.get().load("https://cards.scryfall.io/large/front/" + primerCaracter + "/" + segundoCaracter + "/" + imageId + ".jpg").into(imgvLarge);
        return inflater.inflate(R.layout.fragment_imagen, container, false);
    }

}