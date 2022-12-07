package com.example.bottomsimple;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.Serializable;
import java.util.ArrayList;

public class SliderDetailed extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {
    //global variables
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ArrayList<String> titles;
    String imageId;
    String uuid;
    Card carta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_detailed);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Card> listaCards = (ArrayList<Card>) args.getSerializable("ARRAYLIST");
        if (extras != null) {
           imageId = extras.getString("imageId");
           uuid = extras.getString("uuid");
        }
        Card cartaAux=new Card();
        cartaAux.setUuid(uuid);
        for (Card card:listaCards
             ) {if(card.getUuid().equals(uuid))
            carta=card;
        }


        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        titles = new ArrayList<String>();
        titles.add("Image");
        titles.add("Details");
        setViewPagerAdapter();
        new TabLayoutMediator(tabLayout, viewPager2, this).attach();
    }
    public void setViewPagerAdapter() {
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<>(); //creates an ArrayList of Fragments
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle.putString("imageId", imageId);
        bundle2.putString("uuid", uuid);
        bundle2.putSerializable("card", (Serializable) carta);
        FragmentImagen fragImg = new FragmentImagen();
        fragImg.setArguments(bundle);
        FragmentDetalles fragDet = new FragmentDetalles();
        fragDet.setArguments(bundle2);
        fragmentList.add(fragImg);
        fragmentList.add(fragDet);
        viewPager2Adapter.setData(fragmentList); //sets the data for the adapter
        viewPager2.setAdapter(viewPager2Adapter);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }
}