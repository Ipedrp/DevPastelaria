package com.example.devpastelaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CadastroCliente extends AppCompatActivity {

    private EditText nomeCliente, emailCliente, senhaCliente;
    private Button BotaoCadastroCliente;

    String mensagem[] = {"Preencha todos os campos!", "Cliente cadastrado com sucesso!"};
    String usuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        IniciarCadastroCliente();

        BotaoCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome  = nomeCliente.getText().toString();
                String email = emailCliente.getText().toString();
                String senha = senhaCliente.getText().toString();

                if( nome.isEmpty() || email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    CadastrarUsuario(v);
                }

            }
        });
    }

    public void CadastrarUsuario(View v){

        String email = emailCliente.getText().toString();
        String senha = senhaCliente.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    SalvarDadosUsuario();

                    Snackbar snackbar = Snackbar.make(v, mensagem[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {

                    String erro;

                    try {

                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){

                        erro = "Digite uma senha com no mínimo 6 caracteres!";

                    }catch (FirebaseAuthInvalidCredentialsException e){

                        erro = "E-mail já existe!";

                    }catch (FirebaseAuthUserCollisionException e){

                        erro = "Essa conta já existe!";

                    } catch (Exception e){

                        erro = "Erro ao cadastrar usuário!";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });

    }

    private  void SalvarDadosUsuario(){

        String nome = nomeCliente.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();

        usuarios.put("nome", nome);


        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);

        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Log.d("db", "Sucesso ao salvar do dados!"); /* Para saber se salvou no banco coretamente! */

            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("db_erro", "Erro ao salvar os dados!" + e.toString());

                    }
                });
    }

    public void IniciarCadastroCliente(){

        nomeCliente          = findViewById(R.id.edit_nome);
        emailCliente         = findViewById(R.id.edit_email);
        senhaCliente         = findViewById(R.id.edit_senha);
        BotaoCadastroCliente = findViewById(R.id.btncadastroCliente);

    }

}