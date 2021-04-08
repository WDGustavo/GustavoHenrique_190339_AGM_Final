package com.gustavoh.listchracters.dao;

import com.gustavoh.listchracters.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //cria a uma lista para ser adcionado o que o usuario escrever
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeID = 1;
    //função para salvar na lista o que foi escrito
    public void salva(Personagem personagemSalvo) {

        personagemSalvo.setId(contadorDeID);
        personagens.add(personagemSalvo);
        contadorDeID++;
    }

    public void editar(Personagem personagem){
        Personagem personagemEscolhido = null;
        for(Personagem p :personagens){
            if(p.getId() == personagem.getId())
            {
                personagemEscolhido = p;
            }
        }
        if(personagemEscolhido !=null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    //função para a lista ser carregada
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
