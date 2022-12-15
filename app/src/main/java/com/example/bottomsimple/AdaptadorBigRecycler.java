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
        TextView tvCantidad;
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
            tvCantidad=(TextView) itemView.findViewById(R.id.tvCantidad);
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
        holder.tvCantidad.setVisibility(View.INVISIBLE);
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
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.ten), empieza, empieza + 4, 0);
        } else if (coste.contains("{11}")) {
            empieza = coste.indexOf("{11}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.eleven), empieza, empieza + 4, 0);
        } else if (coste.contains("{13}")) {
            empieza = coste.indexOf("{13}");
            ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.thirteen), empieza, empieza + 4, 0);
        }else if (coste.contains("{X}")) {
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
        empiezaDesde = 0;
        int cuentaGWP = StringUtils.countMatches(ssb, "{G/W/P}");
        for (int i = 0; i < cuentaGWP; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{G/W/P}", empiezaDesde);
            if (coste.contains("{G/W/P}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.gwp), empieza, empieza + 7, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaGUP = StringUtils.countMatches(ssb, "{G/U/P}");
        for (int i = 0; i < cuentaGUP; i++) {
            // Empieza desde el último caracter del último span
            empieza = coste.indexOf("{G/U/P}", empiezaDesde);
            if (coste.contains("{G/U/P}")) {
                ssb.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.gup), empieza, empieza + 7, 0);
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
        TextView tvNumber=holder.tvNumber;
        tvNumber.setText(setNumber[1]);
        String text = carta.getText();
//        int caracteresNumero = 0;
        SpannableStringBuilder ssb2 = new SpannableStringBuilder();
        ssb2.append(text);
        empiezaDesde = 0;
        int cuentaW = StringUtils.countMatches(ssb2, "{W}");
        for (int i = 0; i < cuentaW; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{W}", empiezaDesde);
            if (text.contains("{W}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.w), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaU = StringUtils.countMatches(ssb2, "{U}");
        for (int i = 0; i < cuentaU; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{U}", empiezaDesde);
            if (text.contains("{U}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.u), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaB = StringUtils.countMatches(ssb2, "{B}");
        for (int i = 0; i < cuentaB; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{B}", empiezaDesde);
            if (text.contains("{B}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.b), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaR = StringUtils.countMatches(ssb2, "{R}");
        for (int i = 0; i < cuentaR; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{R}", empiezaDesde);
            if (text.contains("{R}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.r), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaG = StringUtils.countMatches(ssb2, "{G}");
        for (int i = 0; i < cuentaG; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{G}", empiezaDesde);
            if (text.contains("{G}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.g), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentagwp = StringUtils.countMatches(ssb2, "{G/W/P}");
        for (int i = 0; i < cuentaGWP; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{G/W/P}", empiezaDesde);
            if (text.contains("{G/W/P}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.gwp), empieza, empieza + 7, 0);
            }
            empiezaDesde = empieza + 7;
        }
        empiezaDesde = 0;
        int cuentagup = StringUtils.countMatches(ssb2, "{G/U/P}");
        for (int i = 0; i < cuentaGUP; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{G/U/P}", empiezaDesde);
            if (text.contains("{G/U/P}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.gup), empieza, empieza + 7, 0);
            }
            empiezaDesde = empieza + 7;
        }
        empiezaDesde = 0;
        int cuentaC = StringUtils.countMatches(ssb2, "{C}");
        for (int i = 0; i < cuentaC; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{C}", empiezaDesde);
            if (text.contains("{C}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.c), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaTap = StringUtils.countMatches(ssb2, "{T}");
        for (int i = 0; i < cuentaTap; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{T}", empiezaDesde);
            if (text.contains("{T}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.tap), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta1 = StringUtils.countMatches(ssb2, "{1}");
        for (int i = 0; i < cuenta1; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{1}", empiezaDesde);
            if (text.contains("{1}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.one), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta2 = StringUtils.countMatches(ssb2, "{2}");
        for (int i = 0; i < cuenta2; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{2}", empiezaDesde);
            if (text.contains("{2}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.two), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta3 = StringUtils.countMatches(ssb2, "{3}");
        for (int i = 0; i < cuenta3; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{3}", empiezaDesde);
            if (text.contains("{3}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.three), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta4 = StringUtils.countMatches(ssb2, "{4}");
        for (int i = 0; i < cuenta4; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{4}", empiezaDesde);
            if (text.contains("{4}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.four), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta5 = StringUtils.countMatches(ssb2, "{5}");
        for (int i = 0; i < cuenta5; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{5}", empiezaDesde);
            if (text.contains("{5}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.five), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta6 = StringUtils.countMatches(ssb2, "{6}");
        for (int i = 0; i < cuenta6; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{6}", empiezaDesde);
            if (text.contains("{6}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.six), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta7 = StringUtils.countMatches(ssb2, "{7}");
        for (int i = 0; i < cuenta7; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{7}", empiezaDesde);
            if (text.contains("{7}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.seven), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta8 = StringUtils.countMatches(ssb2, "{8}");
        for (int i = 0; i < cuenta8; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{8}", empiezaDesde);
            if (text.contains("{8}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.eight), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta9 = StringUtils.countMatches(ssb2, "{9}");
        for (int i = 0; i < cuenta9; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{9}", empiezaDesde);
            if (text.contains("{9}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.nine), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta10 = StringUtils.countMatches(ssb2, "{10}");
        for (int i = 0; i < cuenta10; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{10}", empiezaDesde);
            if (text.contains("{10}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.ten), empieza, empieza + 4, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuenta11 = StringUtils.countMatches(ssb2, "{11}");
        for (int i = 0; i < cuenta11; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{11}", empiezaDesde);
            if (text.contains("{11}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.eleven), empieza, empieza + 4, 0);
            }
            empiezaDesde = empieza + 3;
        }
        empiezaDesde = 0;
        int cuentaX = StringUtils.countMatches(ssb2, "{X}");
        for (int i = 0; i < cuentaX; i++) {
            // Empieza desde el último caracter del último span
            empieza = text.indexOf("{X}", empiezaDesde);
            if (text.contains("{X}")) {
                ssb2.setSpan(new ImageSpan(holder.tvCost.getContext(), R.drawable.x), empieza, empieza + 3, 0);
            }
            empiezaDesde = empieza + 3;
        }



        TextView tvText = holder.tvText;
        tvText.setText(ssb2);
        if (carta.getText().length() > 100) {
            tvText.setTextSize(15);
        } else {
            tvText.setTextSize(25);
        }
        TextView tvCantidad = holder.tvCantidad;
        tvCantidad.setVisibility(View.INVISIBLE);
        //tvNumber.setText(setNumber[1]);
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