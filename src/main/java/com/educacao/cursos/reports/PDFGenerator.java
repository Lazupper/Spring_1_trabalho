package com.educacao.cursos.reports;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class PDFGenerator {
    
    public static void gerarCertificadoPDF(String nomeAluno, String nomeCurso, String dataEmissao, String nomeArquivo) {
        try {
            // Simulação de geração de PDF usando LaTeX (convertível para PDF depois)
            StringBuilder latex = new StringBuilder();
            
            latex.append("\\documentclass{article}\n");
            latex.append("\\usepackage[utf8]{inputenc}\n");
            latex.append("\\usepackage{geometry}\n");
            latex.append("\\geometry{margin=1in}\n");
            latex.append("\\usepackage{tikz}\n");
            latex.append("\\begin{document}\n");
            latex.append("\\begin{center}\n");
            latex.append("\\begin{tikzpicture}\n");
            latex.append("\\draw (0,0) rectangle (20,14);\n");
            latex.append("\\node at (10,12) {\\Large \\textbf{CERTIFICADO DE CONCLUSÃO}};\n");
            latex.append("\\node at (10,10.5) {Certificamos que};\n");
            latex.append("\\node at (10,9.5) {\\textbf{").append(nomeAluno).append("}};\n");
            latex.append("\\node at (10,8.5) {concluiu com êxito};\n");
            latex.append("\\node at (10,7.5) {\\textbf{").append(nomeCurso).append("}};\n");
            latex.append("\\node at (10,6.5) {Data: ").append(dataEmissao).append("};\n");
            latex.append("\\end{tikzpicture}\n");
            latex.append("\\end{center}\n");
            latex.append("\\end{document}\n");
            
            // Salvar como arquivo de configuração que pode ser convertido
            try (FileWriter writer = new FileWriter(nomeArquivo + ".txt")) {
                writer.write(latex.toString());
            }
            
            System.out.println("Certificado PDF gerado: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao gerar certificado PDF: " + e.getMessage());
        }
    }
    
    public static void gerarRelatorioDesempenhoPDF(String nomeAluno, double[][] notas, String nomeArquivo) {
        try (FileWriter writer = new FileWriter(nomeArquivo + ".txt")) {
            
            writer.write("╔════════════════════════════════════════╗\n");
            writer.write("║      RELATÓRIO DE DESEMPENHO DO ALUNO  ║\n");
            writer.write("╚════════════════════════════════════════╝\n\n");
            
            writer.write("Aluno: " + nomeAluno + "\n");
            writer.write("Data: " + LocalDate.now() + "\n\n");
            
            writer.write("┌──────────────┬────────┬────────┐\n");
            writer.write("│    Prova     │ Nota   │ Status │\n");
            writer.write("├──────────────┼────────┼────────┤\n");
            
            double soma = 0;
            for (int i = 0; i < notas.length; i++) {
                double nota = notas[i][0];
                soma += nota;
                String status = nota >= 7 ? "APROVADO" : "REPROVADO";
                writer.write(String.format("│ Prova %d     │ %.2f   │ %s │\n", i + 1, nota, status));
            }
            
            writer.write("└──────────────┴────────┴────────┘\n\n");
            
            double media = soma / notas.length;
            writer.write("MÉDIA FINAL: " + String.format("%.2f", media) + "\n");
            writer.write("RESULTADO: " + (media >= 7 ? "APROVADO" : "REPROVADO") + "\n");
            
            System.out.println("Relatório de desempenho gerado: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório PDF: " + e.getMessage());
        }
    }
}
