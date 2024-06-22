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
public class Coordenada {
    private int x;
    private int y;
    private ArrayList<Humano> humanosCoordenada=new ArrayList<>();//ESTO NO HACE FALTA PER NO QUITARLO DE MOMENTO
    private ArrayList<Zombie> zombiesCoordenada= new ArrayList<>();//ESTO NO HACE FALTA PER NO QUITARLO DE MOMENTO
    //Deberiamos añadir un atributo Casilla, siendo la casilla a la que pertenece?
    
    public Coordenada(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public boolean equals (Object o){
        if (this==o){
            return true;
        }
        if (o ==null || getClass()!=o.getClass()){
        return false;
        }
        Coordenada otraCoordenada= (Coordenada) o;
        return (x==otraCoordenada.getX() && y ==otraCoordenada.getY());
    }
    public Coordenada copia(){
        return new Coordenada(this.x,this.y);
    }
    
    //FALTARIA AÑADIR SI ESO SETYGET DE ZOMBIES Y SUPERVIVIENTES CND LOS TENGAMOS HECHOS

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + '}';
    }
}
