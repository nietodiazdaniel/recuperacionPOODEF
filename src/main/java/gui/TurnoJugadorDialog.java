/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nieto
 */
public class TurnoJugadorDialog extends JDialog {

    private String accion;
    private String coordenadaX;
    private String coordenadaY;
    private String direccion;

    public TurnoJugadorDialog(Frame parent, int numeroAcciones, int alcanceAtaqueEspecial) {
        super(parent, "Turno del Jugador", true);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(6, 1));
        add(mainPanel, BorderLayout.CENTER);

        JLabel numAccionesLabel = new JLabel("Número de acciones realizadas: " + numeroAcciones, SwingConstants.CENTER);
        mainPanel.add(numAccionesLabel);

        // Etiqueta inicial
        JLabel label = new JLabel("Elige una acción", SwingConstants.CENTER);
        mainPanel.add(label);

        // Botones de acciones
        JButton devorarButton = new JButton("Devorar (Alcance 0)");
        JButton ataqueEspecialButton = new JButton("Ataque Especial (" + alcanceAtaqueEspecial + ")");
        JButton moverseButton = new JButton("Moverse");
        JButton buscarComidaButton = new JButton("Buscar Comida");
        JButton noHacerNadaButton = new JButton("No Hacer Nada");

        mainPanel.add(devorarButton);
        mainPanel.add(ataqueEspecialButton);
        mainPanel.add(moverseButton);
        mainPanel.add(buscarComidaButton);
        mainPanel.add(noHacerNadaButton);

        // Panel dinámico
        JPanel dynamicPanel = new JPanel();
        add(dynamicPanel, BorderLayout.SOUTH);

        // Listeners para los botones
        devorarButton.addActionListener(e -> {
            accion = "Devorar";
            setVisible(false);
        });

        ataqueEspecialButton.addActionListener(e -> {
            mainPanel.setVisible(false);
            dynamicPanel.removeAll();
            dynamicPanel.setLayout(new GridLayout(3, 2));
            dynamicPanel.add(new JLabel("Coordenada X:"));
            JTextField coordXField = new JTextField();
            dynamicPanel.add(coordXField);
            dynamicPanel.add(new JLabel("Coordenada Y:"));
            JTextField coordYField = new JTextField();
            dynamicPanel.add(coordYField);
            JButton atacarButton = new JButton("Atacar");
            atacarButton.addActionListener(event -> {
                accion = "Ataque Especial";
                coordenadaX = coordXField.getText();
                coordenadaY = coordYField.getText();
                setVisible(false);
            });
            dynamicPanel.add(atacarButton);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        });

        moverseButton.addActionListener(e -> {
            mainPanel.setVisible(false);
            dynamicPanel.removeAll();
            dynamicPanel.setLayout(new GridLayout(4, 1));
            dynamicPanel.add(new JLabel("Elige la dirección"));
            JButton arribaButton = new JButton("Arriba");
            JButton abajoButton = new JButton("Abajo");
            JButton izquierdaButton = new JButton("Izquierda");
            JButton derechaButton = new JButton("Derecha");

            arribaButton.addActionListener(event -> {
                accion = "Moverse";
                direccion = "Arriba";
                setVisible(false);
            });
            abajoButton.addActionListener(event -> {
                accion = "Moverse";
                direccion = "Abajo";
                setVisible(false);
            });
            izquierdaButton.addActionListener(event -> {
                accion = "Moverse";
                direccion = "Izquierda";
                setVisible(false);
            });
            derechaButton.addActionListener(event -> {
                accion = "Moverse";
                direccion = "Derecha";
                setVisible(false);
            });

            dynamicPanel.add(arribaButton);
            dynamicPanel.add(abajoButton);
            dynamicPanel.add(izquierdaButton);
            dynamicPanel.add(derechaButton);
            dynamicPanel.revalidate();
            dynamicPanel.repaint();
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

    public int getCoordenadaX() {
        if (coordenadaX == null || coordenadaX.isEmpty()) {
            return -1; // O manejar el caso de valor nulo según tu lógica de aplicación
        }

        return Integer.parseInt(coordenadaX);

    }

    public int getCoordenadaY() {
        if (coordenadaY == null || coordenadaY.isEmpty()) {
            return -1; // O manejar el caso de valor nulo según tu lógica de aplicación
        }

        return Integer.parseInt(coordenadaY);

    }

    public String getDireccion() {
        return direccion;
    }

    
}
