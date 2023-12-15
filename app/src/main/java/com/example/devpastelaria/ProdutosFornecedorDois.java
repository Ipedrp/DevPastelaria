package com.example.devpastelaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProdutosFornecedorDois extends AppCompatActivity {

    public static final String FORNECEDOR_NAME = "fornecedor_name";

    RecyclerView recyclerView;
    ArrayList<ProdutoDois> produtoDoisArrayList;

    MyAdapterProdutosDois myAdapter;

    FirebaseFirestore db;

    String fornecedorID;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_fornecedor_dois);

        String fornecedorId = getIntent().getStringExtra(FORNECEDOR_NAME);
        Log.d("PASSOU AQUI", "" + fornecedorId);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();


        recyclerView = findViewById(R.id.aquiPorraDois);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        fornecedorID = fornecedorId;

        produtoDoisArrayList = new ArrayList<ProdutoDois>();
        myAdapter = new MyAdapterProdutosDois(ProdutosFornecedorDois.this, produtoDoisArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListene();

    }

    private void EventChangeListene() {

        db.collection("Fornecedores").document(fornecedorID).collection("ProdutosDois").orderBy("nome", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.d("Firestore Erro", error.getMessage());

                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){

                                produtoDoisArrayList.add(dc.getDocument().toObject(ProdutoDois.class));

                            }


                            myAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();



                        }

                    }
                });

    }
}