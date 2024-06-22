package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.*;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class DialogCargarPartida extends JDialog{

    private JFileChooser fileChooser;
    private File selectedFile;

    public DialogCargarPartida(JFrame parent) {

        super(parent, "Seleccionar Archivo", true); // true indica que es modal

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo seleccionar archivos, no directorios

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        } else {
            selectedFile = null;
        }
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public String getSelectedFilePath() {
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }
    
    
    
}

