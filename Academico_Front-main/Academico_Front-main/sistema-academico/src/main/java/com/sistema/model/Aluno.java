package com.sistema.model;

public class Aluno {
    private int id;
    private String nome;
    private String matricula;
    private String curso;
    private String senha;  // novo atributo

    public Aluno() {}

    public Aluno(int id, String nome, String loginInput, String curso, String senha) {
        this.id = id;
        this.nome = nome;
        this.matricula = loginInput;
        this.curso = curso;
        this.senha = senha;
    }

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String loginInput) {
        this.matricula = loginInput;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
