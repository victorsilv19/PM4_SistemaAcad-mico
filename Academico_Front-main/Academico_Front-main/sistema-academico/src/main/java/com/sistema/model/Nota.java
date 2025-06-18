package com.sistema.model;

public class Nota {
    private int id;
    private int alunoId;
    private int professorId;
    private int cursoId;
    private double nota;
    private String observacao;

    // Getters e Setters




public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}

public int getAlunoId() {
    return alunoId;
}
public void setAlunoId(int alunoId) {
    this.alunoId = alunoId;
}

public int getProfessorId() {
    return professorId;
}
public void setProfessorId(int professorId) {
    this.professorId = professorId;
}

public int getCursoId() {
    return cursoId;
}
public void setCursoId(int cursoId) {
    this.cursoId = cursoId;
}
 
public double getNota() {
	return nota;
}

public void setNota(double nota) {
	this.nota = nota;
}

public String getObservacao() {
	return observacao;
}

public void setObservacao(String observacao) {
	this.observacao = observacao;
}

}