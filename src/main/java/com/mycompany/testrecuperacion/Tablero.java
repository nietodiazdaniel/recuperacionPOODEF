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
                throw new IllegalArgumentException("Numero no válido. Introduce un número del 1 al 4.");
        }

       
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

    

    public Casilla getCasilla(Coordenada coordenada) {
        return casillas[coordenada.getX()][coordenada.getY()];
    }

    public int getFilas() {
        return filas;
    }

    

    public int getColumnas() {
        return columnas;
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

    
    public String toText(){
        StringBuilder sb = new StringBuilder();
        sb.append("Filas: ").append(filas).append("\n");
        sb.append("Columnas: ").append(columnas).append("\n");
        return sb.toString();
    }

}
