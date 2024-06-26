/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.testrecuperacion.*;
import com.mycompany.testrecuperacion.Conejo;
import com.mycompany.testrecuperacion.Coordenada;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author nieto
 */
public class DialogCrearHumano extends JDialog {

    private JComboBox<String> tipoHumanoComboBox;
    private JTextField xField;
    private JTextField yField;
    private JButton crearButton;

    public DialogCrearHumano(JFrame owner, Juego juego) {
        super(owner, "Crear Humano", true);
        setLayout(new GridLayout(4, 2));

        // Tipo de Humano
        add(new JLabel("Tipo de Humano:"));
        String[] tiposHumanos = {"Huidizo", "Soldado", "Blindado", "Informatico", "Especialista"};
        tipoHumanoComboBox = new JComboBox<>(tiposHumanos);
        add(tipoHumanoComboBox);

        // Coordenada X
        add(new JLabel("X:"));
        xField = new JTextField();
        add(xField);

        // Coordenada Y
        add(new JLabel("Y:"));
        yField = new JTextField();
        add(yField);

        // Botón Crear
        crearButton = new JButton("Crear");
        add(crearButton);
        add(new JLabel()); // Placeholder for layout

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores ingresados
                    String tipoHumano = (String) tipoHumanoComboBox.getSelectedItem();
                    int x = Integer.parseInt(xField.getText());
                    int y = Integer.parseInt(yField.getText());

                    Casilla posicion = juego.getTablero().getCasilla(new Coordenada(x, y));
                    Humano humano = null;
                    switch (tipoHumano) {
                        case "Huidizo":
                            humano = new Huidizo(posicion);
                            break;
                        case "Soldado":
                            humano = new Soldado(posicion);
                            break;
                        case "Blindado":
                            humano = new Blindado(posicion);
                            break;
                        case "Informatico":
                            humano = new Informatico(posicion);
                            break;
                        case "Especialista":
                            humano = new Especialista(posicion);
                            break;
                    }
                    juego.getPantallaJuego().agregarEvento("Ha aparecido un Humano " + humano.getClass().getSimpleName() + " en la posicion " + humano.getCasilla().getCoordenada().toString());
                    juego.getListaHumanos().add(humano);
                    juego.getTablero().getCasilla(new Coordenada(x, y)).getNumHumano().add(humano);
                    SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
                    dispose(); // Cerrar el diálogo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DialogCrearHumano.this,
                            "Por favor, introduce valores válidos.",
                            "Error de entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(owner);
        setVisible(true);

    }
}

