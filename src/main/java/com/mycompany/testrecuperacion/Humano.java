/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.Random;

/**
 *
 * @author delac
 */
public abstract class Humano implements Comestible, Activable {

    private int num_activaciones;
    private int aguante;
    private Casilla casilla;

    public Humano(int num_activaciones, int aguante, Casilla casilla) {
        this.num_activaciones = num_activaciones;
        this.aguante = aguante;
        this.casilla = casilla;
    }

    public int getNum_activaciones() {
        return num_activaciones;
    }


    public int getAguante() {
        return aguante;
    }

   
    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public static Humano aparicion(Casilla casilla) {
        Humano humano;
        Random random = new Random();
        int numeroAleatorio = random.nextInt(99) + 1;
        if (numeroAleatorio <= 60) {
            int numeroAleatorio2 = random.nextInt(100) + 1;
            if (numeroAleatorio2 <= 60) {
                humano = new Blindado(casilla);
            } else if (numeroAleatorio2 > 60 && numeroAleatorio2 <= 90) {
                humano = new Especialista(casilla);
            } else {
                humano = new Soldado(casilla);
            }
        } else if ((numeroAleatorio > 60) && (numeroAleatorio <= 85)) {
            humano = new Huidizo(casilla);
        } else {
            humano = new Informatico(casilla);
        }
        return humano;
    }

    public Coordenada zombieMasCercano(Tablero tablero, Juego juego) {
        Coordenada coormascerca = null;
        int distanciaMinima = 300;

        for (int i = 0; i < juego.getNumJug(); i++) {
            if (juego.getListaJugadores().get(i).getEstado().equals("ACTIVO") && !juego.getListaJugadores().get(i).getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas()-1,tablero.getColumnas()-1))) {
                Coordenada coordZombie = juego.getListaJugadores().get(i).getCasilla().getCoordenada();
                int distancia = tablero.calcularDistancia(this.getCasilla(), juego.getListaJugadores().get(i).getCasilla());

                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    coormascerca = coordZombie;
                }
            }
        }

        return coormascerca;
    }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Número de Activaciones: ").append(num_activaciones).append("\n");
        sb.append("Aguante: ").append(aguante).append("\n");
        sb.append("Casilla: ").append(casilla.getCoordenada().toText());
        return sb.toString();
    }
}
