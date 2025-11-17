package com.educacao.cursos.services;

import com.educacao.cursos.models.Aluno;
import com.educacao.cursos.models.Curso;
import com.educacao.cursos.models.Inscricao;
import java.util.List;

public class RelatorioService {
    private CursoService cursoService;
    private AlunoService alunoService;
    private InscricaoService inscricaoService;
    private CertificadoService certificadoService;

    public RelatorioService(CursoService cursoService, AlunoService alunoService, 
                          InscricaoService inscricaoService, CertificadoService certificadoService) {
        this.cursoService = cursoService;
        this.alunoService = alunoService;
        this.inscricaoService = inscricaoService;
        this.certificadoService = certificadoService;
    }

    public void gerarRelatorioDesempenhoAluno(int alunoId) {
        Aluno aluno = alunoService.buscarAlunoPorId(alunoId);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.println("\n========== RELATÓRIO DE DESEMPENHO ==========");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("\nCursos Inscritos:");

        List<Inscricao> inscricoes = inscricaoService.listarInscricoesPorAluno(alunoId);
        if (inscricoes.isEmpty()) {
            System.out.println("  - Nenhum curso inscrito");
        } else {
            for (Inscricao inscricao : inscricoes) {
                System.out.println("  - " + inscricao.getCurso().getNome() + 
                                 " | Média: " + String.format("%.2f", inscricao.getMediaFinal()) +
                                 " | Status: " + (inscricao.isAprovado() ? "APROVADO" : "REPROVADO") +
                                 " | Certificado: " + (inscricao.isCertificadoObtido() ? "SIM" : "NÃO"));
            }
        }

        System.out.println("\nMédia Geral: " + String.format("%.2f", aluno.calcularMediaGeral()));
        System.out.println("==========================================\n");
    }

    public void gerarDashboard() {
        System.out.println("\n========== DASHBOARD DO SISTEMA ==========");
        System.out.println("Total de Cursos: " + cursoService.getTotalCursos());
        System.out.println("Total de Alunos: " + alunoService.getTotalAlunos());
        System.out.println("Total de Inscrições: " + inscricaoService.listarTodas().size());
        System.out.println("Total de Certificados: " + certificadoService.getTotalCertificadosEmitidos());

        System.out.println("\nTaxas de Aprovação por Curso:");
        for (Curso curso : cursoService.listarTodosCursos()) {
            List<Inscricao> inscricoes = inscricaoService.listarInscricoesPorCurso(curso.getId());
            if (!inscricoes.isEmpty()) {
                long aprovados = inscricoes.stream().filter(Inscricao::isAprovado).count();
                double taxa = (aprovados / (double) inscricoes.size()) * 100;
                System.out.println("  - " + curso.getNome() + ": " + String.format("%.2f%%", taxa));
            }
        }

        System.out.println("\nCursos com Maior Demanda:");
        cursoService.listarTodosCursos().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getTotalAlunosInscritos(), c1.getTotalAlunosInscritos()))
                .limit(3)
                .forEach(c -> System.out.println("  - " + c.getNome() + ": " + c.getTotalAlunosInscritos() + " alunos"));

        System.out.println("=========================================\n");
    }

    public void gerarRelatorioCompleto() {
        System.out.println("\n\n" + "=".repeat(50));
        System.out.println("RELATÓRIO COMPLETO DO SISTEMA DE CURSOS");
        System.out.println("=".repeat(50));

        cursoService.exibirTodosCursos();
        alunoService.exibirTodosAlunos();
        inscricaoService.exibirTodasInscricoes();
        certificadoService.exibirTodosCertificados();

        System.out.println("=".repeat(50) + "\n");
    }
}
