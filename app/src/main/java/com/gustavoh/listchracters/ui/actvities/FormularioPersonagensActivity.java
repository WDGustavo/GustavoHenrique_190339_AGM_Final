package com.gustavoh.listchracters.ui.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gustavoh.listchracters.R;
import com.gustavoh.listchracters.dao.PersonagemDAO;
import com.gustavoh.listchracters.model.Personagem;

import static com.gustavoh.listchracters.ui.actvities.ConstantesActivitys.CHAVE_PERSONAGEM;

public class FormularioPersonagensActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Cria uma variavel referente a classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();
    //cria a variavel para pegar as informações da classe Personagem
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pega o layout do formulario personagem
        setContentView(R.layout.activity_formulario_personagens);
        //Metodo para achar os edits text
        inicializarCampos();
        //Metodo do botão
        botaoAddPersonagem();
        //Edita o personagem criado
        carregarPersonagem();
    }

    private void carregarPersonagem() {
        //*Permite a edição do elemento da lista
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preenchecampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
        //*
    }

    private void preenchecampos() {
        //*permite o usuario preencher os dados
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
        //*
    }

    private void botaoAddPersonagem() {
        //encontra o botão
        Button botaoEnviar = findViewById(R.id.button_enviar);
        //Sera realisada a função ao clicar no botão
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //função para finalizar o formulario
                finalizarFormulario();
            }
        });
    }

    private void finalizarFormulario() {
        //função para pegar o que foi digitado elo usuario
        preencherPersonagem();
        //verifica a informação é valida
        if(personagem.IdValido()){
            //permite editar a informação
            dao.editar(personagem);
            finish();//finaliza
        } else {
            //salva a informação
            dao.salva(personagem);
        }
        finish();//finaliza
    }

    private void inicializarCampos() {
        //*acha os EditTexts
        campoNome = findViewById(R.id.edittext_Nome);
        campoAltura = findViewById(R.id.edittext_Altura);
        campoNascimento = findViewById(R.id.edittext_data_nascimento);
        //*
    }

    private void preencherPersonagem() {
        //Strings para pegar o que esta escrito nos EditText
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        //*
        //seta o local para salvar a informação adcionada
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        //*
    }
}