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

public class AdaptadorBigRecycler extends RecyclerView.Adapter<AdaptadorBigRecycler.ViewHolder> {
    private ArrayList<Card> listaCards;
    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context miContexto;
        TextView tvName;
        TextView tvCost;
        TextView tvType;
        TextView tvSet;
        TextView tvText;
        TextView tvNumber;
        ImageView imgvCartaBig;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.miContexto = miContexto;
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvType=(TextView) itemView.findViewById(R.id.tvType);
            tvSet=(TextView) itemView.findViewById(R.id.tvSet);
            tvText=(TextView) itemView.findViewById(R.id.tvText);
            tvNumber=(TextView) itemView.findViewById(R.id.tvNumber);
            imgvCartaBig=(ImageView) itemView.findViewById(R.id.imgvCartaBig);
        }
    }

    public AdaptadorBigRecycler(Context miContexto, ArrayList<Card> listaCards) {
        this.listaCards = listaCards;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartasView = inflater.inflate(R.layout.item_listview_big, parent, false);
        ViewHolder viewHolder = new ViewHolder(cartasView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String scryfallId = listaCards.get(position).getImagenId();
        char primerCaracter = scryfallId.charAt(0);
        char segundoCaracter = scryfallId.charAt(1);
        Card carta = listaCards.get(position);
        // Set item views based on your views and data model

        TextView tvName = holder.tvName;
        tvName.setText(carta.getName());
        TextView tvCost=holder.tvCost;
        tvCost.setText(carta.getCost());
        TextView tvType=holder.tvType;
        tvType.setText(carta.getRarity());
        TextView tvSet=holder.tvSet;
        String[] setNumber = carta.getSetNumber().split("/");
        tvSet.setText(setNumber[0]);
        TextView tvText=holder.tvText;
        tvText.setText(carta.getText());
        TextView tvNumber=holder.tvNumber;
        tvNumber.setText(setNumber[1]);
        Picasso.get().load("https://cards.scryfall.io/normal/front/" + primerCaracter + "/" + segundoCaracter + "/" + scryfallId + ".jpg").into(holder.imgvCartaBig);
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