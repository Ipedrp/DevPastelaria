package com.example.devpastelaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devpastelaria.databinding.ItemBebidaBinding;

import java.util.ArrayList;

public class MyAdapterBebidas extends RecyclerView.Adapter<MyAdapterBebidas.MyViewHolderB>{

    private final Context context;
    private ArrayList<Bebidas> bebidasList;

    public MyAdapterBebidas(Context context, ArrayList<Bebidas> bebidasList) {
        this.context = context;
        this.bebidasList = bebidasList;
    }


    @NonNull
    @Override
    public MyAdapterBebidas.MyViewHolderB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemBebidaBinding listBebida;
        listBebida = ItemBebidaBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolderB(listBebida);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterBebidas.MyViewHolderB holder, int position) {

        holder.binding.nomeBebida.setText(bebidasList.get(position).getNomeBebida());
        holder.binding.precoBebida.setText(String.valueOf(bebidasList.get(position).getPrecoBebida()));
    }

    @Override
    public int getItemCount() {

        return bebidasList.size();
    }


    public static class MyViewHolderB extends RecyclerView.ViewHolder{

        ItemBebidaBinding binding;

        public MyViewHolderB(ItemBebidaBinding biding) {

            super(biding.getRoot());
            this.binding = biding;
        }
    }
}
