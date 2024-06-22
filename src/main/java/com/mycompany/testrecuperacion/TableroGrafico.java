/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.abs;

/**
 *
 * @author delac
 */

public class TableroGrafico extends JFrame {
    private Casilla[][] casillas;
    private int filas;
    private int columnas;
    private JButton[][] botones;
    private Tablero tablero;

    public TableroGrafico(Tablero tablero, int filas, int columnas) {
        super("Tablero Gráfico");
        this.tablero = tablero;
        this.filas = filas;
        this.columnas = columnas;
        
        setLayout(new GridLayout(filas, columnas));
        botones = new JButton[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(50, 50));
                final int x = i;
                final int y = j;
                botones[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mostrarInformacionCasilla(x, y);
                    }
                });
                add(botones[i][j]);
            }
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actualizarTablero() {
        casillas = tablero.getCasillas();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String contenido = "";

                if (!casillas[i][j].getNumZombie().isEmpty()) {
                    for (Zombie zombie : casillas[i][j].getNumZombie()) {
                        contenido += "Z:" + zombie.getNombre() + " ";
                    }
                }

                if (!casillas[i][j].getNumConejos().isEmpty()) {
                    for (Conejo conejo : casillas[i][j].getNumConejos()) {
                        contenido += "C:" + conejo.getNombre() + " ";
                    }
                }

                if (!casillas[i][j].getNumHumano().isEmpty()) {
                    for (Humano humano : casillas[i][j].getNumHumano()) {
                        contenido += "H:" + humano.getClass().getSimpleName() + " ";
                    }
                }

                botones[i][j].setText(contenido.trim());
            }
        }
    }

    private void mostrarInformacionCasilla(int x, int y) {
        Casilla casilla = casillas[x][y];
        StringBuilder info = new StringBuilder();

        if (!casilla.getNumZombie().isEmpty()) {
            info.append("Zombis:\n");
            for (Zombie zombie : casilla.getNumZombie()) {
                info.append(zombie.getNombre()).append("\n");
            }
        }

        if (!casilla.getNumConejos().isEmpty()) {
            info.append("Conejos:\n");
            for (Conejo conejo : casilla.getNumConejos()) {
                info.append(conejo.getNombre()).append("\n");
            }
        }

        if (!casilla.getNumHumano().isEmpty()) {
            info.append("Humanos:\n");
            for (Humano humano : casilla.getNumHumano()) {
                info.append(humano.getClass().getSimpleName()).append("\n");
            }
        }

        if (info.length() == 0) {
            info.append("La casilla está vacía.");
        }

        JOptionPane.showMessageDialog(this, info.toString(), "Información de la Casilla", JOptionPane.INFORMATION_MESSAGE);
    }

    
}
