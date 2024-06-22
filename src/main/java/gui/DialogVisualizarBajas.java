/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import com.mycompany.testrecuperacion.Zombie;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author nieto
 */
public class DialogVisualizarBajas extends JDialog{
    private ArrayList<Zombie> listaZombies;
    
    public DialogVisualizarBajas(JFrame parent, ArrayList<Zombie> listaZombies) {
        super(parent, "Seleccionar Zombi", true);
        this.listaZombies = listaZombies;
        
        // Crear el panel que contendrá los botones
        JPanel panelBotones = new JPanel(new GridLayout(listaZombies.size(), 1));
        
        // Crear y añadir botones para cada humano en la lista
        for (Zombie zombie : listaZombies) {
            JButton botonZombie = new JButton("" + zombie.getNombre());
            botonZombie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Acción que ocurre al presionar el botón
                    //ABRIR OTRO JDIALOG
                    new DialogInfoBajas(parent, zombie);
                    // Aquí puedes realizar la acción que desees para este humano
                    // Por ejemplo, abrir otra ventana, realizar alguna operación, etc.
                }
            });
            panelBotones.add(botonZombie);
        }
        
        // Configuración del diálogo
        add(panelBotones, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack();
        setVisible(true);
    }
}
