/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

/**
 *
 * @author nieto
 */
public class Conejo implements Comestible {
    private String nombre;
    private int identificador;
    private Casilla casilla;

    public Conejo(String nombre, int identificador,Casilla casilla) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.casilla=casilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }
    
     
    @Override
    public void calmarHambreZombie(Zombie zombie){
        if(zombie.getHambre()>0){
            zombie.setHambre(zombie.getHambre()-1);
        }
    }

    
}
