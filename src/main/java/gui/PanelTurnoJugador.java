/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class PanelTurnoJugador extends JPanel {

    private String accion = null;

    public PanelTurnoJugador(String nombre, int numAcciones, int alcance) {
        setLayout(new GridLayout(8, 1));
        JLabel turnoLabel = new JLabel("Turno: " + nombre, SwingConstants.CENTER);
        Font font = turnoLabel.getFont(); // Obtener la fuente actual
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()); // Crear una nueva fuente en negrita
        turnoLabel.setFont(boldFont); // Establecer la fuente en negrita
        add(turnoLabel);

        // Etiqueta de número de acciones realizadas
        JLabel numAccionesLabel = new JLabel("Número de acciones realizadas: " + numAcciones, SwingConstants.CENTER);
        add(numAccionesLabel);

        // Etiqueta inicial
        JLabel label = new JLabel("Elige una acción", SwingConstants.CENTER);
        add(label);

        // Botones de acciones
        JButton devorarButton = new JButton("Devorar (Alcance 0)");
        add(devorarButton);

        JButton ataqueEspecialButton = new JButton("Ataque Especial (" + alcance + ")");
        add(ataqueEspecialButton);

        JButton moverseButton = new JButton("Moverse");
        add(moverseButton);

        JButton buscarComidaButton = new JButton("Buscar Comida");
        add(buscarComidaButton);

        JButton noHacerNadaButton = new JButton("No Hacer Nada");
        add(noHacerNadaButton);

        devorarButton.addActionListener(e -> {
            accion = "Devorar";
            //setVisible(false);
        });
        ataqueEspecialButton.addActionListener(e -> {
            accion = "Ataque Especial";
            setVisible(false);
        });
        moverseButton.addActionListener(e -> {
            accion = "Moverse";
            setVisible(false);
        });
        buscarComidaButton.addActionListener(e -> {
            accion = "Buscar Comida";
            setVisible(false);
        });

        noHacerNadaButton.addActionListener(e -> {
            accion = "No Hacer Nada";
            setVisible(false);
        });
    }

    public String getAccion() {
        return accion;
    }
}
