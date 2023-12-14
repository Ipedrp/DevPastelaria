package com.example.devpastelaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterBebidas extends RecyclerView.Adapter<MyAdapterBebidas.MyViewHolderB>{

    Context context;

    ArrayList<Bebida> bebidaArrayList;

    public MyAdapterBebidas(Context context, ArrayList<Bebida> bebidaArrayList) {
        this.context = context;
        this.bebidaArrayList = bebidaArrayList;
    }

    @NonNull
    @Override
    public MyAdapterBebidas.MyViewHolderB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_bebida, parent, false);


        return new MyAdapterBebidas.MyViewHolderB(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterBebidas.MyViewHolderB holder, int position) {


        Bebida bebidas = bebidaArrayList.get(position);

        holder.nomeBebida.setText(bebidas.nome);
        holder.precoBebida.setText(String.valueOf(bebidas.preco));

    }

    @Override
    public int getItemCount() {

        return bebidaArrayList.size();

    }




    public static class MyViewHolderB extends RecyclerView.ViewHolder{


        TextView nomeBebida;
        TextView precoBebida;

        public MyViewHolderB(@NonNull View itemView) {
            super(itemView);

            nomeBebida = itemView.findViewById(R.id.nomeBebida);
            precoBebida = itemView.findViewById(R.id.precoBebida);
        }
    }
}
