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
    
    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Potencia: ").append(potencia).append("\n");
        sb.append("Valor de Exito: ").append(valorExito).append("\n");
        sb.append("Alcance: ").append(alcance).append("\n");
        return sb.toString();
    }
    
}
