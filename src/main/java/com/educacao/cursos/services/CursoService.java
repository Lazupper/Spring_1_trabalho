package com.educacao.cursos.services;

import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Professor;
import java.util.ArrayList;
import java.util.List;

public class CursoService {
    private List<Curso> cursos;
    private int proximoId;

    public CursoService() {
        this.cursos = new ArrayList<>();
        this.proximoId = 1;
    }

    public void criarCurso(String nome, String descricao, Professor professor, int vagas, int cargaHoraria, String categoria) {
        Curso novoCurso = new Curso(proximoId++, nome, descricao, professor, vagas, cargaHoraria, categoria);
        cursos.add(novoCurso);
        professor.adicionarCurso(novoCurso);
    }

    public Curso buscarCursoPorId(int id) {
        return cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public List<Curso> listarTodosCursos() {
        return new ArrayList<>(cursos);
    }

    public List<Curso> listarCursosPorCategoria(String categoria) {
        return cursos.stream()
                .filter(c -> c.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public List<Curso> listarCursosPorProfessor(Professor professor) {
        return cursos.stream()
                .filter(c -> c.getProfessor().getId() == professor.getId())
                .toList();
    }

    public int getTotalCursos() {
        return cursos.size();
    }

    public void exibirTodosCursos() {
        System.out.println("\n=== TODOS OS CURSOS ===");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            cursos.forEach(System.out::println);
        }
    }
}
