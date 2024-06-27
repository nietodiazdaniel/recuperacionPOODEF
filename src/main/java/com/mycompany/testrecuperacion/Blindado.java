/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author delac
 */
public class Blindado extends HumanoCombatiente {

    public Blindado(Casilla casilla) {
        super(1,2,casilla);
    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion, Juego juego){
        Casilla nueva;
        if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) <= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() > posicion.getCoordenada().getY()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() - 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) < Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getY() < posicion.getCoordenada().getY()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1));
        } else if (Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) >= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY()) && this.getCasilla().getCoordenada().getX() > posicion.getCoordenada().getX()) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() - 1, this.getCasilla().getCoordenada().getY()));
        } else {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY()));
        }
        
        int xActual = this.getCasilla().getCoordenada().getX();
        int yActual = this.getCasilla().getCoordenada().getY();
        int xDestino = nueva.getCoordenada().getX();
        int yDestino = nueva.getCoordenada().getY();

        // Verifica si la casilla posicion es contigua en sentido vertical u horizontal
        boolean esContiguaHorizontalmente = (xActual == xDestino) && (Math.abs(yActual - yDestino) == 1);
        boolean esContiguaVerticalmente = (yActual == yDestino) && (Math.abs(xActual - xDestino) == 1);

        if (esContiguaHorizontalmente || esContiguaVerticalmente) {
            Casilla casillaActual = tablero.getCasilla(this.getCasilla().getCoordenada());
            ArrayList<Humano> humanosCasillaActual = casillaActual.getNumHumano();
            humanosCasillaActual.remove(this);
            casillaActual.setNumHumano(humanosCasillaActual);

            Casilla casillaObjetivo = tablero.getCasilla(nueva.getCoordenada());
            ArrayList<Humano> humanosCasillaObjetivo = casillaObjetivo.getNumHumano();
            humanosCasillaObjetivo.add(this);
            casillaObjetivo.setNumHumano(humanosCasillaObjetivo);

            this.setCasilla(casillaObjetivo);
            juego.getPantallaJuego().agregarEvento("El humano blindado se ha movido a la posicion "  + nueva.getCoordenada().toString());
        }
        else {
            juego.getPantallaJuego().agregarEvento("El humano blindado no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
        }
        
    }
    
    @Override
    public void activarse(Tablero tablero, Juego juego){
        if(this.getCasilla().getNumZombie().isEmpty()){
            if (this.zombieMasCercano(tablero, juego)!=null) {
                Coordenada objetivo = this.zombieMasCercano(tablero, juego);
                Casilla nueva = tablero.getCasilla(objetivo);
                this.moverse(tablero, nueva,juego);
            }
            SwingUtilities.invokeLater(()->juego.getPantallaJuego().actualizarTablero(juego));
        }else{
            this.atacar(tablero,juego);
            SwingUtilities.invokeLater(()->juego.getPantallaJuego().actualizarTablero(juego));
        }
    }
   

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }


    @Override
    public void atacar(Tablero tablero,Juego juego){
        Casilla casillaTablero= tablero.getCasilla(this.getCasilla().getCoordenada());
        if(casillaTablero.getNumZombie().get(0).getNumHeridas()<5){
            casillaTablero.getNumZombie().get(0).setNumHeridas(casillaTablero.getNumZombie().get(0).getNumHeridas()+1);
            juego.getPantallaJuego().agregarEvento("El humano blindado ha hecho 1 herida a "+casillaTablero.getNumZombie().get(0).getNombre()+" por lo que tiene "+casillaTablero.getNumZombie().get(0).getNumHeridas()+" heridas");
            casillaTablero.getNumZombie().get(0).agregarHerida("Blindado");
            if(casillaTablero.getNumZombie().get(0).getNumHeridas()==5){
                ArrayList<Zombie> zombies = casillaTablero.getNumZombie();
                ArrayList<Zombie> jugadores = casillaTablero.getNumZombie();
                juego.getPantallaJuego().agregarEvento("El humano blindado ha matado al zombie "+casillaTablero.getNumZombie().get(0).getNombre());
                int indice = juego.getListaJugadores().indexOf(jugadores.get(0));

                if (indice != -1) {
                    juego.getListaJugadores().get(indice).setEstado("ELIMINADO");
                }
                zombies.remove(casillaTablero.getNumZombie().get(0));
                casillaTablero.setNumZombie(zombies);
                
            }
        }    
    }
}
