/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author delac
 */
public class Huidizo extends Humano {

    public Huidizo(Casilla casilla) {
        super(1, 1, casilla);
    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion, Juego juego) {
        Casilla nueva;
        int xActual = this.getCasilla().getCoordenada().getX();
        int yActual = this.getCasilla().getCoordenada().getY();
        int xLimite = tablero.getFilas() - 1;
        int yLimite = tablero.getColumnas() - 1;
        //SOLO SE PUEDE MOVER HACIA LA DERECHA O HACIA ABAJO
        if ((xActual < xLimite) && (yActual < yLimite)) {//ME PUEDO MOVER HACIA LA DERECHA O ABAJO
            if ((Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) <= Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY())) && (yActual < yLimite)) {
                nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1));
            } else if ((Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX()) > Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY())) && (yActual < yLimite)) {
                nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY()));
            } else {
                //NO SE MUEVE
                nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY()));
            }
        } else if (yActual < yLimite) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1));
        } else if (xActual < xLimite) {
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY()));
        } else {
            //NO SE MUEVE
            nueva = tablero.getCasilla(new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY()));

        }

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
            juego.getPantallaJuego().agregarEvento("El humano huidizo se ha movido a la posicion " + nueva.getCoordenada().toString());
        } else {
            juego.getPantallaJuego().agregarEvento("El humano huidizo no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
        }
    }

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        zombie.setHambre(0);
    }

    @Override
    public void atacar(Tablero tablero, Juego juego) {
        //EL H. HUIDIZO NO ATACA

    }

    @Override
    public void activarse(Tablero tablero, Juego juego) {
        Casilla objetivo = new Casilla(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1));
        this.moverse(tablero, objetivo, juego);
        if (this.getCasilla().getCoordenada().equals(objetivo.getCoordenada())) {
            Casilla casillaActual = tablero.getCasilla(this.getCasilla().getCoordenada());
            ArrayList<Humano> humanosCasillaActual = casillaActual.getNumHumano();
            humanosCasillaActual.remove(this);
            casillaActual.setNumHumano(humanosCasillaActual);
            juego.getListaHumanos().remove(this);
            juego.getPantallaJuego().agregarEvento("El humano huidizo se ha escapado.");
        }
        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));

    }
}
