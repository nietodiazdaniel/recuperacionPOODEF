/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class PanelMoverse extends JPanel{
    private String direccion = null;
    public PanelMoverse(){
        setLayout(new GridLayout(2, 2, 10, 10)); // GridLayout de 2 filas y 2 columnas con espacios de 10 píxeles

        JButton botonArriba = new JButton("Arriba");
        botonArriba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direccion="Arriba";
            }
        });
        add(botonArriba);

        JButton botonAbajo = new JButton("Abajo");
        botonAbajo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direccion="Abajo";
            }
        });
        add(botonAbajo);

        JButton botonDerecha = new JButton("Derecha");
        botonDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direccion="Derecha";
            }
        });
        add(botonDerecha);

        JButton botonIzquierda = new JButton("Izquierda");
        botonIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                direccion="Izquierda";
            }
        });
        add(botonIzquierda);
    }

    public String getDireccion() {
        return direccion;
    }
    
}
