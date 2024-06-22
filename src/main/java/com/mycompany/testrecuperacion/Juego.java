/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import gui.*;
import gui.PantallaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author nieto
 */
public class Juego {

    private int numJug;
    private Tablero tablero;
    private ArrayList<Zombie> listaJugadores = new ArrayList<>();
    private ArrayList<Humano> listaHumanos = new ArrayList<>();
    private ArrayList<Conejo> listaConejos = new ArrayList<>();
    private PantallaJuego pantallaJuego;

    public Juego(int numJug) {
        this.numJug = numJug;
        this.tablero = new Tablero(numJug);
    }

    public int getNumJug() {
        return numJug;
    }

    public void setNumJug(int numJug) {
        this.numJug = numJug;
    }

    public ArrayList<Zombie> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Humano> getListaHumanos() {
        return listaHumanos;
    }

    public void setListaJugadores(ArrayList<Zombie> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public void setListaHumanos(ArrayList<Humano> listaHumanos) {
        this.listaHumanos = listaHumanos;
    }

    public ArrayList<Conejo> getListaConejos() {
        return listaConejos;
    }

    public void setListaConejos(ArrayList<Conejo> listaConejos) {
        this.listaConejos = listaConejos;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public PantallaJuego getPantallaJuego() {
        return pantallaJuego;
    }

    public boolean todosObjetivoDevorandoHuidizo() {
        Coordenada coordObjetivo = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
        Casilla objetivo = tablero.getCasilla(coordObjetivo);

        for (int i = 0; i <= this.numJug - 1; i++) {
            if (!(listaJugadores.get(i).getCasilla().getCoordenada().equals(coordObjetivo)) || !(listaJugadores.get(i).haDevoradoHuidizo())) {
                return false;
            }
        }
        return true;
    }

    public boolean todosJugadoresEliminados() {
        for (int i = 0; i <= this.numJug - 1; i++) {
            if (listaJugadores.get(i).getEstado().equals("ACTIVO")) {
                return false;
            }
        }
        return true;
    }

    public boolean xObjetivoRestoEliminados() {
        for (int i = 0; i <= this.numJug - 1; i++) {
            if ((listaJugadores.get(i).getEstado().equals("ACTIVO")) && !(listaJugadores.get(i).getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1)) && (listaJugadores.get(i).haDevoradoHuidizo()))) {
                return false;
            }
        }
        return true;
    }

