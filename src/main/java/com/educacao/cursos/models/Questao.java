package com.educacao.cursos.models;

public class Questao {
    private int id;
    private String enunciado;
    private String respostaCorreta;
    private String[] opcoes;

    public Questao(int id, String enunciado, String respostaCorreta, String[] opcoes) {
        this.id = id;
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.opcoes = opcoes;
    }

    public int getId() { return id; }
    public String getEnunciado() { return enunciado; }
    public String getRespostaCorreta() { return respostaCorreta; }
    public String[] getOpcoes() { return opcoes; }

    public boolean verificarResposta(String resposta) {
        return resposta.equalsIgnoreCase(respostaCorreta);
    }

    @Override
    public String toString() {
        return "Q" + id + ": " + enunciado;
    }
}
