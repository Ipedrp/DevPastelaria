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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Produtos extends AppCompatActivity {

    TextView nomeFornecedor;

    EditText nomeProduto, valorProduto, quantidadeProduto;

    Button btnCadastrarProduto;

    String mensagem[] = {"Preencha todos os campos!", "Produto cadastrado com sucesso!"};

    int idProduto;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String fornecedorID;

    private List<Produto> produtos = new ArrayList<>();
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

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

                    SalvarDadosProduto(v);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    private void SalvarDadosProduto(View view) {

        String nome = nomeProduto.getText().toString();
        Double valor = Double.valueOf(valorProduto.getText().toString());
        Integer quantidade = Integer.valueOf(quantidadeProduto.getText().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> produtos = new HashMap<>();
        produtos.put("nome", nome);
        produtos.put("preco", valor);
        produtos.put("qtd", quantidade);


        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        String idProduct = String.valueOf(idProduto);

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID).collection("Produtos").document(idProduct);

        /*DocumentReference dr = documentReference.collection("Produtos").document(idProduct);*/

        idProduto += 1;

        documentReference.set(produtos).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Log.d("db", "Sucesso ao salvar do dados dp produto!");

            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("db_erro", "Erro ao salvar os dados do produto!" + e.toString());

                    }
                });





    }





    public void IniciarCadastroProduto(){

        nomeFornecedor = findViewById(R.id.textCadastroProdutoFornecedor);
        nomeProduto = findViewById(R.id.edit_nomeCadastroProdutoFornecedor);
        valorProduto = findViewById(R.id.edit_valorCadastroProdutoFornecedor);
        quantidadeProduto = findViewById(R.id.edit_quantidadeProdutoFornecedor);
        btnCadastrarProduto = findViewById(R.id.btnCadastroProdutoForncedor);

    }


}