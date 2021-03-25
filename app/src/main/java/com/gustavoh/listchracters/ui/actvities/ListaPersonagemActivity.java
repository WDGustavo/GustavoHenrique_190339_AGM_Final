package com.gustavoh.listchracters.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gustavoh.listchracters.R;
import com.gustavoh.listchracters.dao.PersonagemDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        PersonagemDAO dao = new PersonagemDAO();

        setTitle("Lista de Personagens");

        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaPersonagemActivity.this,FormularioPersonagensActivity.class));
            }
        });

        //List<String> personagens = new ArrayList<>(Arrays.asList("Alex","Ryu","ken","Guile"));//cria a lista com os nomes

        ListView listadepersonagens = findViewById(R.id.activity_main_list_characters);//pega o id da lista no app
        listadepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));//adapta a lista no app


    }
}
