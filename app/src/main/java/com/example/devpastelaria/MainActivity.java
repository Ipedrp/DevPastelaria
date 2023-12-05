package com.example.devpastelaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void telaLoginCliente(View view) {

        Intent tlc = new Intent(getApplicationContext(), LoginCliente.class);
        startActivity(tlc);

    }

    public void telaLoginFornecedor(View view){

        Intent tlf = new Intent(getApplicationContext(), LoginFornecedor.class);
        startActivity(tlf);
    }


}