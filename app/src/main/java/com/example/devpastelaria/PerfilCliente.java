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

public class PerfilCliente extends AppCompatActivity {

    private TextView nomeCliente, emailCliente;

    private Button botaoDeslogar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        IniciarPefilCliente();

        botaoDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(PerfilCliente.this, MainActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null){

                    nomeCliente.setText(documentSnapshot.getString("nome"));

                    emailCliente.setText(email);
                }
            }
        });
    }

    private void IniciarPefilCliente(){

        nomeCliente = findViewById(R.id.nomePerfilCliente);
        emailCliente = findViewById(R.id.emailPerfilCliente);
        botaoDeslogar = findViewById(R.id.btnDeslogarCliente);

    }



}