package com.gustavoh.listchracters.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

//classe criada
public class Personagem implements Serializable {

    //cria as strings privadas
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    //Cria o construtor
    public Personagem(String nome, String altura, String nascimento) {
        //Seta o que esta escrito na string para o encapsulamento
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem() {
    }


    //*retornara o que foi escrito pelo usuario
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    //*Cria o local para gravar e pegar a informação
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    //*

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    //*

    public boolean IdValido() {
        return id > 0;
    }
}
