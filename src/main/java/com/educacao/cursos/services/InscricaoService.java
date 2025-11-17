package com.educacao.cursos.services;

import com.educacao.cursos.models.Aluno;
import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Inscricao;
import java.util.ArrayList;
import java.util.List;

public class InscricaoService {
    private List<Inscricao> inscricoes;
    private int proximoId;

    public InscricaoService() {
        this.inscricoes = new ArrayList<>();
        this.proximoId = 1;
    }

    public boolean inscreverAluno(Aluno aluno, Curso curso) {
        if (curso.getVagasDisponiveis() <= 0) {
            System.out.println("Curso sem vagas disponíveis!");
            return false;
        }

        // Verifica se aluno já está inscrito
        if (aluno.buscarInscricaoPorCurso(curso.getId()) != null) {
            System.out.println("Aluno já está inscrito neste curso!");
            return false;
        }

        Inscricao novaInscricao = new Inscricao(proximoId++, aluno, curso);
        inscricoes.add(novaInscricao);
        curso.inscreverAluno(novaInscricao);
        aluno.adicionarInscricao(novaInscricao);
        System.out.println("Aluno " + aluno.getNome() + " inscrito com sucesso em " + curso.getNome());
        return true;
    }

    public void cancelarInscricao(int inscricaoId) {
        Inscricao inscricao = buscarInscricaoPorId(inscricaoId);
        if (inscricao != null) {
            inscricoes.remove(inscricao);
            inscricao.getCurso().cancelarInscricao(inscricao);
            inscricao.getAluno().getInscricoes().remove(inscricao);
            System.out.println("Inscrição cancelada com sucesso!");
        }
    }

    public Inscricao buscarInscricaoPorId(int id) {
        return inscricoes.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public List<Inscricao> listarInscricoesPorAluno(int alunoId) {
        return inscricoes.stream()
                .filter(i -> i.getAluno().getId() == alunoId)
                .toList();
    }

    public List<Inscricao> listarInscricoesPorCurso(int cursoId) {
        return inscricoes.stream()
                .filter(i -> i.getCurso().getId() == cursoId)
                .toList();
    }

    public List<Inscricao> listarTodas() {
        return new ArrayList<>(inscricoes);
    }

    public void exibirTodasInscricoes() {
        System.out.println("\n=== TODAS AS INSCRIÇÕES ===");
        if (inscricoes.isEmpty()) {
            System.out.println("Nenhuma inscrição realizada.");
        } else {
            inscricoes.forEach(System.out::println);
        }
    }
}
