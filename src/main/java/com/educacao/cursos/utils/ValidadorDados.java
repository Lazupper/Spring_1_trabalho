package com.educacao.cursos.utils;

public class ValidadorDados {
    
    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.replaceAll("[^0-9]", "").length() >= 10;
    }

    public static boolean validarNota(double nota) {
        return nota >= 0 && nota <= 10;
    }

    public static boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty() && nome.length() >= 3;
    }

    public static boolean validarCargaHoraria(int cargaHoraria) {
        return cargaHoraria > 0 && cargaHoraria <= 1000;
    }

    public static boolean validarVagas(int vagas) {
        return vagas > 0 && vagas <= 500;
    }

    public static void exibirErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);
    }

    public static void exibirSucesso(String mensagem) {
        System.out.println("SUCESSO: " + mensagem);
    }

    public static void exibirInfo(String mensagem) {
        System.out.println("INFO: " + mensagem);
    }
}
