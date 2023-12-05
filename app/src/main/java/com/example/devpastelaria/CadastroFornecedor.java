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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroFornecedor extends AppCompatActivity {

    private EditText nomeFornecedor, emailFornecedor, senhaFornecedor;
    private Button BotaoCadastroFornecedor;

    String mensagem[] = {"Preencha todos os campos!", "Fornecedor cadastrado com sucesso!"};
    String fornecedorID;

    private int idProduto = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_fornecedor);

        IniciarCadastroFornecedor();

        BotaoCadastroFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome  = nomeFornecedor.getText().toString();
                String email = emailFornecedor.getText().toString();
                String senha = senhaFornecedor.getText().toString();

                if( nome.isEmpty() || email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v, mensagem[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    CadastrarFornecedor(v);
                }
            }
        });
    }

    public void CadastrarFornecedor(View v){

        String email = emailFornecedor.getText().toString();
        String senha = senhaFornecedor.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    SalvarDadosFornecedor();

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

                        erro = "E-mail fornecedor já existe!";

                    }catch (FirebaseAuthUserCollisionException e){

                        erro = "Essa conta fornecedor já existe!";

                    } catch (Exception e){

                        erro = "Erro ao cadastrar fornecedor!";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });

    }

    private  void SalvarDadosFornecedor(){

        String nome = nomeFornecedor.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> fornecedores = new HashMap<>();

        fornecedores.put("nome", nome);

        fornecedorID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Fornecedores").document(fornecedorID);

        /*String idProduct = String.valueOf(idProduto);

        DocumentReference dr = documentReference.collection("Produtos").document(idProduct);

        idProduto++;

        Map<String, Object> produtos = new HashMap<>();

        produtos.put("nome", "pastel");
        produtos.put("preço", 5.90);
        produtos.put("quantidade", 40);
        dr.set(produtos);*/


        documentReference.set(fornecedores).addOnSuccessListener(new OnSuccessListener<Void>() {
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



    public void IniciarCadastroFornecedor(){

        nomeFornecedor          = findViewById(R.id.edit_nome_cadastro_fornecedor);
        emailFornecedor         = findViewById(R.id.edit_email_cadastro_fornecedor);
        senhaFornecedor         = findViewById(R.id.edit_senha_cadastro_fornecedor);
        BotaoCadastroFornecedor = findViewById(R.id.btncadastroFornecedor);

    }


}