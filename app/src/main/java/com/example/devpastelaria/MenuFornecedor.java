package com.example.devpastelaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuFornecedor extends AppCompatActivity {

    private TextView nomeFornecedor;

    private Button cadastrarProdutos, visualizarProdutos, verPerfilFornecedor;

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


    }


    public void IniciarMenuFornecedor(){

        nomeFornecedor = findViewById(R.id.nome_menu_fornecedor);
        cadastrarProdutos = findViewById(R.id.btncadastrarProdutosMenuFornecedor);
        visualizarProdutos = findViewById(R.id.btnvisualizarProdutosMenuFornecedor);
        verPerfilFornecedor = findViewById(R.id.btnverPerfilMenuFornecedor);

    }


}