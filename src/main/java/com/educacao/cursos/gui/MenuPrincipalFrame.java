package com.educacao.cursos.gui;

import com.educacao.cursos.auth.Usuario;
import com.educacao.cursos.auth.AuthService;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalFrame extends JFrame {
    private Usuario usuarioLogado;
    
    public MenuPrincipalFrame(Usuario usuario) {
        this.usuarioLogado = usuario;
        
        setTitle("Sistema de Gestão de Cursos - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 102, 153));
        JLabel welcomeLabel = new JLabel("Bem-vindo, " + usuarioLogado.getEmail());
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(welcomeLabel);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Menu
        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        switch (usuarioLogado.getTipo()) {
            case ALUNO:
                adicionarBotaoMenu(menuPanel, "Meus Cursos", e -> abrirMeusCursos());
                adicionarBotaoMenu(menuPanel, "Inscrever em Curso", e -> abrirInscricao());
                adicionarBotaoMenu(menuPanel, "Minhas Notas", e -> abrirMinhasNotas());
                break;
                
            case PROFESSOR:
                adicionarBotaoMenu(menuPanel, "Meus Cursos", e -> abrirMeusCursos());
                adicionarBotaoMenu(menuPanel, "Criar Curso", e -> abrirCriarCurso());
                adicionarBotaoMenu(menuPanel, "Registrar Notas", e -> abrirRegistroNotas());
                break;
                
            case ADMIN:
                adicionarBotaoMenu(menuPanel, "Gerenciar Usuários", e -> abrirGerenciarUsuarios());
                adicionarBotaoMenu(menuPanel, "Relatórios", e -> abrirRelatorios());
                break;
        }
        
        // Botão logout
        adicionarBotaoMenu(menuPanel, "Logout", e -> fazerLogout());
        
        panel.add(menuPanel, BorderLayout.CENTER);
        add(panel);
    }
    
    private void adicionarBotaoMenu(JPanel panel, String titulo, javax.swing.Action acao) {
        JButton btn = new JButton(titulo);
        btn.setAction(acao);
        btn.setText(titulo);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(btn);
    }
    
    private void abrirMeusCursos() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirInscricao() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirMinhasNotas() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirCriarCurso() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirRegistroNotas() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirGerenciarUsuarios() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void abrirRelatorios() {
        JOptionPane.showMessageDialog(this, "Função em desenvolvimento", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void fazerLogout() {
        AuthService.logout();
        JOptionPane.showMessageDialog(this, "Logout realizado");
        dispose();
        new LoginFrame().setVisible(true);
    }
}
