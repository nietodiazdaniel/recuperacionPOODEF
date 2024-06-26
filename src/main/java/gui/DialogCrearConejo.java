/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.testrecuperacion.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class DialogCrearConejo extends JDialog {

    private JTextField xField;
    private JTextField yField;
    private JTextField nombreField;
    private JTextField identificadorField;
    private JButton crearButton;

    public DialogCrearConejo(JFrame owner, Juego juego) {
        super(owner, "Crear Conejo", true);
        setLayout(new GridLayout(5, 2));
        add(new JLabel("X:"));
        xField = new JTextField();
        add(xField);

        add(new JLabel("Y:"));
        yField = new JTextField();
        add(yField);

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Identificador:"));
        identificadorField = new JTextField();
        add(identificadorField);

        crearButton = new JButton("Crear");
        add(crearButton);
        add(new JLabel()); // Placeholder for layout

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int x = Integer.parseInt(xField.getText());
                    int y = Integer.parseInt(yField.getText());
                    String nombre = nombreField.getText();
                    int identificador = Integer.parseInt(identificadorField.getText());

                    Casilla casillaConejo = juego.getTablero().getCasilla(new Coordenada(x, y));
                    Conejo nuevoConejo = new Conejo(nombre, identificador, casillaConejo);
                    ArrayList<Conejo> conejosEnCasilla = juego.getTablero().getCasilla(nuevoConejo.getCasilla().getCoordenada()).getNumConejos();
                    conejosEnCasilla.add(nuevoConejo);
                    juego.getTablero().getCasilla(nuevoConejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
                    juego.getListaConejos().add(nuevoConejo);
                    juego.getPantallaJuego().agregarEvento("Ha aparecido un Conejo en la coordenada " + nuevoConejo.getCasilla().getCoordenada().toString());
                    SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
                    dispose(); // Cerrar el diálogo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DialogCrearConejo.this,
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
