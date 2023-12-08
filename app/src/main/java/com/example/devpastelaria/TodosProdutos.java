package com.example.devpastelaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.devpastelaria.Adapter.ProdutosAdapter;
import com.example.devpastelaria.databinding.ActivityTodosProdutosBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TodosProdutos extends AppCompatActivity {

    private ActivityTodosProdutosBinding biding;
    private ProdutosAdapter produtosAdapter;

    private ArrayList<Produto> produtoList = new ArrayList<>();

    FirebaseFirestore db;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_produtos);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando produtos...");
        progressDialog.show();


        biding = ActivityTodosProdutosBinding.inflate(getLayoutInflater());
        setContentView(biding.getRoot());

        RecyclerView recyclerViewProdutos = biding.RecycleViewProdutos;
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProdutos.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();

        produtosAdapter = new ProdutosAdapter(produtoList, this);

        recyclerViewProdutos.setAdapter(produtosAdapter);

        EventChangeListener();

    }

    private void EventChangeListener(){


        db.collection("Produtos").orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){

                    if(progressDialog.isShowing())
                        progressDialog.isShowing();

                    Log.d("Erro ao gerar os produtos!", error.getMessage());

                    return;

                }

                for(DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){

                        produtoList.add(dc.getDocument().toObject(Produto.class));

                    }

                    produtosAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.isShowing();


                }


            }
        });
    }
}