    public void iniciarJuego() {

        Coordenada inicio = new Coordenada(0, 0);
        Casilla comienzo = new Casilla(inicio);
        for (int i = 1; i <= this.numJug; i++) {
            Scanner ent = new Scanner(System.in);
            System.out.println("Nombre " + i);
            String nombre = ent.nextLine();
            Zombie zom = new Zombie(nombre, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
        }
        for (int i = 0; i < numJug; i++) {
            for (int j = 0; j < 3; j++) {
                Random random = new Random();
                int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion = tablero.getCasilla(coor);
                Humano humano = Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
        }

        Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
        Casilla objetivo = tablero.getCasilla(nueva);

        tablero.imprimirTablero();

        while (0 == 0) {
            for (int i = 0; i < this.getNumJug(); i++) {
                //COMPRUEBA SI ESTA VIVO O NO 
                if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                    listaJugadores.get(i).activarse(this.tablero, this);
                }
            }
            for (Humano humano : this.getListaHumanos()) {
                if (!listaJugadores.isEmpty()) {
                    humano.activarse(this.tablero, this);
                }
            }
            for (int i = 0; i < this.getNumJug(); i++) {
                Random random = new Random();
                int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                Casilla posicion = tablero.getCasilla(coor);
                Humano humano = Humano.aparicion(posicion);
                this.listaHumanos.add(humano);
                tablero.getCasilla(coor).getNumHumano().add(humano);
                //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
            }
            tablero.imprimirTablero();
        }

       
    }

    public void iniciarJuegoGUI(String nom1) {
        new Thread(() -> {
            Coordenada inicio = new Coordenada(0, 0);
            Casilla comienzo = new Casilla(inicio);
            Zombie zom = new Zombie(nom1, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);

            for (int i = 0; i < numJug; i++) {
                for (int j = 0; j < 3; j++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
            }

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados()) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                for (Humano humano : copiaListaHumanos) {
                    if (!listaJugadores.isEmpty()) {
                        humano.activarse(this.tablero, this);
                    }
                }
                for (int i = 0; i < this.getNumJug(); i++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    pantallaJuego.agregarEvento("Ha aparecido un Humano " + humano.getClass().getSimpleName() + " en la posicion " + coor.toString());
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo, ajusta según sea necesario
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pantallaJuego.setPanelControles(new PanelPartidaTerminada());
            pantallaJuego.anadirFinalPartida();

        }).start();
    }

    public void iniciarJuegoGUI(String nom1, String nom2) {
        new Thread(() -> {
            Coordenada inicio = new Coordenada(0, 0);
            Casilla comienzo = new Casilla(inicio);
            Zombie zom = new Zombie(nom1, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
            Zombie zom2 = new Zombie(nom2, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom2);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom2);

            for (int i = 0; i < numJug; i++) {
                for (int j = 0; j < 3; j++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
            }
            

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados()) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                for (Humano humano : copiaListaHumanos) {
                    if (!todosJugadoresEliminados()) {
                        humano.activarse(this.tablero, this);
                    }
                }
                for (int i = 0; i < this.getNumJug(); i++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    pantallaJuego.agregarEvento("Ha aparecido un Humano " + humano.getClass().getSimpleName() + " en la posicion " + coor.toString());
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo, ajusta según sea necesario
                } catch (InterruptedException e) {
                }
            }
            pantallaJuego.setPanelControles(new PanelPartidaTerminada());
            pantallaJuego.anadirFinalPartida();

        }).start();
    }

    public void iniciarJuegoGUI(String nom1, String nom2, String nom3) {
        new Thread(() -> {
            Coordenada inicio = new Coordenada(0, 0);
            Casilla comienzo = new Casilla(inicio);
            Zombie zom = new Zombie(nom1, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
            Zombie zom2 = new Zombie(nom2, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom2);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom2);
            Zombie zom3 = new Zombie(nom3, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom3);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom3);

            for (int i = 0; i < numJug; i++) {
                for (int j = 0; j < 3; j++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
            }
            

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados()) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                for (Humano humano : copiaListaHumanos) {
                    if (!listaJugadores.isEmpty()) {
                        humano.activarse(this.tablero, this);
                    }
                }
                for (int i = 0; i < this.getNumJug(); i++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    pantallaJuego.agregarEvento("Ha aparecido un Humano " + humano.getClass().getSimpleName() + " en la posicion " + coor.toString());
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo, ajusta según sea necesario
                } catch (InterruptedException e) {
                }
            }
            pantallaJuego.setPanelControles(new PanelPartidaTerminada());
            pantallaJuego.anadirFinalPartida();

        }).start();
    }

    public void iniciarJuegoGUI(String nom1, String nom2, String nom3, String nom4) {
        new Thread(() -> {
            Coordenada inicio = new Coordenada(0, 0);
            Casilla comienzo = new Casilla(inicio);
            Zombie zom = new Zombie(nom1, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom);
            Zombie zom2 = new Zombie(nom2, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom2);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom2);
            Zombie zom3 = new Zombie(nom3, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom3);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom3);
            Zombie zom4 = new Zombie(nom4, "ACTIVO", 0, 0, comienzo);
            tablero.getCasilla(inicio).getNumZombie().add(zom4);
            tablero.getCasilla(inicio).setNumZombie(tablero.getCasilla(inicio).getNumZombie());
            this.listaJugadores.add(zom4);

            for (int i = 0; i < numJug; i++) {
                for (int j = 0; j < 3; j++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
            }
            

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados()) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                for (Humano humano : copiaListaHumanos) {
                    if (!listaJugadores.isEmpty()) {
                        humano.activarse(this.tablero, this);
                    }
                }
                for (int i = 0; i < this.getNumJug(); i++) {
                    Random random = new Random();
                    int numeroAleatorio1 = random.nextInt(tablero.getFilas() - 1);
                    int numeroAleatorio2 = random.nextInt(tablero.getColumnas() - 1);
                    Coordenada coor = new Coordenada(numeroAleatorio1, numeroAleatorio2);
                    Casilla posicion = tablero.getCasilla(coor);
                    Humano humano = Humano.aparicion(posicion);
                    pantallaJuego.agregarEvento("Ha aparecido un Humano " + humano.getClass().getSimpleName() + " en la posicion " + coor.toString());
                    this.listaHumanos.add(humano);
                    tablero.getCasilla(coor).getNumHumano().add(humano);
                    //tablero.getCasilla(coor).setNumHumano(tablero.getCasilla(coor).getNumHumano());
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo, ajusta según sea necesario
                } catch (InterruptedException e) {
                }
            }
            pantallaJuego.setPanelControles(new PanelPartidaTerminada());
            pantallaJuego.anadirFinalPartida();

        }).start();
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numero de Jugadores: ").append(numJug).append("\n");
        sb.append("Lista de Zombies:\n");
        for (Zombie zombie : listaJugadores) {
            sb.append(zombie.toText()).append("\n");
        }
        sb.append("Lista de Humanos:\n");
        for (Humano humano : listaHumanos) {
            sb.append(humano.toText()).append("\n");
        }
        sb.append("Lista de Conejos:\n");
        for (Conejo conejo : listaConejos) {
            sb.append(conejo.toText()).append("\n");
        }
        sb.append("Tablero: ").append(tablero.toText()).append("\n");
        return sb.toString();
    }

}
