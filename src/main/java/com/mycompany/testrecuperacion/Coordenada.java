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
    
    
    public Coordenada(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    

    public int getY() {
        return y;
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
    
    

    @Override
    public String toString() {
        return "{" + "x=" + x + ", y=" + y + '}';
    }
    public String toText() {
        return "(" + x + ", " + y + ")";
    }
}
