/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.Random;

/**
 *
 * @author nieto
 */
public class Conejo implements Comestible {
    private String nombre;
    private int identificador;
    private Casilla casilla;
    private static int contadorId=0;
    
    private static final String[] NOMBRES_CONEJOS = {
        "Conejo Saltarin",
        "Brincos",
        "Orejas Largas",
        "Pelusa",
        "Colita Blanca",
        "Zanahoria"
    };
     
     
    public Conejo(Casilla casilla) {
        this.nombre = NOMBRES_CONEJOS[new Random().nextInt(NOMBRES_CONEJOS.length)];
        this.identificador = ++contadorId;
        this.casilla=casilla;
    }
    public Conejo(String nombre,int identificador,Casilla casilla) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.casilla=casilla;
    }

    public String getNombre() {
        return nombre;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    
     
    @Override
    public void calmarHambreZombie(Zombie zombie){
        if(zombie.getHambre()>0){
            zombie.setHambre(zombie.getHambre()-1);
        }
    }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Identificador: ").append(identificador).append("\n");
        sb.append("Casilla: ").append(casilla.getCoordenada().toText());
        return sb.toString();
    }
    
}
