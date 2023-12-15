package com.example.devpastelaria;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

public class TodosFornecedoresDois extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<FornecedoresDois> fornecedoresDoisArrayList;

    MyAdapterDois myAdapterDois;

    FirebaseFirestore db;

    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_fornecedores_dois);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();

        recyclerView = findViewById(R.id.aquiPorra);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        fornecedoresDoisArrayList = new ArrayList<FornecedoresDois>();
        myAdapterDois = new MyAdapterDois(TodosFornecedoresDois.this, fornecedoresDoisArrayList);

        recyclerView.setAdapter(myAdapterDois);

        EventChangeListene();

    }

    private void EventChangeListene() {

        db.collection("Fornecedores").orderBy("nome", Query.Direction.ASCENDING)
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

                                fornecedoresDoisArrayList.add(dc.getDocument().toObject(FornecedoresDois.class));

                            }


                            myAdapterDois.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();



                        }

                    }
                });

    }


}