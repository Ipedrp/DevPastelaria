package com.example.devpastelaria;

import static com.example.devpastelaria.ProdutosFornecedorDois.FORNECEDOR_NAME;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterProdutosDois extends RecyclerView.Adapter<MyAdapterProdutosDois.MyViewHolderPD>{

    Context context;

    ArrayList<ProdutoDois> produtosDoisArrayList;

    public MyAdapterProdutosDois(Context context, ArrayList<ProdutoDois> produtosDoisArrayList) {

        this.context = context;
        this.produtosDoisArrayList = produtosDoisArrayList;

    }

    @NonNull
    @Override
    public MyAdapterProdutosDois.MyViewHolderPD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_fornecedor_dois, parent, false);


        return new MyViewHolderPD(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterProdutosDois.MyViewHolderPD holder, int position) {

        ProdutoDois produtosDois = produtosDoisArrayList.get(position);

        holder.nomeProdutoDoisVerTudo.setText(produtosDois.nome);
        holder.nomePrecoDoisVerTudo.setText(String.valueOf(produtosDois.preco));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // You can now use 'clickedPosition' to identify which CardView was clicked
                    ProdutoDois clickedFornecedor = produtosDoisArrayList.get(clickedPosition);

                    // Perform actions based on the clicked item
                    // For example, show a toast with the product name

                    Intent intent = new Intent(view.getContext(), ProdutosFornecedorDois.class);
                    intent.putExtra(FORNECEDOR_NAME, (CharSequence) clickedFornecedor);
                    view.getContext().startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return produtosDoisArrayList.size();
    }


    public static class MyViewHolderPD extends RecyclerView.ViewHolder{


        TextView nomeProdutoDoisVerTudo;
        TextView nomePrecoDoisVerTudo;

        public MyViewHolderPD(@NonNull View itemView) {
            super(itemView);

            nomeProdutoDoisVerTudo = itemView.findViewById(R.id.nomeDoProdutoDoisVerTudo);
            nomePrecoDoisVerTudo = itemView.findViewById(R.id.precoDoProdutoDoisVerTudo);
        }
    }
}
