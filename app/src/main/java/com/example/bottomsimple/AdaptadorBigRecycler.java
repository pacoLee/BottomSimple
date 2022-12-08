package com.example.bottomsimple;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.telecom.Call;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class AdaptadorBigRecycler extends RecyclerView.Adapter<AdaptadorBigRecycler.ViewHolder> {
    private ArrayList<Card> listaCards;
    private ItemClickListener mItemClickListener;
    private LongItemClickListener lItemClickListener;

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
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            tvSet = (TextView) itemView.findViewById(R.id.tvSet);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            imgvCartaBig = (ImageView) itemView.findViewById(R.id.imgvCartaBig);
        }
    }

    public AdaptadorBigRecycler(Context miContexto, ArrayList<Card> listaCards, LongItemClickListener lItemClickListener, ItemClickListener mItemClickListener) {
        this.listaCards = listaCards;
        this.mItemClickListener = mItemClickListener;
        this.lItemClickListener = lItemClickListener;
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

        TextView tvName = holder.tvName;
        tvName.setText(carta.getName());
        if (carta.getName().length() < 15) {
            tvName.setTextSize(30);
        }
        if (carta.getName().length() >= 15 && carta.getName().length() <= 30) {
            tvName.setTextSize(20);
        }
        if (carta.getName().length() > 30) {
            tvName.setTextSize(10);
        }
        TextView tvCost = holder.tvCost;

        String coste = carta.getCost();
        int empieza; // caracter desde el que se realiza el span
//        int caracteresNumero = 0;
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(coste);
        if (coste.contains("{1}")) {
            empieza = coste.indexOf("{1}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.one), empieza, empieza + 3, 0);
//            caracteresNumero = 3;
        } else if (coste.contains("{2}")) {
            empieza = coste.indexOf("{2}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.two), empieza, empieza + 3, 0);
        } else if (coste.contains("{3}")) {
            empieza = coste.indexOf("{3}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.three), empieza, empieza + 3, 0);
        } else if (coste.contains("{4}")) {
            empieza = coste.indexOf("{4}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.four), empieza, empieza + 3, 0);
        } else if (coste.contains("{5}")) {
            empieza = coste.indexOf("{5}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.five), empieza, empieza + 3, 0);
        } else if (coste.contains("{6}")) {
            empieza = coste.indexOf("{6}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.six), empieza, empieza + 3, 0);
        } else if (coste.contains("{7}")) {
            empieza = coste.indexOf("{7}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.seven), empieza, empieza + 3, 0);
        } else if (coste.contains("{8}")) {
            empieza = coste.indexOf("{8}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.eight), empieza, empieza + 3, 0);
        } else if (coste.contains("{9}")) {
            empieza = coste.indexOf("{9}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.nine), empieza, empieza + 3, 0);
        } else if (coste.contains("{10}")) {
            empieza = coste.indexOf("{10}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.ten), empieza, empieza + 3, 0);
        } else if (coste.contains("{11}")) {
            empieza = coste.indexOf("{11}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.eleven), empieza, empieza + 3, 0);
        } else if (coste.contains("{X}")) {
            empieza = coste.indexOf("{X}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.x), empieza, empieza + 3, 0);
        }

        int empiezaDesde = 0;
        int cuentaWhite = StringUtils.countMatches(ssb, "{W}");
        for (int i = 0; i < cuentaWhite; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{W}", empiezaDesde);
            if (coste.contains("{W}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.w), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaBlack = StringUtils.countMatches(ssb, "{B}");
        for (int i = 0; i < cuentaBlack; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{B}", empiezaDesde);
            if (coste.contains("{B}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.b), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaBlue = StringUtils.countMatches(ssb, "{U}");
        for (int i = 0; i < cuentaBlue; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{U}", empiezaDesde);
            if (coste.contains("{U}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.u), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaRed = StringUtils.countMatches(ssb, "{R}");
        for (int i = 0; i < cuentaRed; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{R}", empiezaDesde);
            if (coste.contains("{R}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.r), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaGreen = StringUtils.countMatches(ssb, "{G}");
        for (int i = 0; i < cuentaGreen; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{G}", empiezaDesde);
            if (coste.contains("{G}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.g), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }

        tvCost.setText(ssb);

//        tvCost.setText(carta.getCost());

        TextView tvType = holder.tvType;
        tvType.setText(carta.getType());
        if (carta.getType().length() > 20) {
            tvType.setTextSize(15);
        } else {
            tvType.setTextSize(25);
        }
        TextView tvSet = holder.tvSet;
        String[] setNumber = carta.getSetNumber().split("/");
        tvSet.setText(setNumber[0]);
        TextView tvText = holder.tvText;
        tvText.setText(carta.getText());
        if (carta.getText().length() > 100) {
            tvText.setTextSize(15);
        } else {
            tvText.setTextSize(25);
        }
        TextView tvNumber = holder.tvNumber;
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

    public interface ItemClickListener {
        void onItemClick(Card card);
    }

    public interface LongItemClickListener {
        boolean onItemLongClick(Card card);
    }

}