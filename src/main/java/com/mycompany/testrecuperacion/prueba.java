/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author nieto
 */
public class prueba {

    public prueba() {

    }

    public static void probarLectura() {
        File selectedFile = new File("C:/Users/nieto/Documents/NetBeansProjects/recuPOO/src/main/java/registros/partida");
        int numeroJugadores = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Numero de Jugadores:")) {
                    numeroJugadores = Integer.parseInt(linea.split(":")[1].trim());
                    System.out.println("Numero de jugadores: " + numeroJugadores + "\n");
                    linea = br.readLine();//LEO Lista de zombies:
                    System.out.println("Leo la linea de: " + linea);
                    for (int i = 0; i < numeroJugadores; i++) {
                        String nombre = "";
                        String estado = "";
                        int numAcciones = 0;
                        ArrayList<Comestible> comestiblesDevorados = new ArrayList<>();
                        ArrayList<Comestible> comestiblesEliminados = new ArrayList<>();
                        ArrayList<String> heridasRecibidas = new ArrayList<>();
                        int numHeridas = 0;
                        int hambre = 0;
                        Ataque ataqueEspecial = null;
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
                        if (linea.startsWith("Número de Acciones: ")) {
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
                                            Soldado humano = new Soldado(new Casilla(coordenadaH));
                                            comestiblesDevorados.add(humano);
                                            break;
                                        case "Blindado":
                                            Blindado blindado = new Blindado(new Casilla(coordenadaH));
                                            comestiblesDevorados.add(blindado);
                                            break;
                                        case "Especialista":
                                            Especialista espec = new Especialista(new Casilla(coordenadaH));
                                            comestiblesDevorados.add(espec);
                                            break;
                                        case "Huidizo":
                                            Huidizo huidizo = new Huidizo(new Casilla(coordenadaH));
                                            comestiblesDevorados.add(huidizo);
                                            break;
                                        case "Informatico":
                                            Informatico infor = new Informatico(new Casilla(coordenadaH));
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
                                    Conejo conejo = new Conejo(nombreCon, identificador, new Casilla(coordenadaC));
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
                                            Soldado humano = new Soldado(new Casilla(coordenadaH));
                                            comestiblesEliminados.add(humano);
                                            break;
                                        case "Blindado":
                                            Blindado blindado = new Blindado(new Casilla(coordenadaH));
                                            comestiblesEliminados.add(blindado);
                                            break;
                                        case "Especialista":
                                            Especialista espec = new Especialista(new Casilla(coordenadaH));
                                            comestiblesEliminados.add(espec);
                                            break;
                                        case "Huidizo":
                                            Huidizo huidizo = new Huidizo(new Casilla(coordenadaH));
                                            comestiblesEliminados.add(huidizo);
                                            break;
                                        case "Informatico":
                                            Informatico infor = new Informatico(new Casilla(coordenadaH));
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
                                    Conejo conejo = new Conejo(nombreCon, identificador, new Casilla(coordenadaC));
                                    comestiblesEliminados.add(conejo);
                                }
                            }
                        }
                        linea = br.readLine();
                        if (linea.startsWith("Número de Heridas: ")) {
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
                        System.out.println("LEO ESTOOOO " + linea);
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
                            if (linea.startsWith("Valor de Éxito: ")) {
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
                        System.out.println(zombie.toText());
                        //this.listaJugadores.add(zombie);
                        //tablero.getCasilla(coor).getNumZombie().add(zombie);
                        //tablero.getCasilla(coor).setNumZombie(tablero.getCasilla(coor).getNumZombie());

                    }

                } else if (linea.startsWith("Numero de Conejos:")) {
                    int numeroConejos = Integer.parseInt(linea.split(":")[1].trim());
                    System.out.println("Numero de conejos: " + numeroConejos + "\n");

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
                        Conejo conejo = new Conejo(nombre, identificador, new Casilla(coordenada));
                        System.out.println(conejo.toText());
                        //ArrayList<Conejo> conejosEnCasilla = tablero.getCasilla(conejo.getCasilla().getCoordenada()).getNumConejos();
                        //conejosEnCasilla.add(conejo);
                        //tablero.getCasilla(conejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
                        //this.listaConejos.add(conejo);
                    }
                } else if (linea.startsWith("Numero de Humanos:")) {
                    int numeroHumanos = Integer.parseInt(linea.split(":")[1].trim());
                    System.out.println("Numero de humanos: " + numeroHumanos + "\n");
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
                                Soldado humano = new Soldado(new Casilla(coordenada));
                                System.out.println(humano.toText());
                                // this.listaHumanos.add(humano);
                                //tablero.getCasilla(coordenada).getNumHumano().add(humano);
                                break;
                            case "Blindado":
                                Blindado blindado = new Blindado(new Casilla(coordenada));
                                System.out.println(blindado.toText());
                                //this.listaHumanos.add(blindado);
                                //tablero.getCasilla(coordenada).getNumHumano().add(blindado);
                                break;
                            case "Especialista":
                                Especialista espec = new Especialista(new Casilla(coordenada));
                                System.out.println(espec.toText());
                                //this.listaHumanos.add(espec);
                                //tablero.getCasilla(coordenada).getNumHumano().add(espec);
                                break;
                            case "Huidizo":
                                Huidizo huidizo = new Huidizo(new Casilla(coordenada));
                                System.out.println(huidizo.toText());
                                //this.listaHumanos.add(huidizo);
                                //tablero.getCasilla(coordenada).getNumHumano().add(huidizo);
                                break;
                            case "Informatico":
                                Informatico infor = new Informatico(new Casilla(coordenada));
                                System.out.println(infor.toText());
                                //this.listaHumanos.add(infor);
                                //tablero.getCasilla(coordenada).getNumHumano().add(infor);
                                break;
                        }
                    }
                }

            }

        } catch (IOException e) {
            //ERROR AL LEER ARCHIVO
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        probarLectura();
    }

}
