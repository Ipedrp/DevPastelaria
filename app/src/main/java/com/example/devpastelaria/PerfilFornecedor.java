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

public class PerfilFornecedor extends AppCompatActivity {

    private TextView nomeFornecedor, emailFornecedor;

    private Button botaoDeslogarFornecedor;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String fornecedorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_fornecedor);

        IniciarPefilFornecedor();

        botaoDeslogarFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(PerfilFornecedor.this, MainActivity.class);
                startActivity(i);

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null){

                    nomeFornecedor.setText(documentSnapshot.getString("nome"));

                    emailFornecedor.setText(email);
                }
            }
        });
    }


    private void IniciarPefilFornecedor(){

        nomeFornecedor = findViewById(R.id.nomePerfilFornecedor);
        emailFornecedor = findViewById(R.id.emailPerfilFornecedor);
        botaoDeslogarFornecedor = findViewById(R.id.btnDeslogarFornecedor);

    }




}