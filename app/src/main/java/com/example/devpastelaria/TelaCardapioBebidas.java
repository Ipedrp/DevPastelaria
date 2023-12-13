package com.example.devpastelaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.devpastelaria.databinding.ActivityTelaCardapioBebidasBinding;

import java.util.ArrayList;

public class TelaCardapioBebidas extends AppCompatActivity {

   private ActivityTelaCardapioBebidasBinding binding;
   private MyAdapterBebidas myAdapterBebidas;

   private ArrayList<Bebidas> bebidasList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cardapio_bebidas);

        binding = ActivityTelaCardapioBebidasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerViewBebidas = binding.recyclerViewBebidas;

        recyclerViewBebidas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBebidas.setHasFixedSize(true);

       myAdapterBebidas = new MyAdapterBebidas(this, bebidasList);
       recyclerViewBebidas.setAdapter(myAdapterBebidas);

       getBebidas();
    }

    private void getBebidas(){

        Bebidas bebida01 = new Bebidas(
                "Cola-Cola",
                7.50
        );
        bebidasList.add(bebida01);

        Bebidas bebida02 = new Bebidas(
                "Fanta Uva",
                7.50
        );
        bebidasList.add(bebida02);

        Bebidas bebida03 = new Bebidas(
                "Pepsi",
                7.50
        );
        bebidasList.add(bebida03);

        Bebidas bebida04 = new Bebidas(
                "Água",
                3.50
        );
        bebidasList.add(bebida04);

        Bebidas bebida05 = new Bebidas(
                "Cerveja",
                17.50
        );
        bebidasList.add(bebida05);

    }
}