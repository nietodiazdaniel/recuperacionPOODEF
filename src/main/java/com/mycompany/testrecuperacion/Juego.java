/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import gui.*;
import gui.PantallaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private boolean finalizarAVoluntad = false;

    public Juego(File selectedFile) {
        this.cargarJuego(selectedFile);
    }

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

    public void finalizarVoluntad() {
        this.finalizarAVoluntad = true;
    }

    public boolean isFinalizarAVoluntad() {
        return finalizarAVoluntad;
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
                }
            }

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !finalizarAVoluntad) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                if (!finalizarAVoluntad) {
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
                    }
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo
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
                }
            }

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados() && !finalizarAVoluntad) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                if (!finalizarAVoluntad) {
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
                    }
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo
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
                }
            }

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados() && !finalizarAVoluntad) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                if (!finalizarAVoluntad) {
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
                    }
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo
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
                }
            }

            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados() && !finalizarAVoluntad) {
                for (int i = 0; i < this.getNumJug(); i++) {
                    //COMPRUEBA SI ESTA VIVO O NO 
                    if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                        listaJugadores.get(i).activarse(this.tablero, this);
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                if (!finalizarAVoluntad) {
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
                    }
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo
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
        sb.append("Numero de Conejos: ").append(listaConejos.size()).append("\n");
        sb.append("Lista de Conejos:\n");
        for (Conejo conejo : listaConejos) {
            sb.append(conejo.toText()).append("\n");
        }
        sb.append("Numero de Humanos: ").append(listaHumanos.size()).append("\n");
        sb.append("Lista de Humanos:\n");
        for (Humano humano : listaHumanos) {
            sb.append(humano.toText()).append("\n");
        }

        return sb.toString();
    }

    public void cargarJuego(File selectedFile) {
        int numeroJugadores = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Numero de Jugadores:")) {
                    numeroJugadores = Integer.parseInt(linea.split(":")[1].trim());
                    this.tablero = new Tablero(numeroJugadores);
                    this.numJug = numeroJugadores;
                    linea = br.readLine();//LEE Lista de Zombies:
                    for (int i = 0; i < numeroJugadores; i++) {
                        String nombre = "";
                        String estado = "";
                        int numAcciones = 0;
                        ArrayList<Comestible> comestiblesDevorados = new ArrayList<>();
                        ArrayList<Comestible> comestiblesEliminados = new ArrayList<>();
                        ArrayList<String> heridasRecibidas = new ArrayList<>();
                        int numHeridas = 0;
                        int hambre = 0;
                        AtaqueEspecial ataqueEspecial = null;
                        Coordenada coor = null;

                        linea = br.readLine();
                        if (linea.startsWith("Nombre: ")) {
                            nombre = linea.substring("Nombre: ".length());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Estado: ")) {
                            estado = linea.substring("Estado: ".length());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Numero de Acciones: ")) {
                            numAcciones = Integer.parseInt(linea.split(":")[1].trim());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Numero de Comestibles Devorados: ")) {
                            int numDevorados = Integer.parseInt(linea.split(":")[1].trim());
                            linea = br.readLine();//LEE Comestibles Devorados:
                            for (int j = 0; j < numDevorados; j++) {
                                linea = br.readLine();
                                if (linea.startsWith("Humano:")) {
                                    int numeroActivaciones = 0;
                                    int aguante = 0;
                                    Coordenada coordenadaH = null;
                                    String tipo = "";
                                    linea = br.readLine();
                                    if (linea.startsWith("Tipo: ")) {
                                        tipo = linea.substring("Tipo: ".length());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Numero de Activaciones: ")) {
                                        numeroActivaciones = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Aguante: ")) {
                                        aguante = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Casilla: ")) {
                                        int inicioCoordenada = linea.indexOf("(") + 1;
                                        int finCoordenada = linea.indexOf(")");
                                        String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                                        String[] partesCoordenada = coordenadaStr.split(",");
                                        int x = Integer.parseInt(partesCoordenada[0].trim());
                                        int y = Integer.parseInt(partesCoordenada[1].trim());
                                        coordenadaH = new Coordenada(x, y);
                                    }
                                    switch (tipo) {
                                        case "Soldado":
                                            Soldado humano = new Soldado(this.tablero.getCasilla(coordenadaH));
                                            comestiblesDevorados.add(humano);
                                            break;
                                        case "Blindado":
                                            Blindado blindado = new Blindado(this.tablero.getCasilla(coordenadaH));
                                            comestiblesDevorados.add(blindado);
                                            break;
                                        case "Especialista":
                                            Especialista espec = new Especialista(this.tablero.getCasilla(coordenadaH));
                                            comestiblesDevorados.add(espec);
                                            break;
                                        case "Huidizo":
                                            Huidizo huidizo = new Huidizo(this.tablero.getCasilla(coordenadaH));
                                            comestiblesDevorados.add(huidizo);
                                            break;
                                        case "Informatico":
                                            Informatico infor = new Informatico(this.tablero.getCasilla(coordenadaH));
                                            comestiblesDevorados.add(infor);
                                            break;

                                    }
                                } else if (linea.startsWith("Conejo:")) {
                                    String nombreCon = "";
                                    int identificador = 0;
                                    Coordenada coordenadaC = null;
                                    linea = br.readLine();
                                    if (linea.startsWith("Nombre: ")) {
                                        nombreCon = linea.substring("Nombre: ".length());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Identificador: ")) {
                                        identificador = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Casilla: ")) {
                                        int inicioCoordenada = linea.indexOf("(") + 1;
                                        int finCoordenada = linea.indexOf(")");
                                        String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                                        String[] partesCoordenada = coordenadaStr.split(",");
                                        int x = Integer.parseInt(partesCoordenada[0].trim());
                                        int y = Integer.parseInt(partesCoordenada[1].trim());
                                        coordenadaC = new Coordenada(x, y);

                                    }
                                    Conejo conejo = new Conejo(nombreCon, identificador, this.tablero.getCasilla(coordenadaC));
                                    comestiblesDevorados.add(conejo);
                                }
                            }
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Numero de Comestibles Eliminados: ")) {
                            int numEliminados = Integer.parseInt(linea.split(":")[1].trim());
                            linea = br.readLine();//LEE Comestibles Eliminados:
                            for (int j = 0; j < numEliminados; j++) {
                                linea = br.readLine();
                                if (linea.startsWith("Humano:")) {
                                    int numeroActivaciones = 0;
                                    int aguante = 0;
                                    Coordenada coordenadaH = null;
                                    String tipo = "";
                                    linea = br.readLine();
                                    if (linea.startsWith("Tipo: ")) {
                                        tipo = linea.substring("Tipo: ".length());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Numero de Activaciones: ")) {
                                        numeroActivaciones = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Aguante: ")) {
                                        aguante = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Casilla: ")) {
                                        int inicioCoordenada = linea.indexOf("(") + 1;
                                        int finCoordenada = linea.indexOf(")");
                                        String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                                        String[] partesCoordenada = coordenadaStr.split(",");
                                        int x = Integer.parseInt(partesCoordenada[0].trim());
                                        int y = Integer.parseInt(partesCoordenada[1].trim());
                                        coordenadaH = new Coordenada(x, y);
                                    }
                                    switch (tipo) {
                                        case "Soldado":
                                            Soldado humano = new Soldado(this.tablero.getCasilla(coordenadaH));
                                            comestiblesEliminados.add(humano);
                                            break;
                                        case "Blindado":
                                            Blindado blindado = new Blindado(this.tablero.getCasilla(coordenadaH));
                                            comestiblesEliminados.add(blindado);
                                            break;
                                        case "Especialista":
                                            Especialista espec = new Especialista(this.tablero.getCasilla(coordenadaH));
                                            comestiblesEliminados.add(espec);
                                            break;
                                        case "Huidizo":
                                            Huidizo huidizo = new Huidizo(this.tablero.getCasilla(coordenadaH));
                                            comestiblesEliminados.add(huidizo);
                                            break;
                                        case "Informatico":
                                            Informatico infor = new Informatico(this.tablero.getCasilla(coordenadaH));
                                            comestiblesEliminados.add(infor);
                                            break;

                                    }
                                } else if (linea.startsWith("Conejo:")) {
                                    String nombreCon = "";
                                    int identificador = 0;
                                    Coordenada coordenadaC = null;
                                    linea = br.readLine();
                                    if (linea.startsWith("Nombre: ")) {
                                        nombreCon = linea.substring("Nombre: ".length());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Identificador: ")) {
                                        identificador = Integer.parseInt(linea.split(":")[1].trim());
                                    }
                                    linea = br.readLine();
                                    if (linea.startsWith("Casilla: ")) {
                                        int inicioCoordenada = linea.indexOf("(") + 1;
                                        int finCoordenada = linea.indexOf(")");
                                        String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                                        String[] partesCoordenada = coordenadaStr.split(",");
                                        int x = Integer.parseInt(partesCoordenada[0].trim());
                                        int y = Integer.parseInt(partesCoordenada[1].trim());
                                        coordenadaC = new Coordenada(x, y);

                                    }
                                    Conejo conejo = new Conejo(nombreCon, identificador, this.tablero.getCasilla(coordenadaC));
                                    comestiblesEliminados.add(conejo);
                                }
                            }
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Numero de Heridas: ")) {
                            numHeridas = Integer.parseInt(linea.split(":")[1].trim());
                            linea = br.readLine();//LEE Heridas Recibidas:
                            for (int j = 0; j < numHeridas; j++) {
                                linea = br.readLine();
                                String tipo;
                                tipo = linea.trim();
                                heridasRecibidas.add(tipo);
                            }
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Hambre: ")) {
                            hambre = Integer.parseInt(linea.split(":")[1].trim());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Ataque Especial:")) {
                            String nombreAtaque = "";
                            int potencia = 0;
                            int valorExito = 0;
                            int alcance = 0;
                            linea = br.readLine();
                            if (linea.startsWith("Nombre: ")) {
                                nombreAtaque = linea.substring("Nombre: ".length());
                            }
                            linea = br.readLine();
                            if (linea.startsWith("Potencia: ")) {
                                potencia = Integer.parseInt(linea.split(":")[1].trim());
                            }
                            linea = br.readLine();
                            if (linea.startsWith("Valor de Exito: ")) {
                                valorExito = Integer.parseInt(linea.split(":")[1].trim());
                            }
                            linea = br.readLine();
                            if (linea.startsWith("Alcance: ")) {
                                alcance = Integer.parseInt(linea.split(":")[1].trim());
                            }
                            ataqueEspecial = new AtaqueEspecial(nombreAtaque, potencia, valorExito, alcance);

                        }
                        linea = br.readLine();
                        if (linea.startsWith("Casilla:")) {
                            int inicioCoordenada = linea.indexOf("(") + 1;
                            int finCoordenada = linea.indexOf(")");
                            String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                            String[] partesCoordenada = coordenadaStr.split(",");
                            int x = Integer.parseInt(partesCoordenada[0].trim());
                            int y = Integer.parseInt(partesCoordenada[1].trim());
                            coor = new Coordenada(x, y);
                        }
                        Casilla casillaZ = new Casilla(coor);
                        //CREAMOS EL ZOMBIE
                        Zombie zombie = new Zombie(nombre, estado, numHeridas, hambre, casillaZ);
                        zombie.setNumAcciones(numAcciones);
                        zombie.setComestiblesDevorados(comestiblesDevorados);
                        zombie.setComestiblesEliminados(comestiblesEliminados);
                        zombie.setHeridasRecibidas(heridasRecibidas);
                        zombie.setAtaqueEspecial(ataqueEspecial);
                        this.listaJugadores.add(zombie);
                        tablero.getCasilla(coor).getNumZombie().add(zombie);
                        tablero.getCasilla(coor).setNumZombie(tablero.getCasilla(coor).getNumZombie());

                    }
                } else if (linea.startsWith("Numero de Conejos:")) {
                    int numeroConejos = Integer.parseInt(linea.split(":")[1].trim());
                    linea = br.readLine();//LEE Lista de COnejos:
                    for (int i = 0; i < numeroConejos; i++) {
                        String nombre = "";
                        int identificador = 0;
                        Coordenada coordenada = null;
                        linea = br.readLine();
                        if (linea.startsWith("Nombre: ")) {
                            nombre = linea.substring("Nombre: ".length());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Identificador: ")) {
                            identificador = Integer.parseInt(linea.split(":")[1].trim());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Casilla: ")) {
                            int inicioCoordenada = linea.indexOf("(") + 1;
                            int finCoordenada = linea.indexOf(")");
                            String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                            String[] partesCoordenada = coordenadaStr.split(",");
                            int x = Integer.parseInt(partesCoordenada[0].trim());
                            int y = Integer.parseInt(partesCoordenada[1].trim());
                            coordenada = new Coordenada(x, y);

                        }
                        Conejo conejo = new Conejo(nombre, identificador, this.tablero.getCasilla(coordenada));
                        ArrayList<Conejo> conejosEnCasilla = tablero.getCasilla(conejo.getCasilla().getCoordenada()).getNumConejos();
                        conejosEnCasilla.add(conejo);
                        tablero.getCasilla(conejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
                        this.listaConejos.add(conejo);
                    }
                } else if (linea.startsWith("Numero de Humanos:")) {
                    int numeroHumanos = Integer.parseInt(linea.split(":")[1].trim());
                    linea = br.readLine();
                    for (int i = 0; i < numeroHumanos; i++) {
                        int numeroActivaciones = 0;
                        int aguante = 0;
                        Coordenada coordenada = null;
                        String tipo = "";
                        linea = br.readLine();
                        if (linea.startsWith("Tipo: ")) {
                            tipo = linea.substring("Tipo: ".length());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Numero de Activaciones: ")) {
                            numeroActivaciones = Integer.parseInt(linea.split(":")[1].trim());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Aguante: ")) {
                            aguante = Integer.parseInt(linea.split(":")[1].trim());
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Casilla: ")) {
                            int inicioCoordenada = linea.indexOf("(") + 1;
                            int finCoordenada = linea.indexOf(")");
                            String coordenadaStr = linea.substring(inicioCoordenada, finCoordenada);
                            String[] partesCoordenada = coordenadaStr.split(",");
                            int x = Integer.parseInt(partesCoordenada[0].trim());
                            int y = Integer.parseInt(partesCoordenada[1].trim());
                            coordenada = new Coordenada(x, y);
                        }
                        switch (tipo) {
                            case "Soldado":
                                Soldado humano = new Soldado(this.tablero.getCasilla(coordenada));
                                this.listaHumanos.add(humano);
                                tablero.getCasilla(coordenada).getNumHumano().add(humano);
                                break;
                            case "Blindado":
                                Blindado blindado = new Blindado(this.tablero.getCasilla(coordenada));
                                this.listaHumanos.add(blindado);
                                tablero.getCasilla(coordenada).getNumHumano().add(blindado);
                                break;
                            case "Especialista":
                                Especialista espec = new Especialista(this.tablero.getCasilla(coordenada));
                                this.listaHumanos.add(espec);
                                tablero.getCasilla(coordenada).getNumHumano().add(espec);
                                break;
                            case "Huidizo":
                                Huidizo huidizo = new Huidizo(this.tablero.getCasilla(coordenada));
                                this.listaHumanos.add(huidizo);
                                tablero.getCasilla(coordenada).getNumHumano().add(huidizo);
                                break;
                            case "Informatico":
                                Informatico infor = new Informatico(this.tablero.getCasilla(coordenada));
                                this.listaHumanos.add(infor);
                                tablero.getCasilla(coordenada).getNumHumano().add(infor);
                                break;

                        }
                    }
                } 
            }

        } catch (IOException e) {
            //ERROR AL LEER ARCHIVO
        }
    }

    public void iniciarJuegoCargado() {
        new Thread(() -> {
            Coordenada nueva = new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1);
            Casilla objetivo = tablero.getCasilla(nueva);

            pantallaJuego = new PantallaJuego(numJug);
            SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
            pantallaJuego.agregarEvento("JUEGO INICIADO CON " + numJug + " JUGADORES.");

            boolean igualarTurnos = false;
            boolean turnoDesigual = false;
            while (!todosObjetivoDevorandoHuidizo() && !todosJugadoresEliminados() && !xObjetivoRestoEliminados() && !finalizarAVoluntad) {
                if (!igualarTurnos) {
                    for (Zombie zombie : listaJugadores) {
                        if (zombie.getNumAcciones() > 0 && zombie.getNumAcciones() < 3) {
                            turnoDesigual = true;
                        }
                        if (turnoDesigual) {
                            if (zombie.getEstado().equals("ACTIVO")) {
                                zombie.activarse(tablero, this);

                            }
                        }
                    }
                    igualarTurnos = true;

                } else {
                    for (int i = 0; i < this.getNumJug(); i++) {
                        //COMPRUEBA SI ESTA VIVO O NO 
                        if ("ACTIVO".equals(this.getListaJugadores().get(i).getEstado())) {
                            listaJugadores.get(i).activarse(this.tablero, this);
                        }
                    }
                }
                pantallaJuego.setPanelControles(new PanelControlPredeterminado());
                ArrayList<Humano> copiaListaHumanos = new ArrayList(this.getListaHumanos());
                pantallaJuego.agregarEvento("********** TURNO HUMANOS **********");
                if (!finalizarAVoluntad) {
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
                    }
                }
                SwingUtilities.invokeLater(() -> pantallaJuego.actualizarTablero(this));
                try {
                    Thread.sleep(500); // Pausa de medio segundo
                } catch (InterruptedException e) {
                }
            }
            pantallaJuego.setPanelControles(new PanelPartidaTerminada());
            pantallaJuego.anadirFinalPartida();
        }).start();
    }

}
