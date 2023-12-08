package com.example.devpastelaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devpastelaria.Produto;
import com.example.devpastelaria.databinding.ProdutosItemBinding;

import java.util.ArrayList;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder>{

    private final ArrayList<Produto> produtoList;
    private final Context context;

    public ProdutosAdapter(ArrayList<Produto> produtoList, Context context) {
        this.produtoList = produtoList;
        this.context = context;
    }

    /* Método responsvel por criar as vizualizações da lista */
    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProdutosItemBinding listItem;
        listItem = ProdutosItemBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ProdutoViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {

        holder.biding.TextNomeProduto.setText(produtoList.get(position).getNome());
        holder.biding.TextValorProduto.setText((int) produtoList.get(position).getPreco());


    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder{

        ProdutosItemBinding biding;

        public ProdutoViewHolder(ProdutosItemBinding biding) {
            super(biding.getRoot());
            this.biding = biding;
        }
    }
}
