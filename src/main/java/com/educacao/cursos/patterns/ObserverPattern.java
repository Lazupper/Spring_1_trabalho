package com.educacao.cursos.patterns;

import java.util.*;

public class ObserverPattern {
    
    // Interface Observer
    public interface Observer {
        void update(String evento);
    }
    
    // Classe Observable
    public static class Observable {
        private List<Observer> observers = new ArrayList<>();
        
        public void adicionarObservador(Observer observer) {
            observers.add(observer);
        }
        
        public void removerObservador(Observer observer) {
            observers.remove(observer);
        }
        
        public void notificarObservadores(String evento) {
            for (Observer observer : observers) {
                observer.update(evento);
            }
        }
    }
    
    // Implementação: Monitorador de Cursos
    public static class MonitoradorCursos extends Observable {
        
        public void inscreverAluno(String nomeCurso, String nomeAluno) {
            notificarObservadores("Novo aluno inscrito em " + nomeCurso + ": " + nomeAluno);
        }
        
        public void atualizarNota(String nomeCurso, String nomeAluno, double nota) {
            notificarObservadores("Nota atualizada para " + nomeAluno + " em " + nomeCurso + ": " + nota);
        }
    }
    
    // Observadores
    public static class ProfessorObserver implements Observer {
        private String nomeProfessor;
        
        public ProfessorObserver(String nome) {
            this.nomeProfessor = nome;
        }
        
        @Override
        public void update(String evento) {
            System.out.println("[PROFESSOR " + nomeProfessor + "] Notificado: " + evento);
        }
    }
    
    public static class AdministradorObserver implements Observer {
        @Override
        public void update(String evento) {
            System.out.println("[ADMINISTRADOR] Notificado: " + evento);
        }
    }
}
