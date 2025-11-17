package com.educacao.cursos.models;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nome;
    private String descricao;
    private Professor professor;
    private int vagasDisponiveis;
    private int cargaHoraria;
    private String categoria;
    private List<Aula> aulas;
    private List<Inscricao> inscricoes;

    public Curso(int id, String nome, String descricao, Professor professor, int vagas, int cargaHoraria, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
        this.vagasDisponiveis = vagas;
        this.cargaHoraria = cargaHoraria;
        this.categoria = categoria;
        this.aulas = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Professor getProfessor() { return professor; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public int getCargaHoraria() { return cargaHoraria; }
    public String getCategoria() { return categoria; }
    public List<Aula> getAulas() { return aulas; }
    public List<Inscricao> getInscricoes() { return inscricoes; }

    public void adicionarAula(Aula aula) {
        aulas.add(aula);
    }

    public void inscreverAluno(Inscricao inscricao) {
        if (vagasDisponiveis > 0) {
            inscricoes.add(inscricao);
            vagasDisponiveis--;
        }
    }

    public void cancelarInscricao(Inscricao inscricao) {
        inscricoes.remove(inscricao);
        vagasDisponiveis++;
    }

    public int getTotalAlunosInscritos() {
        return inscricoes.size();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Curso: " + nome + " | Professor: " + professor.getNome() +
               " | Vagas: " + vagasDisponiveis + " | Carga Horária: " + cargaHoraria + "h";
    }
}
