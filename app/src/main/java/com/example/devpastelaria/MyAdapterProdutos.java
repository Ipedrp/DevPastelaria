package com.example.devpastelaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterProdutos extends RecyclerView.Adapter<MyAdapterProdutos.MyViewHolderP>{

    Context context;

    ArrayList<Produto> produtosArrayList;

    public MyAdapterProdutos(Context context, ArrayList<Produto> produtosArrayList) {
        this.context = context;
        this.produtosArrayList = produtosArrayList;
    }


    @NonNull
    @Override
    public MyViewHolderP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_fornecedor, parent, false);


        return new MyViewHolderP(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderP holder, int position) {

        Produto produtos = produtosArrayList.get(position);

        holder.nomeProdutoVerTudo.setText(produtos.nome);
        holder.nomePrecoVerTudo.setText(String.valueOf(produtos.preco));



    }

    @Override
    public int getItemCount() {

        return produtosArrayList.size();

    }

    public static class MyViewHolderP extends RecyclerView.ViewHolder{


        TextView nomeProdutoVerTudo;
        TextView nomePrecoVerTudo;

        public MyViewHolderP(@NonNull View itemView) {
            super(itemView);

            nomeProdutoVerTudo = itemView.findViewById(R.id.nomeDoProdutoVerTudo);
            nomePrecoVerTudo = itemView.findViewById(R.id.precoDoProdutoVerTudo);
        }
    }
}
