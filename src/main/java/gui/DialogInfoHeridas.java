/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import com.mycompany.testrecuperacion.Zombie;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author nieto
 */
public class DialogInfoHeridas extends JDialog{
    public DialogInfoHeridas(Frame parent, Zombie zombie) {
        super(parent, "Heridas de Zombi " + zombie.getNombre(), true);

        // Crear texto de información del zombie
        JTextArea textoInfo = new JTextArea();
        textoInfo.append("Ha recibido heridas de :\n" );
        for(String nombre: zombie.getHeridasRecibidas()){
            textoInfo.append(nombre+"\n");
        }
        

        // Configurar el área de texto
        textoInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textoInfo);

        // Configuración del diálogo
        add(scrollPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        pack(); // Ajusta automáticamente el tamaño de la ventana
        setVisible(true);
    }
}
