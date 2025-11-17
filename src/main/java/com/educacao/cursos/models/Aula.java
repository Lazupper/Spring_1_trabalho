package com.educacao.cursos.models;

import java.time.LocalDate;

public class Aula {
    private int id;
    private Curso curso;
    private String titulo;
    private String conteudo;
    private LocalDate dataPublicacao;
    private int duracao;

    public Aula(int id, Curso curso, String titulo, String conteudo, int duracao) {
        this.id = id;
        this.curso = curso;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDate.now();
        this.duracao = duracao;
    }

    public int getId() { return id; }
    public Curso getCurso() { return curso; }
    public String getTitulo() { return titulo; }
    public String getConteudo() { return conteudo; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public int getDuracao() { return duracao; }

    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    @Override
    public String toString() {
        return "Aula: " + titulo + " | Duração: " + duracao + "min | Publicada em: " + dataPublicacao;
    }
}
