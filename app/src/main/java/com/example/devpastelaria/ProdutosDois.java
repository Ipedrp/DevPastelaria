package com.example.devpastelaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProdutosDois extends AppCompatActivity {

    TextView nomeFornecedor;

    EditText nomeProduto, valorProduto, quantidadeProduto;

    Button btnCadastrarProduto;

    String mensagem[] = {"Preencha todos os campos!", "Produto cadastrado com sucesso!"};

    int idProduto;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String fornecedorID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_dois);


        IniciarCadastroProduto();

        btnCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nomeProduto.getText().toString();
                Double valor = Double.valueOf(valorProduto.getText().toString());
                Integer quantidade = Integer.valueOf(quantidadeProduto.getText().toString());

                if( nome.isEmpty() || valor.equals("") || quantidade.equals("")){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {

                    SalvarDadosProdutoDois(v);
                }
            }
        });
    }


    private void SalvarDadosProdutoDois(View view) {

        String nome = nomeProduto.getText().toString();
        Double valor = Double.valueOf(valorProduto.getText().toString());
        Integer quantidade = Integer.valueOf(quantidadeProduto.getText().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> produtosDois = new HashMap<>();
        produtosDois.put("nome", nome);
        produtosDois.put("preco", valor);
        produtosDois.put("qtd", quantidade);


        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        String idProduct = String.valueOf(idProduto);

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID).collection("ProdutosDois").document(idProduct);

        /*DocumentReference dr = documentReference.collection("Produtos").document(idProduct);*/

        idProduto += 1;

        documentReference.set(produtosDois).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.d("db", "Sucesso ao salvar do dados dp produto dois!");

                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("db_erro", "Erro ao salvar os dados do produto dois!" + e.toString());

                    }
                });





    }



    public void IniciarCadastroProduto(){

        nomeFornecedor = findViewById(R.id.textCadastroProdutoDoisFornecedor);
        nomeProduto = findViewById(R.id.edit_nomeCadastroProdutoDoisFornecedor);
        valorProduto = findViewById(R.id.edit_valorCadastroProdutoDoisFornecedor);
        quantidadeProduto = findViewById(R.id.edit_quantidadeProdutoDoisFornecedor);
        btnCadastrarProduto = findViewById(R.id.btnCadastroProdutoDoisForncedor);

    }

}