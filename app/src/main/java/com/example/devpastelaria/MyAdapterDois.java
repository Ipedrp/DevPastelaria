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

public class MyAdapterDois extends RecyclerView.Adapter<MyAdapterDois.MyViewHolderD>{

    Context context;

    ArrayList<FornecedoresDois> fornecedoresDoisArrayList;

    public MyAdapterDois(Context context, ArrayList<FornecedoresDois> fornecedoresDoisArrayList) {
        this.context = context;
        this.fornecedoresDoisArrayList = fornecedoresDoisArrayList;
    }

    @NonNull
    @Override
    public MyAdapterDois.MyViewHolderD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_dois, parent, false);

        return new MyAdapterDois.MyViewHolderD(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterDois.MyViewHolderD holder, int position) {

        FornecedoresDois fornecedores = fornecedoresDoisArrayList.get(position);

        holder.nomeFornecedorVerTudo.setText(fornecedores.nome);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // You can now use 'clickedPosition' to identify which CardView was clicked
                    FornecedoresDois clickedFornecedor = fornecedoresDoisArrayList.get(clickedPosition);

                    // Perform actions based on the clicked item
                    // For example, show a toast with the product name

                    Intent intent = new Intent(view.getContext(), ProdutosFornecedorDois.class);
                    intent.putExtra(ProdutosFornecedor.FORNECEDOR_NAME, clickedFornecedor.getId());
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return fornecedoresDoisArrayList.size();
    }

    public static class MyViewHolderD extends RecyclerView.ViewHolder{


        TextView nomeFornecedorVerTudo;

        public MyViewHolderD(@NonNull View itemView) {
            super(itemView);

            nomeFornecedorVerTudo = itemView.findViewById(R.id.nomeDoFornecedorVerTudoDois);
        }
    }
}
