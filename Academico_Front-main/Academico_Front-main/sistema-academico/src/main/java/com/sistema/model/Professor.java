package com.sistema.model;

public class Professor {
    private int id;
    private String nome;
    private String matricula;
    private String disciplina; 
    public Professor() {}

    public Professor(int id, String nome, String loginInput) {
        this.id = id;
        this.nome = nome;
        this.matricula = loginInput;
    }

    // Getters e setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getLoginInput() { return matricula; }

    public void setLoginInput(String loginInput) { this.matricula = loginInput; }

    public String getDisciplina() { return disciplina; }

    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }
    
    private String senha;

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

}
