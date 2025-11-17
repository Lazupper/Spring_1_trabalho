package com.educacao.cursos.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscricao {
    private int id;
    private Aluno aluno;
    private Curso curso;
    private LocalDate dataInscricao;
    private String status;
    private List<Double> notas;
    private double mediaFinal;
    private boolean certificadoObtido;

    public Inscricao(int id, Aluno aluno, Curso curso) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataInscricao = LocalDate.now();
        this.status = "ATIVA";
        this.notas = new ArrayList<>();
        this.mediaFinal = 0.0;
        this.certificadoObtido = false;
    }

    public int getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public Curso getCurso() { return curso; }
    public LocalDate getDataInscricao() { return dataInscricao; }
    public String getStatus() { return status; }
    public List<Double> getNotas() { return notas; }
    public double getMediaFinal() { return mediaFinal; }
    public boolean isCertificadoObtido() { return certificadoObtido; }

    public void adicionarNota(double nota) {
        if (nota >= 0 && nota <= 10) {
            notas.add(nota);
            calcularMedia();
        }
    }

    private void calcularMedia() {
        if (!notas.isEmpty()) {
            mediaFinal = notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        }
    }

    public void definirCertificado(boolean obtido) {
        this.certificadoObtido = obtido;
    }

    public boolean isAprovado() {
        return mediaFinal >= 7.0;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome() + " | Curso: " + curso.getNome() +
               " | Data: " + dataInscricao + " | Média: " + String.format("%.2f", mediaFinal) +
               " | Status: " + status + " | Certificado: " + (certificadoObtido ? "SIM" : "NÃO");
    }
}
