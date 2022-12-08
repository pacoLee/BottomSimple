package com.example.bottomsimple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorSmallRecycler extends RecyclerView.Adapter<AdaptadorSmallRecycler.ViewHolder> {
        private ArrayList<Card> listaCards;
        private AdaptadorBigRecycler.ItemClickListener mItemClickListener;
        private AdaptadorBigRecycler.LongItemClickListener lItemClickListener;

        public class ViewHolder extends RecyclerView.ViewHolder {
        private Context miContexto;
        TextView nombre;
        TextView setNumber;
        TextView rarity;
        ImageView imgvCartaSmall;
        ImageView imgvRarity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.miContexto = miContexto;
             nombre = (TextView) itemView.findViewById(R.id.tvNombre);
             setNumber = (TextView) itemView.findViewById(R.id.tvSetNumber);
            rarity=(TextView) itemView.findViewById(R.id.tvRarity);
            imgvCartaSmall=(ImageView) itemView.findViewById(R.id.imgvCartaSmall);
            imgvRarity=(ImageView) itemView.findViewById(R.id.imgvRarity);
        }
    }

    public AdaptadorSmallRecycler(Context miContexto, ArrayList<Card> listaCards, AdaptadorBigRecycler.LongItemClickListener lItemClickListener, AdaptadorBigRecycler.ItemClickListener mItemClickListener) {
        this.listaCards = listaCards;
        this.mItemClickListener=mItemClickListener;
        this.lItemClickListener=lItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartasView = inflater.inflate(R.layout.item_listview_small, parent, false);
        ViewHolder viewHolder = new ViewHolder(cartasView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(view -> {
            mItemClickListener.onItemClick(listaCards.get(position));
        });
        holder.itemView.setOnLongClickListener(view -> {
            return lItemClickListener.onItemLongClick(listaCards.get(position));
        });

        String scryfallId = listaCards.get(position).getImagenId();
        char primerCaracter = scryfallId.charAt(0);
        char segundoCaracter = scryfallId.charAt(1);
        Card carta = listaCards.get(position);
        // Set item views based on your views and data model

        TextView nombre = holder.nombre;
        nombre.setText(carta.getName());
        TextView set=holder.setNumber;
        set.setText(carta.getSetNumber());
        TextView rarity=holder.rarity;
        rarity.setText(carta.getRarity());
        ImageView imgvRarity=holder.imgvRarity;
        switch (carta.getRarity()){
            case "mythic":
                imgvRarity.setImageResource(R.drawable.mythic);
                break;
            case "rare":
                imgvRarity.setImageResource(R.drawable.rare);
                break;
            case "uncommon":
                imgvRarity.setImageResource(R.drawable.uncommon);
                break;
        }
        Picasso.get().load("https://cards.scryfall.io/small/front/" + primerCaracter + "/" + segundoCaracter + "/" + scryfallId + ".jpg").into(holder.imgvCartaSmall);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listaCards.size();
    }

}