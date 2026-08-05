/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FinverUI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Angel H
 */
public class CircleProgressBar extends javax.swing.JPanel {

    private int porcentaje = 0;

    public CircleProgressBar() {
        setPreferredSize(new Dimension(150, 150));
        setOpaque(false);
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        if (porcentaje < 0) porcentaje = 0;
        if (porcentaje > 100) porcentaje = 100;

        this.porcentaje = porcentaje;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int grosor = 12;
        int margen = 10;

        int ancho = getWidth() - margen * 2;
        int alto = getHeight() - margen * 2;

        // Fondo del anillo
        g2.setStroke(new BasicStroke(grosor));
        g2.setColor(new Color(220, 220, 220));
        g2.drawOval(margen, margen, ancho, alto);

        // Progreso
        g2.setColor(new Color(66, 133, 244));
        int angulo = (int) (360 * porcentaje / 100.0);
        g2.drawArc(margen, margen, ancho, alto, 90, -angulo);

        // Texto
        String texto = porcentaje + "%";
        g2.setFont(new Font("Arial", Font.BOLD, 22));

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(texto)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 5;

        g2.setColor(Color.BLACK);
        g2.drawString(texto, x, y);

        g2.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
