package com.gustavoh.listchracters.ui.actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gustavoh.listchracters.R;
import com.gustavoh.listchracters.dao.PersonagemDAO;
import com.gustavoh.listchracters.model.Personagem;

import java.io.Serializable;

public class FormularioPersonagensActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Cria uma variavel referente a classe PersonagemDAO
    private final PersonagemDAO dao = new PersonagemDAO();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pega o layout do formulario personagem
        setContentView(R.layout.activity_formulario_personagens);

        //acha os EditTexts
        campoNome = findViewById(R.id.edittext_Nome);
        campoAltura = findViewById(R.id.edittext_Altura);
        campoNascimento = findViewById(R.id.edittext_data_nascimento);

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
                //vai para a lista apos clicar no botão
                //startActivity(new Intent(FormularioPersonagensActivity.this, ListaPersonagemActivity.class));

                //Utilização do encapsulamento
                //new Personagem(nome,altura,nascimento);

                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);

                //Toast.makeText(FormularioPersonagensActivity.this, personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),Toast.LENGTH_SHORT).show();

                //Toast.makeText(FormularioPersonagensActivity.this,"Estou Funcionando",Toast.LENGTH_SHORT).show();

                Intent dados = getIntent();
                Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                campoNome.setText(personagem.getNome());
                campoAltura.setText(personagem.getAltura());
                campoNascimento.setText(personagem.getNascimento());

            }
        });
    }
}