/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

/**
 *
 * @author nieto
 */
public abstract class Ataque {
    private String nombre;
    private int potencia;
    private int valorExito;
    private int alcance;
   
    public Ataque(String nombre, int potencia, int valorExito, int alcance) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.valorExito = valorExito;
        this.alcance = alcance;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getValorExito() {
        return valorExito;
    }

    public int getAlcance() {
        return alcance;
    }
    public abstract void realizarAtaque (Zombie zombie,Casilla objetivo, Juego juego);
    
}
