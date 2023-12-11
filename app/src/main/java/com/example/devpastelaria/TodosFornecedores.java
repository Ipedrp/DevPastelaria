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

public class TodosFornecedores extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Fornecedores> fornecedoresArrayList;

    MyAdapter myAdapter;

    FirebaseFirestore db;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_fornecedores);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        fornecedoresArrayList = new ArrayList<Fornecedores>();
        myAdapter = new MyAdapter(TodosFornecedores.this, fornecedoresArrayList);

        recyclerView.setAdapter(myAdapter);

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

                                fornecedoresArrayList.add(dc.getDocument().toObject(Fornecedores.class));

                            }


                            myAdapter.notifyDataSetChanged();

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();



                        }

                    }
                });

    }
}