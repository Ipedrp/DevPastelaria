package com.example.devpastelaria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Produtos extends AppCompatActivity {

    TextView nomeFornecedor;

    EditText nomeProduto, valorProduto, quantidadeProduto;

    Button btnCadastrarProduto;

    String mensagem[] = {"Preencha todos os campos!", "Produto cadastrado com sucesso!"};

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

                    /* CadastrarProduto(v);  Criar o método para cadastrar o produto! */
                }
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