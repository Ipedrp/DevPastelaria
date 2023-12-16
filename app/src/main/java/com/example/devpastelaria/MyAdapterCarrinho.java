package com.example.devpastelaria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterCarrinho extends RecyclerView.Adapter<MyAdapterCarrinho.MyViewHolderC>{

    Context context;

    ArrayList<Produto> produtoArrayList;
    ArrayList<ProdutoDois> produtoDoisArrayList; /*Bebida*/

    public MyAdapterCarrinho(Context context, ArrayList<Produto> produtoArrayList, ArrayList<ProdutoDois> produtoDoisArrayList) {
        this.context = context;
        this.produtoArrayList = produtoArrayList;
        this.produtoDoisArrayList = produtoDoisArrayList;
    }

    @NonNull
    @Override
    public MyAdapterCarrinho.MyViewHolderC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_carrinho, parent, false);


        return new MyAdapterCarrinho.MyViewHolderC(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterCarrinho.MyViewHolderC holder, int position) {
        Produto produtos = produtoArrayList.get(position);
        ProdutoDois bebidas = produtoDoisArrayList.get(position);

        holder.nomeProdutoVerTudo.setText(produtos.nome);
        holder.nomePrecoVerTudo.setText(String.valueOf(produtos.preco));
        holder.nomeProdutoVerTudo.setText(bebidas.nome);
        holder.nomePrecoVerTudo.setText(String.valueOf(bebidas.preco));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolderC extends RecyclerView.ViewHolder{


        TextView nomeProdutoVerTudo, nomeProdutoVerTudoB;
        TextView nomePrecoVerTudo, nomePrecoVerTudoB;

        public MyViewHolderC(@NonNull View itemView) {
            super(itemView);

            nomeProdutoVerTudo = itemView.findViewById(R.id.nomeDoProdutoVerTudoCarrinho);
            nomePrecoVerTudo = itemView.findViewById(R.id.precoDoProdutoVerTudoCarrinho);
            nomeProdutoVerTudoB = itemView.findViewById(R.id.nomeDoProdutoVerTudoCarrinhoBebida);
            nomePrecoVerTudoB = itemView.findViewById(R.id.precoDoProdutoVerTudoCarrinhoBebida);
        }
    }
}
