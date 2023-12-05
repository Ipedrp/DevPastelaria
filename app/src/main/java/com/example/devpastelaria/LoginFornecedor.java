package com.example.devpastelaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFornecedor extends AppCompatActivity {

    private TextView cadastroFornecedor;
    private EditText emailFornecedor, senhaFornecedor;
    private Button BotaoentrarMenuFornecedor;
    String mensagem[] = {"Preencha todos os campos!"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fornecedor);

        IniciarLoginFornecedor();

        cadastroFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginFornecedor.this, CadastroFornecedor.class);

                startActivity(intent);

                finish();

            }
        });

        BotaoentrarMenuFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailFornecedor.getText().toString();
                String senha = senhaFornecedor.getText().toString();

                if( email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {

                    AutenticarFornecedor(v);

                }
            }
        });
    }


    public void AutenticarFornecedor( View v){

        String email = emailFornecedor.getText().toString();
        String senha = senhaFornecedor.getText().toString();


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            MenuTelaPrincipalFornecedor();


                        }
                    }, 1000);
                } else {
                    String erro;

                    try {
                        throw task.getException();

                    } catch (Exception e){

                        erro = "Erro ao logar fornecedor!";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }

    private void MenuTelaPrincipalFornecedor(){

        Intent c = new Intent(LoginFornecedor.this, MenuFornecedor.class);

        startActivity(c);


    }



    public void IniciarLoginFornecedor(){

        emailFornecedor           = findViewById(R.id.edit_email_fornecedor);
        senhaFornecedor           = findViewById(R.id.edit_senha_fornecedor);
        BotaoentrarMenuFornecedor = findViewById(R.id.btnloginMenuFornecedor);
        cadastroFornecedor        = findViewById(R.id.criarCadastroFornecedor);

    }


}