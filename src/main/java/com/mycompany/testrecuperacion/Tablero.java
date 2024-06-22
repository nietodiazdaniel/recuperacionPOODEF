/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import static java.lang.Math.abs;

/**
 *
 * @author ramir
 */
public class Tablero {

    private Casilla[][] casillas;
    private int filas;
    private int columnas;
    private TableroGrafico tableroGrafico;

    public Tablero(Integer numJug) {
        switch (numJug) {
            case 1:
                inicializarTablero(7, 7);
                break;
            case 2:
                inicializarTablero(8, 8);
                break;
            case 3:
                inicializarTablero(9, 9);
                break;
            case 4:
                inicializarTablero(10, 10);
                break;
            default:
                throw new IllegalArgumentException("Número no válido. Introduce un número del 1 al 4.");
        }

       // tableroGrafico = new TableroGrafico(this, filas, columnas);
        //actualizarTableroGrafico();
    }

    private void inicializarTablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada cor = new Coordenada(i, j);
                casillas[i][j] = new Casilla(cor);
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String contenido = "";

                if (!casillas[i][j].getNumZombie().isEmpty()) {
                    for (Zombie zombie : casillas[i][j].getNumZombie()) {
                        contenido += "Z:" + zombie.getNombre() + " ";
                    }
                }

                if (!casillas[i][j].getNumConejos().isEmpty()) {
                    for (Conejo conejo : casillas[i][j].getNumConejos()) {
                        contenido += "C:" + conejo.getNombre() + " ";
                    }
                }

                if (!casillas[i][j].getNumHumano().isEmpty()) {
                    for (Humano humano : casillas[i][j].getNumHumano()) {
                        contenido += "H:" + humano.getClass().getSimpleName() + " ";
                    }
                }

                if (contenido.isEmpty()) {
                    System.out.print("[  ] ");
                } else {
                    System.out.print("[" + contenido.trim() + "] ");
                }
            }
            System.out.println();
        }
    }

    public Casilla getCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()];
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
    
    
    
    public int calcularDistancia(Casilla c1, Casilla c2){
        int disttotal = 0;
        if (!(c1.getCoordenada().getX() == c2.getCoordenada().getX())) {//SI SUS X NO SON IGUALES
            disttotal = disttotal + Math.abs(c1.getCoordenada().getX() - c2.getCoordenada().getX());//SUMA A 0 LA DISTANCIA DE X
        }
        if (!(c1.getCoordenada().getY() == c2.getCoordenada().getY())) {//SI SUS Y NO SON IGUALES
            disttotal = disttotal + Math.abs(c1.getCoordenada().getY() - c2.getCoordenada().getY());//SUMA A LO ANTERIOR LA DISTANCIA DE Y
        }
        
        return disttotal; 
    }

    public void actualizarTableroGrafico() {
        if (tableroGrafico != null) {
            tableroGrafico.actualizarTablero();
        }
    }

}
