package com.example.devpastelaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroBebidas extends AppCompatActivity {


    EditText nomebebida, valorbebida, quantidadebebida;

    Button btnCadastrarBebida;

    String mensagem[] = {"Preencha todos os campos!", "Bebida cadastrada com sucesso!"};

    int idBebida;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String fornecedorID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bebidas);

        IniciarCadastroBebida();

        btnCadastrarBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nomebebida.getText().toString();
                Double valor = Double.valueOf(valorbebida.getText().toString());
                Integer quantidade = Integer.valueOf(quantidadebebida.getText().toString());

                if( nome.isEmpty() || valor.equals("") || quantidade.equals("")){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {

                    SalvarDadosBebida(v);
                }
            }
        });
    }


    private void SalvarDadosBebida(View view) {

        String nome = nomebebida.getText().toString();
        Double valor = Double.valueOf(valorbebida.getText().toString());
        Integer quantidade = Integer.valueOf(quantidadebebida.getText().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> bebidas = new HashMap<>();
        bebidas.put("nome", nome);
        bebidas.put("preco", valor);
        bebidas.put("qtd", quantidade);


        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        String idDrinks = String.valueOf(idBebida);

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID).collection("Bebidas").document(idDrinks);

        /*DocumentReference dr = documentReference.collection("Produtos").document(idProduct);*/

        idBebida += 1;

        documentReference.set(bebidas).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.d("db", "Sucesso ao salvar do dados da bebidas!");

                        Snackbar snackbar = Snackbar.make(view, mensagem[1], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("db_erro", "Erro ao salvar os dados do bebida!" + e.toString());

                    }
                });


    }


    public void IniciarCadastroBebida(){

        nomebebida = findViewById(R.id.edit_nomeCadastroBebidaFornecedor);
        valorbebida = findViewById(R.id.edit_valorCadastroBebidaFornecedor);
        quantidadebebida = findViewById(R.id.edit_quantidadeBebidaFornecedor);
        btnCadastrarBebida = findViewById(R.id.btnCadastroBebidaForncedor);

    }
}