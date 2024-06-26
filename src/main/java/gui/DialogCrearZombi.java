/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.testrecuperacion.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nieto
 */
public class DialogCrearZombi extends JDialog {

    private JTextField nombreField;
    private JComboBox<Integer> numAccionesComboBox;
    private JComboBox<Integer> numHeridasComboBox;
    private JComboBox<Integer> hambreComboBox;
    private JTextField xField;
    private JTextField yField;
    private JTextField nombreAtaqueField;
    private JTextField alcanceField;
    private JTextField potenciaField;
    private JTextField valorExitoField;
    private JButton crearButton;

    public DialogCrearZombi(Frame owner, Juego juego) {
        super(owner, "Crear Zombi", true);
        setLayout(new GridLayout(11, 2));

        // Campos para datos del Zombi
        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Num Acciones:"));
        Integer[] acciones = {0, 1, 2, 3};
        numAccionesComboBox = new JComboBox<>(acciones);
        add(numAccionesComboBox);

        add(new JLabel("Num Heridas:"));
        Integer[] heridas = {0, 1, 2, 3, 4, 5};
        numHeridasComboBox = new JComboBox<>(heridas);
        add(numHeridasComboBox);

        add(new JLabel("Hambre:"));
        Integer[] nivelHambre = {0, 1, 2, 3, 4, 5};
        hambreComboBox = new JComboBox<>(nivelHambre);
        add(hambreComboBox);

        // Campos para coordenada
        add(new JLabel("X:"));
        xField = new JTextField();
        add(xField);

        add(new JLabel("Y:"));
        yField = new JTextField();
        add(yField);

        // Campos para datos del ataque
        add(new JLabel("Nombre de Ataque:"));
        nombreAtaqueField = new JTextField();
        add(nombreAtaqueField);

        add(new JLabel("Alcance:"));
        alcanceField = new JTextField();
        add(alcanceField);

        add(new JLabel("Potencia:"));
        potenciaField = new JTextField();
        add(potenciaField);

        add(new JLabel("Valor de Exito:"));
        valorExitoField = new JTextField();
        add(valorExitoField);

        // Botón Crear
        crearButton = new JButton("Crear");
        add(crearButton);
        add(new JLabel()); 

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores ingresados
                    String nombre = nombreField.getText();
                    int numAcciones = (Integer) numAccionesComboBox.getSelectedItem();
                    int numHeridas = (Integer) numHeridasComboBox.getSelectedItem();
                    int hambre = (Integer) hambreComboBox.getSelectedItem();
                    int x = Integer.parseInt(xField.getText());
                    int y = Integer.parseInt(yField.getText());
                    String nombreAtaque = nombreAtaqueField.getText();
                    int alcance = Integer.parseInt(alcanceField.getText());
                    int potencia = Integer.parseInt(potenciaField.getText());
                    int valorExito = Integer.parseInt(valorExitoField.getText());

                    Casilla casillaZombi = juego.getTablero().getCasilla(new Coordenada(x, y));
                    Zombie zombi = new Zombie(nombre, "ACTIVO", numHeridas, hambre, casillaZombi);
                    zombi.setNumAcciones(numAcciones);

                    AtaqueEspecial ataque = new AtaqueEspecial(nombreAtaque, potencia, valorExito, alcance);

                    if (numHeridas > 0) {
                        for (int i = 0; i < numHeridas; i++) {
                            DialogCausaHerida heridaDialog = new DialogCausaHerida(owner);
                            heridaDialog.setVisible(true);
                            String causaHerida = heridaDialog.getResult();
                            zombi.agregarHerida(causaHerida);
                        }
                    }

                    juego.getTablero().getCasilla(new Coordenada(x,y)).getNumZombie().add(zombi);
                    juego.getTablero().getCasilla(new Coordenada(x,y)).setNumZombie(juego.getTablero().getCasilla(new Coordenada(x,y)).getNumZombie());
                    juego.getListaJugadores().add(zombi);
                    juego.setNumJug(juego.getNumJug()+1);
                    SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
                    
                    dispose(); // Cerrar el diálogo
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DialogCrearZombi.this,
                            "Por favor, introduce valores válidos.",
                            "Error de entrada",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        setSize(500, 600);
        setLocationRelativeTo(owner);
        setVisible(true);
    }
}
