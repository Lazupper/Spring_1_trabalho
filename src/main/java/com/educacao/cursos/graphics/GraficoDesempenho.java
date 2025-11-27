package com.educacao.cursos.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraficoDesempenho extends JPanel {
    private Map<String, Double> dados;
    private String titulo;
    
    public GraficoDesempenho(String titulo) {
        this.titulo = titulo;
        this.dados = new HashMap<>();
        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);
    }
    
    public void adicionarDado(String label, Double valor) {
        dados.put(label, valor);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        
        // Desenhar título
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(titulo, padding, padding - 20);
        
        // Desenhar eixo X e Y
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(padding, height - padding, width - padding, height - padding);
        g2d.drawLine(padding, padding, padding, height - padding);
        
        if (dados.isEmpty()) return;
        
        // Desenhar barras
        int barWidth = (width - 2 * padding) / Math.max(1, dados.size());
        int index = 0;
        double maxValor = dados.values().stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
        
        for (Map.Entry<String, Double> entry : dados.entrySet()) {
            int x = padding + index * barWidth + 10;
            int barHeight = (int) ((entry.getValue() / maxValor) * (height - 2 * padding - 20));
            int y = height - padding - barHeight;
            
            g2d.setColor(new Color(51, 102, 153));
            g2d.fillRect(x, y, barWidth - 20, barHeight);
            
            g2d.setColor(Color.BLACK);
            g2d.drawString(entry.getKey(), x, height - padding + 20);
            g2d.drawString(String.format("%.0f", entry.getValue()), x, y - 5);
            
            index++;
        }
    }
}
