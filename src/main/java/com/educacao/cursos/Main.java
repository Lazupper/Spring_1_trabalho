package com.educacao.cursos;

import com.educacao.cursos.models.*;
import com.educacao.cursos.services.*;
import com.educacao.cursos.utils.Gerador;
import com.educacao.cursos.utils.ValidadorDados;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Gerador.imprimirTitulo("SISTEMA DE GESTÃO DE CURSOS ONLINE");
        System.out.println("Iniciando simulação completa do sistema...\n");

        // Inicializar serviços
        CursoService cursoService = new CursoService();
        AlunoService alunoService = new AlunoService();
        InscricaoService inscricaoService = new InscricaoService();
        ProvaService provaService = new ProvaService();
        CertificadoService certificadoService = new CertificadoService();
        RelatorioService relatorioService = new RelatorioService(cursoService, alunoService, inscricaoService, certificadoService);

        // ===== PASSO 1: CADASTRAR PROFESSORES =====
        Gerador.imprimirTitulo("1. CADASTRANDO PROFESSORES");
        Professor prof1 = new Professor(1, "Dr. Carlos Silva", "carlos@university.edu", "11987654321", "Engenharia de Software");
        Professor prof2 = new Professor(2, "Dra. Ana Costa", "ana@university.edu", "11987654322", "Desenvolvimento Web");
        System.out.println("Professor 1 cadastrado: " + prof1.getNome());
        System.out.println("Professor 2 cadastrado: " + prof2.getNome());

        // ===== PASSO 2: CADASTRAR CURSOS =====
        Gerador.imprimirTitulo("2. CADASTRANDO CURSOS");
        cursoService.criarCurso("Java Avançado", "Aprenda programação OOP com Java", prof1, 30, 40, "Programação");
        cursoService.criarCurso("React Fundamentals", "Desenvolvimento frontend com React", prof2, 25, 36, "Web");
        cursoService.criarCurso("Spring Boot Essentials", "Construa aplicações robustas", prof1, 20, 45, "Backend");
        cursoService.exibirTodosCursos();

        // ===== PASSO 3: CADASTRAR ALUNOS =====
        Gerador.imprimirTitulo("3. CADASTRANDO ALUNOS");
        alunoService.cadastrarAluno("João Santos", "joao@student.edu", "11912345678");
        alunoService.cadastrarAluno("Maria Oliveira", "maria@student.edu", "11912345679");
        alunoService.cadastrarAluno("Pedro Costa", "pedro@student.edu", "11912345680");
        alunoService.cadastrarAluno("Ana Ferreira", "ana@student.edu", "11912345681");
        alunoService.exibirTodosAlunos();

        // ===== PASSO 4: INSCREVER ALUNOS EM CURSOS =====
        Gerador.imprimirTitulo("4. INSCREVENDO ALUNOS EM CURSOS");
        Aluno aluno1 = alunoService.buscarAlunoPorId(1);
        Aluno aluno2 = alunoService.buscarAlunoPorId(2);
        Aluno aluno3 = alunoService.buscarAlunoPorId(3);
        Aluno aluno4 = alunoService.buscarAlunoPorId(4);

        Curso curso1 = cursoService.buscarCursoPorId(1);
        Curso curso2 = cursoService.buscarCursoPorId(2);
        Curso curso3 = cursoService.buscarCursoPorId(3);

        inscricaoService.inscreverAluno(aluno1, curso1);
        inscricaoService.inscreverAluno(aluno1, curso3);
        inscricaoService.inscreverAluno(aluno2, curso1);
        inscricaoService.inscreverAluno(aluno2, curso2);
        inscricaoService.inscreverAluno(aluno3, curso2);
        inscricaoService.inscreverAluno(aluno4, curso3);

        // ===== PASSO 5: ADICIONAR AULAS =====
        Gerador.imprimirTitulo("5. ADICIONANDO AULAS AOS CURSOS");
        new Aula(1, curso1, "Introdução ao OOP", "Conceitos básicos de orientação a objetos", 60);
        new Aula(2, curso1, "Herança e Polimorfismo", "Pilares avançados da OOP", 90);
        
        Aula aula1 = new Aula(1, curso1, "Introdução ao OOP", "Conceitos básicos de orientação a objetos", 60);
        Aula aula2 = new Aula(2, curso1, "Herança e Polimorfismo", "Pilares avançados da OOP", 90);
        Aula aula3 = new Aula(3, curso2, "React Hooks", "Gerenciamento de estado com Hooks", 75);

        curso1.adicionarAula(aula1);
        curso1.adicionarAula(aula2);
        curso2.adicionarAula(aula3);

        System.out.println("Curso 1 - " + curso1.getNome() + ": " + curso1.getAulas().size() + " aulas adicionadas");
        System.out.println("Curso 2 - " + curso2.getNome() + ": " + curso2.getAulas().size() + " aulas adicionadas");

        // ===== PASSO 6: CRIAR E RESPONDER PROVAS =====
        Gerador.imprimirTitulo("6. CRIANDO PROVAS E AVALIAÇÕES");
        provaService.criarProva(curso1, "Avaliação 1 - OOP");
        Prova prova1 = provaService.buscarProvaPorId(1);

        // Adicionar questões
        provaService.adicionarQuestao(1, "O que é encapsulamento?", "A", 
                                     new String[]{"A) Proteção de dados", "B) Método público", "C) Variável global"});
        provaService.adicionarQuestao(1, "Qual é o modificador de herança em Java?", "B",
                                     new String[]{"A) private", "B) extends", "C) interface"});
        provaService.adicionarQuestao(1, "Qual é o objetivo do polimorfismo?", "C",
                                     new String[]{"A) Criar classes", "B) Declarar variáveis", "C) Usar um objeto de múltiplas formas"});

        System.out.println("Prova criada: " + prova1.getTitulo());
        System.out.println("Total de questões: " + prova1.getQuestoes().size());

        // ===== PASSO 7: REGISTRAR NOTAS =====
        Gerador.imprimirTitulo("7. REGISTRANDO NOTAS DOS ALUNOS");

        // Aluno 1 no Curso 1
        Inscricao ins1 = inscricaoService.buscarInscricaoPorId(1);
        provaService.registrarNotaAluno(ins1, 8.5);
        provaService.registrarNotaAluno(ins1, 9.0);

        // Aluno 2 no Curso 1
        Inscricao ins2 = inscricaoService.buscarInscricaoPorId(2);
        provaService.registrarNotaAluno(ins2, 7.0);
        provaService.registrarNotaAluno(ins2, 7.5);

        // Aluno 2 no Curso 2
        Inscricao ins3 = inscricaoService.buscarInscricaoPorId(4);
        provaService.registrarNotaAluno(ins3, 9.5);
        provaService.registrarNotaAluno(ins3, 10.0);

        // Aluno 3 no Curso 2
        Inscricao ins4 = inscricaoService.buscarInscricaoPorId(5);
        provaService.registrarNotaAluno(ins4, 5.5);
        provaService.registrarNotaAluno(ins4, 6.0);

        // Aluno 1 no Curso 3
        Inscricao ins5 = inscricaoService.buscarInscricaoPorId(3);
        provaService.registrarNotaAluno(ins5, 8.0);
        provaService.registrarNotaAluno(ins5, 8.5);

        // Aluno 4 no Curso 3
        Inscricao ins6 = inscricaoService.buscarInscricaoPorId(6);
        provaService.registrarNotaAluno(ins6, 7.5);
        provaService.registrarNotaAluno(ins6, 8.0);

        // ===== PASSO 8: EMITIR CERTIFICADOS =====
        Gerador.imprimirTitulo("8. EMITINDO CERTIFICADOS");
        List<Inscricao> inscricoes = inscricaoService.listarTodas();
        for (Inscricao inscricao : inscricoes) {
            certificadoService.emitirCertificado(inscricao);
        }

        // ===== PASSO 9: GERAR DASHBOARD =====
        Gerador.imprimirTitulo("9. GERANDO DASHBOARD");
        relatorioService.gerarDashboard();

        // ===== PASSO 10: GERAR RELATÓRIOS INDIVIDUAIS =====
        Gerador.imprimirTitulo("10. RELATÓRIOS DE DESEMPENHO INDIVIDUAL");
        relatorioService.gerarRelatorioDesempenhoAluno(1);
        relatorioService.gerarRelatorioDesempenhoAluno(2);
        relatorioService.gerarRelatorioDesempenhoAluno(3);

        // ===== PASSO 11: RELATÓRIO COMPLETO =====
        Gerador.imprimirTitulo("11. RELATÓRIO COMPLETO DO SISTEMA");
        relatorioService.gerarRelatorioCompleto();

        // ===== PASSO 12: DEMONSTRAÇÃO DE VALIDAÇÕES =====
        Gerador.imprimirTitulo("12. DEMONSTRANDO VALIDAÇÕES");
        System.out.println("Email válido: " + ValidadorDados.validarEmail("joao@example.com"));
        System.out.println("Email inválido: " + ValidadorDados.validarEmail("email_invalido"));
        System.out.println("Telefone válido: " + ValidadorDados.validarTelefone("11987654321"));
        System.out.println("Nota válida (8.5): " + ValidadorDados.validarNota(8.5));
        System.out.println("Nota inválida (15): " + ValidadorDados.validarNota(15));

        // ===== PASSO 13: DEMONSTRAÇÃO DE TENTATIVAS INVÁLIDAS =====
        Gerador.imprimirTitulo("13. DEMONSTRANDO REGRAS DE NEGÓCIO");
        System.out.println("\nTentando inscrever aluno já inscrito:");
        inscricaoService.inscreverAluno(aluno1, curso1);

        System.out.println("\nTentando emitir certificado para aluno reprovado:");
        Aluno alunoReprovado = alunoService.buscarAlunoPorId(3);
        Inscricao insReprovada = inscricaoService.buscarInscricaoPorId(5);
        certificadoService.emitirCertificado(insReprovada);

        // ===== FINALIZAÇÃO =====
        Gerador.imprimirTitulo("SIMULAÇÃO COMPLETA FINALIZADA COM SUCESSO");
        System.out.println("\nResumo Final:");
        System.out.println("- Total de Cursos: " + cursoService.getTotalCursos());
        System.out.println("- Total de Alunos: " + alunoService.getTotalAlunos());
        System.out.println("- Total de Inscrições: " + inscricaoService.listarTodas().size());
        System.out.println("- Total de Certificados: " + certificadoService.getTotalCertificadosEmitidos());
        System.out.println("\nTodas as operações essenciais do domínio foram executadas com sucesso!");
    }
}
