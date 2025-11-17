package com.educacao.cursos.services;

import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Prova;
import com.educacao.cursos.models.Questao;
import com.educacao.cursos.models.Inscricao;
import java.util.ArrayList;
import java.util.List;

public class ProvaService {
    private List<Prova> provas;
    private int proximoId;

    public ProvaService() {
        this.provas = new ArrayList<>();
        this.proximoId = 1;
    }

    public void criarProva(Curso curso, String titulo) {
        Prova novaProva = new Prova(proximoId++, curso, titulo);
        provas.add(novaProva);
    }

    public void adicionarQuestao(int provaId, String enunciado, String respostaCorreta, String[] opcoes) {
        Prova prova = buscarProvaPorId(provaId);
        if (prova != null) {
            Questao questao = new Questao(prova.getQuestoes().size() + 1, enunciado, respostaCorreta, opcoes);
            prova.adicionarQuestao(questao);
        }
    }

    public Prova buscarProvaPorId(int id) {
        return provas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Prova> buscarProvasPorCurso(int cursoId) {
        return provas.stream()
                .filter(p -> p.getCurso().getId() == cursoId)
                .toList();
    }

    public double corrigirProva(int provaId, List<String> respostas) {
        Prova prova = buscarProvaPorId(provaId);
        if (prova != null) {
            return prova.corrigir(respostas);
        }
        return 0.0;
    }

    public void registrarNotaAluno(Inscricao inscricao, double nota) {
        inscricao.adicionarNota(nota);
        System.out.println("Nota " + String.format("%.2f", nota) + " registrada para " + inscricao.getAluno().getNome());
    }

    public void exibirTodasProvas() {
        System.out.println("\n=== TODAS AS PROVAS ===");
        if (provas.isEmpty()) {
            System.out.println("Nenhuma prova cadastrada.");
        } else {
            provas.forEach(System.out::println);
        }
    }
}
