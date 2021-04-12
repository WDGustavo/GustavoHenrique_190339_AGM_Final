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

public class FormularioPersonagensActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Cria uma variavel referente a classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();;
    private Personagem Personagem;

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
        editarPersonagem();
    }

    private void editarPersonagem() {
        //Permite a edição do elemento da lista
        Intent dados = getIntent();
        if(dados.hasExtra("personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            campoNome.setText(personagem.getNome());
            campoAltura.setText(personagem.getAltura());
            campoNascimento.setText(personagem.getNascimento());
        }else{
            Personagem = new Personagem();
        }
    }

    private void botaoAddPersonagem() {
        //encontra o botão
        Button botaoEnviar = findViewById(R.id.button_enviar);
        //Sera realisada a função ao clicar no botão
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Strings para pegar o que esta escrito nos EditText
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                //Utilisação das funções que tem na classe Personagem
                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                //salva o que esta na variavel
                dao.salva(personagemSalvo);
                finish();

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);
            }
        });
    }

    private void inicializarCampos() {
        //acha os EditTexts
        campoNome = findViewById(R.id.edittext_Nome);
        campoAltura = findViewById(R.id.edittext_Altura);
        campoNascimento = findViewById(R.id.edittext_data_nascimento);
    }
}