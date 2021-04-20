package com.gustavoh.listchracters.ui.actvities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    //cria o atributo adapter
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pega o layout da lista de personagem
        setContentView(R.layout.activity_lista_personagem);

        //seta o titulo do app
        setTitle(TITULO_APPBAR);
        //configuração do botão
        configuraFABnovoPersonagem();
        configuraLista();


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
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        //limpa a lista
        adapter.clear();
        //carrega as informações
        adapter.addAll(dao.todos());
    }
    //cria a funcção para deletar e não ficar com persistencia de dados
    private void remove(Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    //*cria uma caixa para poder remover um item da lista
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return configuraMenu(item);
    }

    private boolean configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
       if(itemId == R.id.activity_lista_personagem_menu_remover) {
           new AlertDialog.Builder(this)
                   .setTitle("Remove Personagem")
                   .setMessage("Tem Certeza que deseja remover")
                   .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                           //pega a posição do item da lista
                           Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                           //remove o item da lista
                           remove(personagemEscolhido);
                       }
                   })
                   .setNegativeButton("Não", null)
                   .show();

       }
        return super.onContextItemSelected(item);
    }

    //*
    private void configuraLista() {
        //pega o id da lista no app
        ListView listadepersonagens = findViewById(R.id.activity_main_list_characters);
        //função para pegar a lista de personagens
        listaDePersonagens(listadepersonagens);
        //função para configurar um item da lista
        configuraItemLista(listadepersonagens);
        registerForContextMenu(listadepersonagens);
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

    private void listaDePersonagens(ListView listadepersonagens) {
        //*passa as informações para a lista no layout
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listadepersonagens.setAdapter(adapter);
        //*
    }
}
