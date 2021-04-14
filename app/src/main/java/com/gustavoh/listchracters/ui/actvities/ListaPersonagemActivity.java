package com.gustavoh.listchracters.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gustavoh.listchracters.R;
import com.gustavoh.listchracters.dao.PersonagemDAO;
import com.gustavoh.listchracters.model.Personagem;

import java.util.List;

import static com.gustavoh.listchracters.ui.actvities.ConstantesActivitys.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Personagens";
    //usa as funções criada na classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pega o layout da lista de personagem
        setContentView(R.layout.activity_lista_personagem);

        //seta o titulo do app
        setTitle(TITULO_APPBAR);
        //configuração do botão
        configuraFABnovoPersonagem();

    }

    private void configuraFABnovoPersonagem() {
        //cria a variavel para achar o botão
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);

        //cria a função de click no botão
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //função para poder abrir o formulario
                AbrirFormulario();
            }
        });
    }

    private void AbrirFormulario() {
        //função criada para ir para a tela de adicionar personagem na lista
        startActivity(new Intent(this, FormularioPersonagensActivity.class));
    }

    //Faz a Persistencia da lista
    @Override
    protected void onResume() {
        //salva para caso a pessoa retore a lista e apaga o que ja foi criado
        super.onResume();

        //pega o id da lista no app
        ListView listadepersonagens = findViewById(R.id.activity_main_list_characters);
        //adapta a lista no app
        final List<Personagem> personagens = dao.todos();
        //função para pegar a lista de ersonagens
        listaDePersonagens(listadepersonagens, personagens);
        //função para configurar um item da lista
        configuraItemLista(listadepersonagens);
    }

    private void configuraItemLista(ListView listadepersonagens) {
        listadepersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //seleciona o item da lista
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioEdicao(personagemEscolhido);
            }
        });
    }

    private void abreFormularioEdicao(Personagem personagemEscolhido) {
        //*Pega a informação do item do formulario e o chama
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagensActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
        //*
    }

    private void listaDePersonagens(ListView listadepersonagens, List<Personagem> personagens) {
        //passa as informações para a lista no layout
        listadepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}
