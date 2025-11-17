package com.educacao.cursos.models;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa {
    private String especializacao;
    private List<Curso> cursosDados;

    public Professor(int id, String nome, String email, String telefone, String especializacao) {
        super(id, nome, email, telefone);
        this.especializacao = especializacao;
        this.cursosDados = new ArrayList<>();
    }

    public String getEspecializacao() { return especializacao; }
    public List<Curso> getCursosDados() { return cursosDados; }

    public void adicionarCurso(Curso curso) {
        cursosDados.add(curso);
    }

    @Override
    public String toString() {
        return super.toString() + " | Especialização: " + especializacao + " | Cursos: " + cursosDados.size();
    }
}
