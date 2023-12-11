package com.example.devpastelaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;

    ArrayList<Fornecedores> fornecedoresArrayList;

    public MyAdapter(Context context, ArrayList<Fornecedores> fornecedoresArrayList) {
        this.context = context;
        this.fornecedoresArrayList = fornecedoresArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Fornecedores fornecedores = fornecedoresArrayList.get(position);

        holder.nomeFornecedorVerTudo.setText(fornecedores.nome);



    }

    @Override
    public int getItemCount() {

        return fornecedoresArrayList.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView nomeFornecedorVerTudo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeFornecedorVerTudo = itemView.findViewById(R.id.nomeDoFornecedorVerTudo);
        }
    }
}
