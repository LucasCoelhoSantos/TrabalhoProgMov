package com.example.ufmsquizz.db;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nome, email, senha;
    private byte fotoPerfil;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, byte fotoPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
    }

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

    public byte getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String toString() {
        return "Nome: " + nome.toString() + "\nEmail: " + email.toString();
    }
}