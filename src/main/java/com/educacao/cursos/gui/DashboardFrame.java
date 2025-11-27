package com.educacao.cursos.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame() {
        setTitle("Dashboard - Estatísticas do Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 102, 153));
        JLabel titleLabel = new JLabel("Dashboard do Sistema");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Conteúdo principal
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        contentPanel.add(criarCardEstatistica("Total de Alunos", "248", new Color(76, 175, 80)));
        contentPanel.add(criarCardEstatistica("Total de Cursos", "15", new Color(33, 150, 243)));
        contentPanel.add(criarCardEstatistica("Taxa de Aprovação", "85%", new Color(255, 152, 0)));
        contentPanel.add(criarCardEstatistica("Professores Ativos", "8", new Color(156, 39, 176)));
        
        panel.add(contentPanel, BorderLayout.CENTER);
        
        // Rodapé
        JPanel footerPanel = new JPanel();
        JButton fecharBtn = new JButton("Fechar");
        fecharBtn.addActionListener(e -> dispose());
        footerPanel.add(fecharBtn);
        panel.add(footerPanel, BorderLayout.SOUTH);
        
        add(panel);
    }
    
    private JPanel criarCardEstatistica(String titulo, String valor, Color cor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(cor, 3));
        card.setBackground(new Color(240, 240, 240));
        
        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setForeground(cor);
        card.add(tituloLabel, BorderLayout.NORTH);
        
        JLabel valorLabel = new JLabel(valor);
        valorLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valorLabel.setForeground(cor);
        valorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(valorLabel, BorderLayout.CENTER);
        
        return card;
    }
}
