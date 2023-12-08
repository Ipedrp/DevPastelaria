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
import com.google.firebase.auth.FirebaseUser;

public class LoginCliente extends AppCompatActivity {

    private TextView cadastroCliente;
    private EditText emailCliente, senhaCliente;
    private Button BotaoentrarMenuCliente;
    String mensagem[] = {"Preencha todos os campos!"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

        IniciarLoginCliente();

        cadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginCliente.this, CadastroCliente.class);

                startActivity(intent);

                finish();
            }
        });

        BotaoentrarMenuCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailCliente.getText().toString();
                String senha = senhaCliente.getText().toString();

                if( email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {

                    AutenticarCliente(v);

                }

            }
        });
    }

    public void AutenticarCliente( View v){

        String email = emailCliente.getText().toString();
        String senha = senhaCliente.getText().toString();


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {

                            MenuTelaPrincipal();


                         }
                     }, 1000);
                } else {
                    String erro;

                    try {
                        throw task.getException();

                    } catch (Exception e){

                        erro = "Erro ao logar usuário!";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }

   /* Metodo para o usuario atual sempre ficar logado, até ele clicar no botão deslogar */
    /*@Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if(usuarioAtual != null){
            MenuTelaPrincipal();
        }

    }*/

    private void MenuTelaPrincipal(){

        Intent c = new Intent(LoginCliente.this, MenuCliente.class);

        startActivity(c);


    }


    public void IniciarLoginCliente(){

        emailCliente           = findViewById(R.id.edit_email);
        senhaCliente           = findViewById(R.id.edit_senha);
        BotaoentrarMenuCliente = findViewById(R.id.btnloginMenuCLiente);
        cadastroCliente        = findViewById(R.id.criarCadastroCliente);

    }


}