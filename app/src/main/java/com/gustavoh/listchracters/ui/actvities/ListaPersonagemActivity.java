package com.gustavoh.listchracters.ui.actvities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.gustavoh.listchracters.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        List<String> personagens = new ArrayList<>(Arrays.asList("Alex","Ryu","ken","Guile"));//cria a lista com os nomes

        ListView listadepersonagens = findViewById(R.id.activity_main_list_characters);//pega o id da lista no app
        listadepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));//adapta a lista no app

    }
}
