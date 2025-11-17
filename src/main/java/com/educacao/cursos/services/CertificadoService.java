package com.educacao.cursos.services;

import com.educacao.cursos.models.Certificado;
import com.educacao.cursos.models.Inscricao;
import java.util.ArrayList;
import java.util.List;

public class CertificadoService {
    private List<Certificado> certificados;
    private int proximoId;

    public CertificadoService() {
        this.certificados = new ArrayList<>();
        this.proximoId = 1;
    }

    public Certificado emitirCertificado(Inscricao inscricao) {
        if (!inscricao.isAprovado()) {
            System.out.println("Aluno não atingiu a média mínima para certificado!");
            return null;
        }

        Certificado novoCertificado = new Certificado(proximoId++, inscricao, inscricao.getMediaFinal());
        certificados.add(novoCertificado);
        inscricao.definirCertificado(true);
        System.out.println("Certificado emitido para " + inscricao.getAluno().getNome() + 
                         " - Registro: " + novoCertificado.getNumeroRegistro());
        return novoCertificado;
    }

    public Certificado buscarCertificadoPorId(int id) {
        return certificados.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public List<Certificado> buscarCertificadosPorAluno(int alunoId) {
        return certificados.stream()
                .filter(c -> c.getInscricao().getAluno().getId() == alunoId)
                .toList();
    }

    public List<Certificado> listarTodos() {
        return new ArrayList<>(certificados);
    }

    public int getTotalCertificadosEmitidos() {
        return certificados.size();
    }

    public void exibirTodosCertificados() {
        System.out.println("\n=== TODOS OS CERTIFICADOS ===");
        if (certificados.isEmpty()) {
            System.out.println("Nenhum certificado emitido.");
        } else {
            certificados.forEach(System.out::println);
        }
    }
}
