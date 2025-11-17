package com.educacao.cursos.models;

import java.time.LocalDate;

public class Certificado {
    private int id;
    private Inscricao inscricao;
    private LocalDate dataEmissao;
    private String numeroRegistro;
    private double notaFinal;

    public Certificado(int id, Inscricao inscricao, double notaFinal) {
        this.id = id;
        this.inscricao = inscricao;
        this.dataEmissao = LocalDate.now();
        this.notaFinal = notaFinal;
        this.numeroRegistro = gerarNumeroRegistro();
    }

    private String gerarNumeroRegistro() {
        return "CERT-" + System.currentTimeMillis();
    }

    public int getId() { return id; }
    public Inscricao getInscricao() { return inscricao; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public String getNumeroRegistro() { return numeroRegistro; }
    public double getNotaFinal() { return notaFinal; }

    @Override
    public String toString() {
        return "Certificado ID: " + id + " | Aluno: " + inscricao.getAluno().getNome() +
               " | Curso: " + inscricao.getCurso().getNome() + " | Nota: " + String.format("%.2f", notaFinal) +
               " | Registro: " + numeroRegistro + " | Emissão: " + dataEmissao;
    }
}
