package com.example.devpastelaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCliente extends AppCompatActivity {

    private Button verProdutos, verFornecedores, perfilCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        InciarMenuCliente();

        verFornecedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vf = new Intent(MenuCliente.this, TodosFornecedores.class);
                startActivity(vf);

            }
        });


        perfilCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pc = new Intent(MenuCliente.this, PerfilCliente.class);
                startActivity(pc);

            }
        });

        verProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vp = new Intent(MenuCliente.this, ProdutosFornecedor.class);
                startActivity(vp);

            }
        });
    }


    public void InciarMenuCliente(){

        verProdutos = findViewById(R.id.btnPasteis);
        verFornecedores = findViewById(R.id.btnVerTodosForncedores);
        perfilCliente = findViewById(R.id.btnPerfilUsuário);

    }


}