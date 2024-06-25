/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author nieto
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numeroFilas = 0;
        int numeroColumnas = 0;
        int numeroConejos;
        String nombre;
        int identificador;;
        File selectedFile = new File("C:/Users/nieto/Documents/NetBeansProjects/recuPOO/src/main/java/registros/partida1");
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Filas:")) {
                    numeroFilas = Integer.parseInt(linea.split(":")[1].trim());
                } else if (linea.startsWith("Columnas:")) {
                    numeroColumnas = Integer.parseInt(linea.split(":")[1].trim());
                } else if (linea.startsWith("Numero de Conejos:")) {
                    numeroConejos = Integer.parseInt(linea.split(":")[1].trim());
                    linea = br.readLine();//LEE Lista de COnejos:
                    for (int i = 0; i < numeroConejos; i++) {
                        linea = br.readLine();
                        if (linea.startsWith("Nombre: ")) {
                            nombre = linea.substring("Nombre: ".length());
                            System.out.println("Nombre: " + nombre + "\n");
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Identificador: ")) {
                            identificador = Integer.parseInt(linea.split(":")[1].trim());
                            System.out.println("Identificador: " + identificador + "\n");
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Casilla: ")) {
                            int inicioCoordenada = linea.indexOf("(") + 1;
                            int finCoordenada = linea.indexOf(")");
                            String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                            String[] partesCoordenada = coordenadaStr.split(",");
                            int x = Integer.parseInt(partesCoordenada[0].trim());
                            int y = Integer.parseInt(partesCoordenada[1].trim());
                            Coordenada coordenada = new Coordenada(x, y);
                            System.out.println("Casilla: " + coordenada.toString() + "\n");
                        }
                        linea = br.readLine();
                    }
                }
            }
        } catch (IOException e) {
            //ERROR AL LEER ARCHIVO
        }
        System.out.println("F: " + numeroFilas + " C: " + numeroColumnas);
    }

}
