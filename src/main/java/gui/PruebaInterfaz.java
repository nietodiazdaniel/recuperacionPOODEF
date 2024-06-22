/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.testrecuperacion.Tablero;
import com.mycompany.testrecuperacion.Conejo;
import com.mycompany.testrecuperacion.Zombie;
import com.mycompany.testrecuperacion.Casilla;
import com.mycompany.testrecuperacion.Coordenada;
import com.mycompany.testrecuperacion.Humano;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class PruebaInterfaz extends JFrame {

    private JButton[][] botones;
    private int size;
    private JTextArea eventTextArea;
    private JPanel panelControles;

    public PruebaInterfaz(int numeroJugadores) {
        switch (numeroJugadores) {
            case 1:
                size = 7;
                break;
            case 2:
                size = 8;
                break;
            case 3:
                size = 9;
                break;
            case 4:
                size = 10;
                break;
            default:
                throw new IllegalArgumentException("Atributo debe estar entre 1 y 4");
        }
        //************************** JFRAME******************************************************************************************
        // Configurar el JFrame
        setTitle("FESTINFORMATICO ZOMBI");
        setSize(1500, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/images.png")).getImage());
        setLayout(new BorderLayout()); // Establecer el layout del JFrame
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        //************************** JFRAME******************************************************************************************

        //************************** BARRA MENU SUPERIOR******************************************************************************************
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(1500, 50));
        setJMenuBar(menuBar);

        JMenu menuArchivo = new javax.swing.JMenu();
        JMenuItem itemGuardar = new javax.swing.JMenuItem();
        JMenuItem itemSalir = new javax.swing.JMenuItem();

        menuArchivo.setText("Archivo");

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloGuardar.png"))); // NOI18N
        itemGuardar.setText("Guardar");
        itemGuardar.setPreferredSize(new java.awt.Dimension(141, 32));
        /*itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });*/
        menuArchivo.add(itemGuardar);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloSalir.png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.setPreferredSize(new java.awt.Dimension(121, 32));
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar.add(menuArchivo);

        //************************** BARRA MENU SUPERIOR******************************************************************************************
        //************************** PANEL TABLERO******************************************************************************************
        JPanel panelTablero = new JPanel(new GridLayout(size, size));
        // Calcular el tamaño preferido del panel basado en el tamaño del tablero
        int panelTamano = getHeight() - menuBar.getHeight(); // Deseamos que el panel ocupe toda la altura del JFrame (1280x720)
        panelTablero.setPreferredSize(new Dimension(panelTamano, panelTamano));

        // Añadir los botones al panel
        botones = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botones[i][j] = new JButton();
                panelTablero.add(botones[i][j]);
            }
        }

        // Añadir el panel al JFrame en la posición deseada
        add(panelTablero, BorderLayout.WEST);
        //************************** PANEL TABLERO******************************************************************************************

        //************************** PANEL DERECHO ( CONTROLES Y TEXTO ) ******************************************************************************************
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
        //************************** PANEL CONTROLES ******************************************************************************************
        panelControles = new JPanel(new GridLayout(1, 1));
        //AQUI DEBEMOS HACER DESDE FUERA EL SET PARA ESTABLECER LA PANTALLA QUE QUEREMOS
        //panelControles.add(new PanelMoverse());
        

      

        panelDerecho.add(panelControles);
        //************************** PANEL CONTROLES ******************************************************************************************

        //************************** PANEL TEXTO ******************************************************************************************
        JPanel panelTexto = new JPanel(new BorderLayout());
        eventTextArea = new JTextArea();
        eventTextArea.setEditable(false);
        eventTextArea.setLineWrap(true);
        eventTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(eventTextArea);
        panelTexto.add(scrollPane, BorderLayout.CENTER);
        panelTexto.setBorder(BorderFactory.createTitledBorder("Registro de Eventos"));
        panelDerecho.add(panelTexto);
        //************************** PANEL TEXTO ******************************************************************************************

        add(panelDerecho, BorderLayout.CENTER);
        //************************** PANEL DERECHO ( CONTROLES Y TEXTO ) ******************************************************************************************
    }

    public void actualizarTablero(Tablero tablero) {
        Font fontSmall = new Font("Arial", Font.PLAIN, 10); // Aquí ajusta el tamaño de 12 según sea necesario
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Casilla casilla = tablero.getCasilla(new Coordenada(i, j));
                //String texto = "";
                StringBuilder texto = new StringBuilder("<html>");

                // Añadir información sobre zombies
                if (!casilla.getNumZombie().isEmpty()) {
                    for (Zombie zombie : casilla.getNumZombie()) {
                        //texto += "Z:" + zombie.getNombre() + " ";
                        texto.append("Z:").append(zombie.getNombre()).append("<br>");
                    }
                }
                // Añadir información sobre humanos
                if (!casilla.getNumHumano().isEmpty()) {
                    for (Humano humano : casilla.getNumHumano()) {
                        //texto += "H:" + humano.getClass().getSimpleName() + " ";
                        texto.append("H:").append(humano.getClass().getSimpleName()).append("<br>");
                    }
                }
                // Añadir información sobre conejos
                if (!casilla.getNumConejos().isEmpty()) {
                    for (Conejo conejo : casilla.getNumConejos()) {
                        //texto += "C:" + conejo.getNombre() + " ";
                        texto.append("C:").append(conejo.getNombre()).append("<br>");
                    }
                }
                texto.append("</html>");//NO ESTABA
                // Actualizar el texto del botón
                botones[i][j].setText(texto.toString());//sin toString
                botones[i][j].setFont(fontSmall);

            }
        }
        botones[tablero.getFilas() - 1][tablero.getColumnas() - 1].setText("<html>OBJETIVO</html>");
    }

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        //AdvertenciaSalir advert = new AdvertenciaSalir(this, true);
        //advert.setLocationRelativeTo(this);
        //advert.setVisible(true);
        AdvertenciaSalir dialog = new AdvertenciaSalir(null, true); // Crear instancia del JDialog
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true); // Mostrar el JDialog de manera modal

        // Después de cerrar el JDialog, verificar la opción seleccionada
        if (dialog.getOpcionSeleccionada() == 1) {
            System.exit(0); // Salir del programa
        }
    }

    public void agregarEvento(String evento) {
        eventTextArea.append(evento + "\n");
        eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
    }

    public void setPanelControles(javax.swing.JPanel panel) {
        panelControles.removeAll(); 
        panelControles.add(panel);
        panelControles.revalidate();
        panelControles.repaint();
    }

    

    
}
