/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.ArrayList;

/**
 *
 * @author nieto
 */
public class Casilla {
    private Coordenada coordenada;
    private ArrayList<Zombie> numZombie=new ArrayList<>();
    private ArrayList<Humano> numHumano=new ArrayList<>();
    private ArrayList<Conejo> numConejos = new ArrayList<>();

    public Casilla(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getCoordenada(){
        return this.coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
    public ArrayList<Zombie> getNumZombie() {
        return numZombie;
    }

    public void setNumZombie(ArrayList<Zombie> numZombie) {
        this.numZombie = numZombie;
    }

    public ArrayList<Humano> getNumHumano() {
        return numHumano;
    }

    public void setNumHumano(ArrayList<Humano> numHumano) {
        this.numHumano = numHumano;
    }

    public ArrayList<Conejo> getNumConejos() {
        return numConejos;
    }

    public void setNumConejos(ArrayList<Conejo> numConejos) {
        this.numConejos = numConejos;
    }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coordenada: ").append(coordenada.toText()).append("\n");
        return sb.toString();
    }
}
