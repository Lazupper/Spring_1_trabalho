package com.educacao.cursos.notifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NotificacaoService {
    private static List<Notificacao> notificacoes = new ArrayList<>();
    private static List<NotificacaoListener> listeners = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static class Notificacao {
        public int id;
        public String titulo;
        public String mensagem;
        public LocalDateTime dataCriacao;
        public TipoNotificacao tipo;
        public boolean lida;
        
        public Notificacao(String titulo, String mensagem, TipoNotificacao tipo) {
            this.id = notificacoes.size() + 1;
            this.titulo = titulo;
            this.mensagem = mensagem;
            this.tipo = tipo;
            this.dataCriacao = LocalDateTime.now();
            this.lida = false;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s: %s (em %s)", tipo, titulo, mensagem, dataCriacao.format(formatter));
        }
    }
    
    public enum TipoNotificacao {
        INSCRICAO, NOTA, CERTIFICADO, AVISO, ATUALIZACAO
    }
    
    public interface NotificacaoListener {
        void notificacaoRecebida(Notificacao notif);
    }
    
    public static void criarNotificacao(String titulo, String mensagem, TipoNotificacao tipo) {
        Notificacao notif = new Notificacao(titulo, mensagem, tipo);
        notificacoes.add(notif);
        System.out.println("NOTIFICAÇÃO: " + notif);
        
        // Notificar listeners
        for (NotificacaoListener listener : listeners) {
            listener.notificacaoRecebida(notif);
        }
    }
    
    public static List<Notificacao> obterNotificacoes() {
        return new ArrayList<>(notificacoes);
    }
    
    public static List<Notificacao> obterNotificacoesNaoLidas() {
        List<Notificacao> naoLidas = new ArrayList<>();
        for (Notificacao notif : notificacoes) {
            if (!notif.lida) {
                naoLidas.add(notif);
            }
        }
        return naoLidas;
    }
    
    public static void marcarComoLida(int id) {
        for (Notificacao notif : notificacoes) {
            if (notif.id == id) {
                notif.lida = true;
                System.out.println("Notificação marcada como lida: " + id);
                return;
            }
        }
    }
    
    public static void adicionarListener(NotificacaoListener listener) {
        listeners.add(listener);
    }
    
    public static void removerListener(NotificacaoListener listener) {
        listeners.remove(listener);
    }
    
    public static void listarTodas() {
        System.out.println("\n=== CENTRAL DE NOTIFICAÇÕES ===");
        if (notificacoes.isEmpty()) {
            System.out.println("Nenhuma notificação");
            return;
        }
        
        for (Notificacao notif : notificacoes) {
            String status = notif.lida ? "[LIDA]" : "[NÃO LIDA]";
            System.out.println(status + " " + notif);
        }
    }
}
