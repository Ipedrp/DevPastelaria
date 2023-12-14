package com.example.devpastelaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MenuFornecedor extends AppCompatActivity {

    private TextView nomeFornecedor;

    private Button cadastrarProdutos, cadastrarBebidas, verPerfilFornecedor;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String fornecedorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fornecedor);

        IniciarMenuFornecedor();

        verPerfilFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuFornecedor.this, PerfilFornecedor.class);

                startActivity(intent);

                finish();
            }
        });

        cadastrarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cp = new Intent(MenuFornecedor.this, Produtos.class);

                startActivity(cp);

                finish();

            }
        });

        cadastrarBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cb = new Intent(MenuFornecedor.this, CadastroBebidas.class);

                startActivity(cb);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null){

                    nomeFornecedor.setText(documentSnapshot.getString("nome"));

                }
            }
        });
    }

    public void IniciarMenuFornecedor(){

        nomeFornecedor = findViewById(R.id.nome_menu_fornecedor);
        cadastrarProdutos = findViewById(R.id.btncadastrarProdutosMenuFornecedor);
        cadastrarBebidas = findViewById(R.id.btncadastrarBebidasMenuFornecedor);
        verPerfilFornecedor = findViewById(R.id.btnverPerfilMenuFornecedor);

    }


}