package com.educacao.cursos.services;

import com.educacao.cursos.models.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoService {
    private List<Aluno> alunos;
    private int proximoId;

    public AlunoService() {
        this.alunos = new ArrayList<>();
        this.proximoId = 1;
    }

    public void cadastrarAluno(String nome, String email, String telefone) {
        String matricula = "MAT-" + System.currentTimeMillis();
        Aluno novoAluno = new Aluno(proximoId++, nome, email, telefone, matricula);
        alunos.add(novoAluno);
    }

    public Aluno buscarAlunoPorId(int id) {
        return alunos.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        return alunos.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    public List<Aluno> listarTodosAlunos() {
        return new ArrayList<>(alunos);
    }

    public int getTotalAlunos() {
        return alunos.size();
    }

    public void atualizarDadosAluno(int id, String nome, String email, String telefone) {
        Aluno aluno = buscarAlunoPorId(id);
        if (aluno != null) {
            aluno.setNome(nome);
            aluno.setEmail(email);
            aluno.setTelefone(telefone);
        }
    }

    public void exibirTodosAlunos() {
        System.out.println("\n=== TODOS OS ALUNOS ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            alunos.forEach(System.out::println);
        }
    }
}
