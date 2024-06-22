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
public class DialogInfoZombie extends JDialog{
    public DialogInfoZombie(Frame parent, Zombie zombie) {
        super(parent, "Información de Zombi " + zombie.getNombre(), true);

        // Crear texto de información del zombie
        JTextArea textoInfo = new JTextArea();
        textoInfo.append("Nombre: " + zombie.getNombre() + "\n");
        textoInfo.append("Estado: " + zombie.getEstado() + "\n");
        textoInfo.append("Número de Acciones Realizadas en este Turno: " + zombie.getNumAcciones() + "\n");
        textoInfo.append("Número de Heridas Recibidas: " + zombie.getNumHeridas() + "\n");
        textoInfo.append("Devorar: Potencia= 1  Valor de Exito= 4  Alcance=0\n");
        textoInfo.append("Ataque Especial:  Nombre= "+zombie.getAtaqueEspecial().getNombre()+"  Potencia= "+zombie.getAtaqueEspecial().getPotencia()+"  Valor de Exito= "+zombie.getAtaqueEspecial().getValorExito()+"  Alcance= "+zombie.getAtaqueEspecial().getAlcance()+"\n");
        textoInfo.append("Hambre: " + zombie.getHambre() + "\n");
        textoInfo.append("Coordenada de la Casilla: " + zombie.getCasilla().getCoordenada().toString() + "\n");

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
