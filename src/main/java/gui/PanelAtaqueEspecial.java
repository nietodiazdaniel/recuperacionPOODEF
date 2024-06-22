/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class PanelAtaqueEspecial extends JPanel{
    private int coordX = -1;
    private int coordY = -1;
    public PanelAtaqueEspecial(){
        setLayout(new GridLayout(3, 2, 10, 10)); // GridLayout con 3 filas y 2 columnas

        JLabel etiquetaX = new JLabel("Coordenada X:");
        add(etiquetaX);

        JTextField campoX = new JTextField();
        add(campoX);

        JLabel etiquetaY = new JLabel("Coordenada Y:");
        add(etiquetaY);

        JTextField campoY = new JTextField();
        add(campoY);

        JButton botonAtacar = new JButton("Atacar");
        botonAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las coordenadas ingresadas
                try {
                    coordX = Integer.parseInt(campoX.getText());
                    coordY = Integer.parseInt(campoY.getText());
                    
                    // Aquí se realizaría la lógica para el ataque con las coordenadas (x, y)
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelAtaqueEspecial.this, "Por favor ingresa números válidos para X y Y", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(botonAtacar);
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
    
}
