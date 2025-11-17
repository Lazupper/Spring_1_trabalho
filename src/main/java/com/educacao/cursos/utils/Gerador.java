package com.educacao.cursos.utils;

public class Gerador {
    
    public static String gerarMatricula() {
        return "MAT-" + System.currentTimeMillis();
    }

    public static String gerarNumeroRegistroCertificado() {
        return "CERT-" + System.currentTimeMillis();
    }

    public static String gerarIdInscricao() {
        return "INS-" + System.currentTimeMillis();
    }

    public static void imprimirSeparador() {
        System.out.println("\n" + "=".repeat(60));
    }

    public static void imprimirSeparadorSimples() {
        System.out.println("-".repeat(60));
    }

    public static void imprimirTitulo(String titulo) {
        imprimirSeparador();
        System.out.println(centralizarTexto(titulo));
        imprimirSeparador();
    }

    public static String centralizarTexto(String texto) {
        int padding = (60 - texto.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + texto;
    }
}
