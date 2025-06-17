package com.sistema.model;

public class Admin {
    private int id;
    private String nome;
    private String loginInput;
    private String senha;

    public Admin() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLoginInput() {
        return loginInput;
    }
    public void setLoginInput(String matricula) {
        this.loginInput = matricula;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
