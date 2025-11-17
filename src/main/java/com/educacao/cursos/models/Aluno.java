package com.educacao.cursos.models;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String matricula;
    private List<Inscricao> inscricoes;

    public Aluno(int id, String nome, String email, String telefone, String matricula) {
        super(id, nome, email, telefone);
        this.matricula = matricula;
        this.inscricoes = new ArrayList<>();
    }

    public String getMatricula() { return matricula; }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    public void adicionarInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public Inscricao buscarInscricaoPorCurso(int cursoId) {
        return inscricoes.stream()
                .filter(i -> i.getCurso().getId() == cursoId)
                .findFirst()
                .orElse(null);
    }

    public double calcularMediaGeral() {
        if (inscricoes.isEmpty()) return 0.0;
        return inscricoes.stream()
                .mapToDouble(Inscricao::getMediaFinal)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + matricula + " | Cursos inscritos: " + inscricoes.size();
    }
}
