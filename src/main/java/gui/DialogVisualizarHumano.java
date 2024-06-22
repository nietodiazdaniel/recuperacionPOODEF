/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import com.mycompany.testrecuperacion.Humano;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author nieto
 */
public class DialogVisualizarHumano extends JDialog{
    private ArrayList<Humano> listaHumanos;
    
    public DialogVisualizarHumano(JFrame parent, ArrayList<Humano> listaHumanos) {
        super(parent, "Seleccionar Humano", true);
        this.listaHumanos = listaHumanos;
        
        // Crear el panel que contendrá los botones
        JPanel panelBotones = new JPanel(new GridLayout(listaHumanos.size(), 1));
        
        // Crear y añadir botones para cada humano en la lista
        for (Humano humano : listaHumanos) {
            JButton botonHumano = new JButton("" + humano.getClass().getSimpleName());
            botonHumano.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Acción que ocurre al presionar el botón
                    //ABRIR OTRO JDIALOG
                    new DialogInfoHumano(parent, humano);
                    // Aquí puedes realizar la acción que desees para este humano
                    // Por ejemplo, abrir otra ventana, realizar alguna operación, etc.
                }
            });
            panelBotones.add(botonHumano);
        }
        
        // Configuración del diálogo
        add(panelBotones, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }
}
