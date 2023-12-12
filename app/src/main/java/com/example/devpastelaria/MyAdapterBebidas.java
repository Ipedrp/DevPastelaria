package com.example.devpastelaria;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devpastelaria.databinding.ItemBebidaBinding;

public class MyAdapterBebidas extends RecyclerView.Adapter<MyAdapterBebidas.MyViewHolderB>{
    @NonNull
    @Override
    public MyAdapterBebidas.MyViewHolderB onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterBebidas.MyViewHolderB holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyViewHolderB extends RecyclerView.ViewHolder{

        ItemBebidaBinding itemBebidaBinding;

        public MyViewHolderB(@NonNull View itemView) {
            super(itemView);
        }
    }
}
