/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import com.mycompany.testrecuperacion.Humano;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author nieto
 */
public class DialogInfoHumano extends JDialog{
    public DialogInfoHumano(Frame parent, Humano humano) {
        super(parent, "Información de Humano " + humano.getClass().getSimpleName(), true);

        // Crear texto de información del humano
        JTextArea textoInfo = new JTextArea();
        textoInfo.append("Número de Activaciones: " + humano.getNum_activaciones() + "\n");
        textoInfo.append("Aguante: " + humano.getAguante() + "\n");
        textoInfo.append("Coordenada de la Casilla: " + humano.getCasilla().getCoordenada().toString() + "\n");

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
