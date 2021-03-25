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

public class FormularioPersonagensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagens);

        PersonagemDAO dao = new PersonagemDAO();

        EditText campoNome = findViewById(R.id.edittext_Nome);
        EditText campoAltura = findViewById(R.id.edittext_Altura);
        EditText campoNascimento = findViewById(R.id.edittext_data_nascimento);

        Button botaoEnviar = findViewById(R.id.button_enviar);
        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);

                startActivity(new Intent(FormularioPersonagensActivity.this, ListaPersonagemActivity.class));


                new Personagem(nome,altura,nascimento);

                //Toast.makeText(FormularioPersonagensActivity.this, personagemSalvo.getNome() + " - " + personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),Toast.LENGTH_SHORT).show();

                //Toast.makeText(FormularioPersonagensActivity.this,"Estou Funcionando",Toast.LENGTH_SHORT).show();
            }
        });
    }
}