/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

/**
 *
 * @author nieto
 */
public class Dado {
    private static final int numCaras = 6;

    private Dado() {
        //Se deja vacio para evitar que otras clases creen instancias de esta
    }

    public static int tirarDado() {
        return (int) (Math.random() * numCaras) + 1;
    }
//prueba para ver si ya me deja
}
