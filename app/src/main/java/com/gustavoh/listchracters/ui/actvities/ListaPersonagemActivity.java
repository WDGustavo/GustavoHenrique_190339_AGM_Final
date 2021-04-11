package com.gustavoh.listchracters.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gustavoh.listchracters.R;
import com.gustavoh.listchracters.dao.PersonagemDAO;
import com.gustavoh.listchracters.model.Personagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    //usa as funções criada na classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //pega o layout da lista de personagem
        setContentView(R.layout.activity_lista_personagem);

        //seta o titulo do app
        setTitle("Lista de Personagens");

        dao.salva(new Personagem("Ken", "1,80","165101615"));
        dao.salva(new Personagem("Ryu", "1,80","165101615"));
        //cria a variavel para achar o botão
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);

        //cria a função de click no botão
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //função criada para ir para a tela de adicionar personagem na lista
                startActivity(new Intent(ListaPersonagemActivity.this,FormularioPersonagensActivity.class));
            }
        });

    }

    //Faz a Persistencia da lista
    @Override
    protected void onResume() {
        super.onResume();

        //pega o id da lista no app
        ListView listadepersonagens = findViewById(R.id.activity_main_list_characters);
        //adapta a lista no app
        List<Personagem> personagens = dao.todos();
        listadepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listadepersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
                //Log.i("personagem",""+ personagemEscolhido);
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagensActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);
            }
        });
    }
}
