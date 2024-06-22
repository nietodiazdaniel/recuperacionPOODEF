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
public class Informatico extends HumanoCombatiente {

    public Informatico(Casilla casilla) {
        super(1, 3, casilla);
    }

    @Override
    public void calmarHambreZombie(Zombie zombie) {
        if (zombie.getHambre() >= 4) {
            zombie.setHambre(zombie.getHambre() - 2);

            //el informatico podia dañar si se lo comen
            if (((Math.random() * 2) + 1) == 1) {
                zombie.setNumHeridas(zombie.getNumHeridas() + 1);
            }
        }

    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion, Juego juego) {
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
            juego.getPantallaJuego().agregarEvento("El humano informatico se ha movido a la posicion " + nueva.getCoordenada().toString());
        } else {
            juego.getPantallaJuego().agregarEvento("El humano informatico no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
        }
    }

    @Override
    public void atacar(Tablero tablero, Juego juego) {
        Casilla casillaTablero = tablero.getCasilla(this.getCasilla().getCoordenada());
        if (!casillaTablero.getNumZombie().isEmpty()) {
            if (casillaTablero.getNumZombie().get(0).getNumHeridas() < 5) {
                casillaTablero.getNumZombie().get(0).setNumHeridas(casillaTablero.getNumZombie().get(0).getNumHeridas() + 1);
                juego.getPantallaJuego().agregarEvento("El humano informatico ha hecho 1 herida a " + casillaTablero.getNumZombie().get(0).getNombre() + " por lo que tiene " + casillaTablero.getNumZombie().get(0).getNumHeridas() + " heridas");
                casillaTablero.getNumZombie().get(0).agregarHerida("Informatico");
                if (casillaTablero.getNumZombie().get(0).getNumHeridas() == 5) {
                    ArrayList<Zombie> zombies = casillaTablero.getNumZombie();
                    ArrayList<Zombie> jugadores = casillaTablero.getNumZombie();
                    juego.getPantallaJuego().agregarEvento("El humano informatico ha matado al zombie " + casillaTablero.getNumZombie().get(0).getNombre());

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

    public void atacarADistancia(Tablero tablero, Juego juego, Casilla objetivo) {//MODIFICAR PARA ATCAR A DISTANCIA
        Casilla casillaTablero = tablero.getCasilla(objetivo.getCoordenada());
        if (casillaTablero.getNumZombie().get(0).getNumHeridas() < 5) {
            casillaTablero.getNumZombie().get(0).setNumHeridas(casillaTablero.getNumZombie().get(0).getNumHeridas() + 1);
            juego.getPantallaJuego().agregarEvento("El humano informatico ha hecho 1 herida a " + casillaTablero.getNumZombie().get(0).getNombre() + " por lo que tiene " + casillaTablero.getNumZombie().get(0).getNumHeridas() + " heridas");
            casillaTablero.getNumZombie().get(0).agregarHerida("Informatico");
            if (casillaTablero.getNumZombie().get(0).getNumHeridas() == 5) {
                ArrayList<Zombie> zombies = casillaTablero.getNumZombie();
                ArrayList<Zombie> jugadores = casillaTablero.getNumZombie();
                juego.getPantallaJuego().agregarEvento("El humano informatico ha matado al zombie " + casillaTablero.getNumZombie().get(0).getNombre());
                int indice = juego.getListaJugadores().indexOf(jugadores.get(0));

                if (indice != -1) {
                    juego.getListaJugadores().get(indice).setEstado("ELIMINADO");
                }
                zombies.remove(casillaTablero.getNumZombie().get(0));
                casillaTablero.setNumZombie(zombies);

            }
        }
    }

    @Override
    public void activarse(Tablero tablero, Juego juego) {
        Coordenada opc1 = new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY());
        Casilla opcion1 = null;
        if ((opc1.getX() >= 0) && (opc1.getX() < tablero.getFilas() - 1) && (opc1.getY() >= 0) && (opc1.getY() < tablero.getColumnas() - 1)) {
            opcion1 = tablero.getCasilla(opc1);
        }
        Coordenada opc2 = new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1);
        Casilla opcion2 = null;
        if ((opc2.getX() >= 0) && (opc2.getX() < tablero.getFilas() - 1) && (opc2.getY() >= 0) && (opc2.getY() < tablero.getColumnas() - 1)) {
            opcion2 = tablero.getCasilla(opc2);
        }
        Coordenada opc3 = new Coordenada(this.getCasilla().getCoordenada().getX() - 1, this.getCasilla().getCoordenada().getY());
        Casilla opcion3 = null;
        if ((opc3.getX() >= 0) && (opc3.getX() < tablero.getFilas() - 1) && (opc3.getY() >= 0) && (opc3.getY() < tablero.getColumnas() - 1)) {
            opcion3 = tablero.getCasilla(opc3);
        }
        Coordenada opc4 = new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() - 1);
        Casilla opcion4 = null;
        if ((opc4.getX() >= 0) && (opc4.getX() < tablero.getFilas() - 1) && (opc4.getY() >= 0) && (opc4.getY() < tablero.getColumnas() - 1)) {
            opcion4 = tablero.getCasilla(opc4);
        }
        if (!this.getCasilla().getNumZombie().isEmpty()) {//ESTO FUNCIONA BIEN
            this.atacar(tablero, juego);
            this.atacar(tablero, juego);
            SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
        } else if ((opcion1 == null || opcion1.getNumZombie().isEmpty()) && (opcion2 == null || opcion2.getNumZombie().isEmpty()) && (opcion3 == null || opcion3.getNumZombie().isEmpty()) && (opcion4 == null || opcion4.getNumZombie().isEmpty())) {//ESTO FUNCIONA BIEN
            Coordenada objetivo = this.zombieMasCercano(tablero, juego);
            Casilla nueva = tablero.getCasilla(objetivo);
            this.moverse(tablero, nueva, juego);
            Coordenada objetivo1 = this.zombieMasCercano(tablero, juego);
            Casilla nueva1 = tablero.getCasilla(objetivo1);
            this.moverse(tablero, nueva1, juego);
            SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
        } else {
            if (opcion1 != null && !opcion1.getNumZombie().isEmpty()) {
                this.atacarADistancia(tablero, juego, opcion1);
            } else if (opcion2 != null && !opcion2.getNumZombie().isEmpty()) {
                this.atacarADistancia(tablero, juego, opcion2);
            } else if (opcion3 != null && !opcion3.getNumZombie().isEmpty()) {
                this.atacarADistancia(tablero, juego, opcion3);
            } else if (opcion4 != null && !opcion4.getNumZombie().isEmpty()) {
                this.atacarADistancia(tablero, juego, opcion4);
            }
            SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
        }
    }
}
