package com.gustavoh.listchracters.dao;

import com.gustavoh.listchracters.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {
    //cria a uma lista para ser adcionado o que o usuario escrever
    private final static List<Personagem> personagens = new ArrayList<>();

    //função para salvar na lista o que foi escrito
    public void salva(Personagem personagemSalvo) {
        personagens.add(personagemSalvo);
    }

    //função para a lista ser carregada
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
