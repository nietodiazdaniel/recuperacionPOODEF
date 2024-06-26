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
public class DialogCrearAtaque extends JDialog {

    private JTextField nombreField;
    private JTextField alcanceField;
    private JTextField potenciaField;
    private JTextField valorExitoField;
    private JComboBox<String> objetivoComboBox;
    private JButton crearButton;

    public DialogCrearAtaque(JFrame owner, Juego juego) {
        super(owner, "Crear Ataque", true);
        setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas, 10px de espacio entre componentes

        // Campos para datos del Ataque
        add(new JLabel("Nombre de Ataque:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Alcance:"));
        alcanceField = new JTextField();
        add(alcanceField);

        add(new JLabel("Potencia:"));
        potenciaField = new JTextField();
        add(potenciaField);

        add(new JLabel("Valor de Éxito:"));
        valorExitoField = new JTextField();
        add(valorExitoField);

        ArrayList<String> nombres = new ArrayList<>();
        for (Zombie zombie : juego.getListaJugadores()) {
            nombres.add(zombie.getNombre());
        }
        // ComboBox para seleccionar el objetivo
        add(new JLabel("Objetivo:"));
        objetivoComboBox = new JComboBox<>(nombres.toArray(new String[0]));
        add(objetivoComboBox);

        // Botón Crear
        crearButton = new JButton("Crear");
        add(crearButton);
        add(new JLabel()); // Placeholder for layout

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores ingresados
                    String nombre = nombreField.getText();
                    int alcance = Integer.parseInt(alcanceField.getText());
                    int potencia = Integer.parseInt(potenciaField.getText());
                    int valorExito = Integer.parseInt(valorExitoField.getText());
                    String objetivo = (String) objetivoComboBox.getSelectedItem();

                    Ataque ataque = new AtaqueEspecial(nombre, potencia, valorExito, alcance);
                    int indice = -1;
                    for (int i = 0; i < juego.getListaJugadores().size(); i++) {
                        if (juego.getListaJugadores().get(i).getNombre().equals(objetivo)) {
                            indice = i;
                            break;
                        }

                    }
                    if (indice != -1) {
                        juego.getListaJugadores().get(indice).setAtaqueEspecial(ataque);

                    } 
                    dispose(); // Cerrar el diálogo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DialogCrearAtaque.this,
                            "Por favor, introduce valores válidos.",
                            "Error de entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(owner);
        setVisible(true);
    }
}
