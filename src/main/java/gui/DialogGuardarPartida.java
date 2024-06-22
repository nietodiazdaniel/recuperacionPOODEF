/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.testrecuperacion.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class DialogGuardarPartida extends JDialog {

    private Juego juego;

    public DialogGuardarPartida(JFrame parent, Juego juego) {
        super(parent, "Guardar Juego", true);
        this.juego = juego;

        // Mostrar el JFileChooser para seleccionar la ubicación del archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Especifica una ubicación para guardar el archivo");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            guardarJuego(juego, fileToSave.getAbsolutePath());
        }

        // Cerrar el diálogo
        dispose();
    }

    private void guardarJuego(Juego juego, String rutaArchivo) {
        try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
            fileWriter.write(juego.toText());
            juego.getPantallaJuego().agregarEvento("Juego guardado exitosamente en: " + rutaArchivo);
        } catch (IOException i) {
            juego.getPantallaJuego().agregarEvento("Error al guardar el juego: " + i.getMessage());
        }
    }
}
