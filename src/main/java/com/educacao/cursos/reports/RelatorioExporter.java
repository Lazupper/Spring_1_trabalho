package com.educacao.cursos.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class RelatorioExporter {
    
    public static void exportarCSV(String nomeArquivo, String[][] dados, String[] headers) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            
            // Escrever headers
            for (int i = 0; i < headers.length; i++) {
                writer.write(headers[i]);
                if (i < headers.length - 1) writer.write(",");
            }
            writer.write("\n");
            
            // Escrever dados
            for (String[] linha : dados) {
                for (int i = 0; i < linha.length; i++) {
                    writer.write(linha[i]);
                    if (i < linha.length - 1) writer.write(",");
                }
                writer.write("\n");
            }
            
            System.out.println("Relatório CSV exportado: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }
    
    public static void exportarHTML(String nomeArquivo, String titulo, String[][] dados, String[] headers) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("<meta charset='UTF-8'>\n");
            writer.write("<title>" + titulo + "</title>\n");
            writer.write("<style>\n");
            writer.write("body { font-family: Arial; margin: 20px; }\n");
            writer.write("table { border-collapse: collapse; width: 100%; }\n");
            writer.write("th, td { border: 1px solid #333; padding: 10px; text-align: left; }\n");
            writer.write("th { background-color: #336699; color: white; }\n");
            writer.write("</style>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("<h1>" + titulo + "</h1>\n");
            writer.write("<p>Gerado em: " + LocalDate.now() + "</p>\n");
            writer.write("<table>\n");
            
            // Headers
            writer.write("<tr>\n");
            for (String header : headers) {
                writer.write("<th>" + header + "</th>\n");
            }
            writer.write("</tr>\n");
            
            // Dados
            for (String[] linha : dados) {
                writer.write("<tr>\n");
                for (String celula : linha) {
                    writer.write("<td>" + celula + "</td>\n");
                }
                writer.write("</tr>\n");
            }
            
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            
            System.out.println("Relatório HTML exportado: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao exportar HTML: " + e.getMessage());
        }
    }
}
