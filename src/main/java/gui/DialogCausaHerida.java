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
public class DialogCausaHerida extends JDialog{
    private JComboBox<String> heridaComboBox;
    private JButton aceptarButton;
    private String result;

    public DialogCausaHerida(Frame owner) {
        super(owner, "Seleccionar Causa de Herida", true);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Causa de la Herida:"));
        String[] causas = {"Blindado", "Soldado", "Informatico", "Especialista", "Hambre"};
        heridaComboBox = new JComboBox<>(causas);
        add(heridaComboBox);

        aceptarButton = new JButton("Aceptar");
        add(aceptarButton);
        add(new JLabel()); // Placeholder for layout

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = (String) heridaComboBox.getSelectedItem();
                dispose();
            }
        });

        setSize(300, 100);
        setLocationRelativeTo(owner);
    }

    public String getResult() {
        return result;
    }
}
