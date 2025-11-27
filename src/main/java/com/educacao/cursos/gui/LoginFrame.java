package com.educacao.cursos.gui;

import com.educacao.cursos.auth.AuthService;
import com.educacao.cursos.auth.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JComboBox<Usuario.TipoUsuario> tipoCombo;
    
    public LoginFrame() {
        setTitle("Sistema de Gestão de Cursos - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel titleLabel = new JLabel("Login do Sistema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        
        // Email
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        
        // Senha
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Senha:"), gbc);
        senhaField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(senhaField, gbc);
        
        // Tipo de usuário
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Tipo:"), gbc);
        tipoCombo = new JComboBox<>(Usuario.TipoUsuario.values());
        gbc.gridx = 1;
        panel.add(tipoCombo, gbc);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> fazerLogin());
        buttonPanel.add(loginBtn);
        
        JButton registroBtn = new JButton("Registrar");
        registroBtn.addActionListener(e -> abrirRegistro());
        buttonPanel.add(registroBtn);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        add(panel);
    }
    
    private void fazerLogin() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        
        Usuario usuario = AuthService.login(email, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            abrirMenuPrincipal(usuario);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirRegistro() {
        String email = JOptionPane.showInputDialog("Email:");
        if (email == null) return;
        
        String senha = JOptionPane.showInputDialog("Senha:");
        if (senha == null) return;
        
        Usuario.TipoUsuario tipo = (Usuario.TipoUsuario) tipoCombo.getSelectedItem();
        
        if (AuthService.registrar(email, senha, tipo)) {
            JOptionPane.showMessageDialog(this, "Registro bem-sucedido!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao registrar", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirMenuPrincipal(Usuario usuario) {
        new MenuPrincipalFrame(usuario).setVisible(true);
    }
}
