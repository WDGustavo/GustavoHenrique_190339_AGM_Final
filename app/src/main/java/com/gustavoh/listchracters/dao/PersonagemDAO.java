package com.gustavoh.listchracters.dao;

import com.gustavoh.listchracters.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    //cria a uma lista para ser adcionado o que o usuario escrever
    private final static List<Personagem> personagens = new ArrayList<>();
    //criado para fazer o contador de id
    private static int contadorDeID = 1;

    //função para salvar na lista o que foi escrito
    public void salva(Personagem personagemSalvo) {
        //salva a informação no id
        personagemSalvo.setId(contadorDeID);
        //adiciona personagem
        personagens.add(personagemSalvo);
        //adiciona mais 1 id ara salvar a informação
        contadorDeID++;
    }

    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscarPersonagemID(personagem);

        if (personagemEscolhido != null) {
            //*faz o posicionamento da informação
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
            //*
        }
    }

    private Personagem buscarPersonagemID(Personagem personagem) {
        //verificação que passa pela lista
        for (Personagem p : personagens) {
            if (p.getId() == personagem.getId()) {
                //guarda e armazena a informação passada
                return p;
            }
        }
        //retorna o personagem
        return null;
    }

    //função para a lista ser carregada
    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }
}
