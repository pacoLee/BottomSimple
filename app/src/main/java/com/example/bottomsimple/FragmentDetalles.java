package com.example.bottomsimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentDetalles extends Fragment {
    TextView tvDetalles;
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
        //tvDetalles=(TextView) view.findViewById(R.id.tvDetalles);
        //tvDetalles.setText("asdf");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles, container, false);
    }
}