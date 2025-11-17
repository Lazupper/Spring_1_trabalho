package com.educacao.cursos.models;

import java.util.ArrayList;
import java.util.List;

public class Prova {
    private int id;
    private Curso curso;
    private String titulo;
    private List<Questao> questoes;
    private double pontuacaoMaxima;

    public Prova(int id, Curso curso, String titulo) {
        this.id = id;
        this.curso = curso;
        this.titulo = titulo;
        this.questoes = new ArrayList<>();
        this.pontuacaoMaxima = 10.0;
    }

    public int getId() { return id; }
    public Curso getCurso() { return curso; }
    public String getTitulo() { return titulo; }
    public List<Questao> getQuestoes() { return questoes; }
    public double getPontuacaoMaxima() { return pontuacaoMaxima; }

    public void adicionarQuestao(Questao questao) {
        questoes.add(questao);
    }

    public double corrigir(List<String> respostas) {
        if (respostas.size() != questoes.size()) return 0.0;
        
        double acertos = 0;
        for (int i = 0; i < questoes.size(); i++) {
            if (questoes.get(i).verificarResposta(respostas.get(i))) {
                acertos++;
            }
        }
        return (acertos / questoes.size()) * pontuacaoMaxima;
    }

    @Override
    public String toString() {
        return "Prova: " + titulo + " | Questões: " + questoes.size() + " | Pontuação Máxima: " + pontuacaoMaxima;
    }
}
