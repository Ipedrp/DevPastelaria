package com.example.devpastelaria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
        String fornecedorId = getIntent().getStringExtra(FORNECEDOR_NAME);
        Log.d("PASSOU AQUI", "" + fornecedorId);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        fornecedorID = fornecedorId;

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