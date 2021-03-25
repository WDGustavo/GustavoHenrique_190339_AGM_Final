package com.gustavoh.listchracters.model;

import androidx.annotation.NonNull;

//classe criada
public class Personagem {

    //cria as strings privadas
    private final String nome;
    private final String altura;
    private final String nascimento;

    //Cria o construtor
    public Personagem(String nome, String altura, String nascimento) {
        //Seta o que esta escrito na string para o encapsulamento
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    //retornara o que foi escrito pelo usuario
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    /*public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/
}
