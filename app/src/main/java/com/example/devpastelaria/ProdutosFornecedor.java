package com.example.devpastelaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProdutosFornecedor extends AppCompatActivity {
    public static final String FORNECEDOR_NAME = "fornecedor_name";
    RecyclerView recyclerView;
    ArrayList<Produto> produtoArrayList;

    MyAdapterProdutos myAdapter;

    FirebaseFirestore db;

    String fornecedorID;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_fornecedor);
        String productName = getIntent().getStringExtra(FORNECEDOR_NAME);
        Log.d("PASSOU AQUI", "" + productName);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

// Specify the collection
        CollectionReference collection = db.collection("Fornecedores");

// Specify the field name and its valu

// Create a query to find the document with the specified field value
        Query query = collection.whereEqualTo("nome", productName);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("PASSOU AQUI 2", "eNTREI NO IF");
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("PASSOU AQUI 3", "eNTREI NO FOR");
                    // Access the document ID
                    fornecedorID = document.getId();

                    // Now, you have the document ID associated with the specified field value
                    // You can use this ID as needed
                    Log.d("Firestore", "Document ID: " + fornecedorID);
                }
            } else {
                // Handle errors
                Log.e("Firestore", "Error getting documents: ", task.getException());
            }
        });


        produtoArrayList = new ArrayList<Produto>();
        myAdapter = new MyAdapterProdutos(ProdutosFornecedor.this, produtoArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListene();
    }

    private void EventChangeListene() {

        db.collection("Fornecedores").document(fornecedorID).collection("Produtos").orderBy("nome", Query.Direction.ASCENDING)
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

                                produtoArrayList.add(dc.getDocument().toObject(Produto.class));

                            }


                            myAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();



                        }

                    }
                });

    }
}