package com.example.ufmsquizz.db;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome, email, senha, fotoPerfil;
    //private Byte fotoPerfil;

    public Usuario() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String toString() {
        return "Nome: " + nome.toString() + "\nEmail: " + email.toString();
    }
}