package com.example.devpastelaria;

import android.content.Context;
import android.content.Intent;
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // You can now use 'clickedPosition' to identify which CardView was clicked
                    Produto clickedFornecedor = produtosArrayList.get(clickedPosition);

                    // Perform actions based on the clicked item
                    // For example, show a toast with the product name

                    Intent intent = new Intent(view.getContext(), /*telaCarrinho*/);
                    intent.putExtra(/*codigo*/, /*idDoClienteQueEstaLogado*/);
                    intent.putExtra(/*codigo2*/, /*idDoProdutoQueFoiClicado*/);
                    view.getContext().startActivity(intent);

                }
            }
        });
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
