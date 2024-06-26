/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import gui.*;

/**
 *
 * @author nieto
 */
public class Zombie implements Activable {

    private String nombre;
    private String estado;//SERA "ACTIVO" O "ELIMINADO"
    private final int maxAcciones = 3;
    private int numAcciones = 0;
    private ArrayList<Comestible> comestiblesDevorados = new ArrayList<>();
    private ArrayList<Comestible> comestiblesEliminados = new ArrayList<>();
    private int numHeridas;
    private ArrayList<String> heridasRecibidas = new ArrayList<>();
    private final int maxHeridas = 5;
    private int hambre;
    private Ataque devorar = new Devorar();
    private Ataque ataqueEspecial = new AtaqueEspecial();
    private Casilla casilla;
    
    public Zombie(String nombre, String estado, int numHeridas, int hambre, Casilla casilla) {
        this.nombre = nombre;
        this.estado = estado;
        this.numHeridas = numHeridas;
        this.hambre = hambre;
        this.casilla = casilla;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumAcciones() {
        return numAcciones;
    }

    public void setNumAcciones(int numAcciones) {
        this.numAcciones = numAcciones;
    }

    public ArrayList<Comestible> getComestiblesDevorados() {
        return comestiblesDevorados;
    }

    public void setComestiblesDevorados(ArrayList<Comestible> comestiblesDevorados) {
        this.comestiblesDevorados = comestiblesDevorados;
    }

    public ArrayList<Comestible> getComestiblesEliminados() {
        return comestiblesEliminados;
    }

    public void setComestiblesEliminados(ArrayList<Comestible> comestiblesEliminados) {
        this.comestiblesEliminados = comestiblesEliminados;
    }

    public int getNumHeridas() {
        return numHeridas;
    }

    public void setNumHeridas(int numHeridas) {
        this.numHeridas = numHeridas;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }
    
    public void incrementarAcciones() {
        this.numAcciones++;
    }

    public void agregarHerida(String tipoHumano) {
        if (heridasRecibidas.size() < maxHeridas) {
            heridasRecibidas.add(tipoHumano);
        }
    }

    public void setHeridasRecibidas(ArrayList<String> heridasRecibidas) {
        this.heridasRecibidas = heridasRecibidas;
    }

    public void setAtaqueEspecial(Ataque ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }
    
    public ArrayList<String> getHeridasRecibidas() {
        return heridasRecibidas;
    }

    public Ataque getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public boolean haDevoradoHuidizo() {
        for (Comestible comestible : comestiblesDevorados) {
            if (comestible instanceof Huidizo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Zombie zombi = (Zombie) obj;
        return nombre.equals(zombi.nombre);
    }

    @Override
    public void moverse(Tablero tablero, Casilla posicion, Juego juego) {
        int xActual = this.getCasilla().getCoordenada().getX();
        int yActual = this.getCasilla().getCoordenada().getY();
        int xDestino = posicion.getCoordenada().getX();
        int yDestino = posicion.getCoordenada().getY();

        // Verifica si la casilla Destino o Posicion es contigua en sentido vertical u horizontal
        boolean esContiguaHorizontalmente = (xActual == xDestino) && (Math.abs(yActual - yDestino) == 1);
        boolean esContiguaVerticalmente = (yActual == yDestino) && (Math.abs(xActual - xDestino) == 1);

        if (esContiguaHorizontalmente || esContiguaVerticalmente) {
            Casilla casillaActual = tablero.getCasilla(this.getCasilla().getCoordenada());
            if (casillaActual.getNumHumano().size() == 0) {
                ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                zombiesCasillaActual.remove(this);
                casillaActual.setNumZombie(zombiesCasillaActual);

                Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                zombiesCasillaObjetivo.add(this);
                casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                this.setCasilla(casillaObjetivo);
                juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.getCoordenada().toString() + " gastando 1 accion");
                numAcciones++;

            } else if (casillaActual.getNumHumano().size() == 1) {
                if (this.getNumAcciones() < 2) {
                    ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                    zombiesCasillaActual.remove(this);
                    casillaActual.setNumZombie(zombiesCasillaActual);

                    Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                    ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                    zombiesCasillaObjetivo.add(this);
                    casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                    this.setCasilla(casillaObjetivo);
                    juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.getCoordenada().toString() + " gastando 2 acciones");
                    this.setNumAcciones(this.getNumAcciones() + 2);
                } else {
                    juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " no se puede mover, utiliza la accion en otra accion diferente a moverse");
                }

            } else if (casillaActual.getNumHumano().size() == 2) {
                if (this.getNumAcciones() < 1) {
                    ArrayList<Zombie> zombiesCasillaActual = casillaActual.getNumZombie();
                    zombiesCasillaActual.remove(this);
                    casillaActual.setNumZombie(zombiesCasillaActual);

                    Casilla casillaObjetivo = tablero.getCasilla(posicion.getCoordenada());
                    ArrayList<Zombie> zombiesCasillaObjetivo = casillaObjetivo.getNumZombie();
                    zombiesCasillaObjetivo.add(this);
                    casillaObjetivo.setNumZombie(zombiesCasillaObjetivo);

                    this.setCasilla(casillaObjetivo);
                    juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " se ha movido a la posicion " + posicion.getCoordenada().toString() + " gastando 3 acciones");
                    this.setNumAcciones(this.getNumAcciones() + 3);
                } else {
                    juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " no se puede mover, utiliza la accion en otra accion diferente a moverse");
                }

            } else {
                juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " no se puede mover porque esta rodeado de zombies, utiliza la accion en otra accion diferente a moverse");
            }

        } else {
            juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " no se puede mover hasta esa posicion porque esta muy lejos, prueba con una coordenada valida");
        }

    }

    @Override
    public void activarse(Tablero tablero, Juego juego) {
        if ((estado.equals("ACTIVO")) && !(this.getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1)) && haDevoradoHuidizo())) { //CONDICION DE VIVO Y QUE NO ESTE MUERTO
            juego.getPantallaJuego().agregarEvento("********** TURNO DE " + getNombre() + " **********");
            while (this.getNumAcciones() < this.maxAcciones) {
                //juego.getPantallaJuego().agregarEvento("ZOMBIE : " + this.getNombre() + " ACCIONES REALIZADAS: " + this.getNumAcciones() + "/3 NIVEL DE HAMBRE: " + this.getHambre());
                //juego.getPantallaJuego().agregarEvento("Ingrese la accion que desea hacer (Atacar(1)/Moverse(2)/Buscar Comida(3)/No Hacer Nada(4)");
                PanelTurnoJugador panelTurno = new PanelTurnoJugador(this.getNombre(), this.getNumAcciones(), this.getAtaqueEspecial().getAlcance());
                juego.getPantallaJuego().setPanelControles(panelTurno);
                while (panelTurno.getAccion() == null) {
                    try {
                        // Espera 100 milisegundos antes de volver a comprobar
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                switch (panelTurno.getAccion()) {
                    case "Devorar":
                        juego.getPantallaJuego().agregarEvento(getNombre() + " elige Devorar:");
                        Casilla casillaTablero = tablero.getCasilla(this.getCasilla().getCoordenada());
                        devorar.realizarAtaque(this, casillaTablero, juego);
                        numAcciones++;
                        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));

                        break;
                    case "Ataque Especial":
                        juego.getPantallaJuego().agregarEvento(getNombre() + " elige Ataque Especial:");
                        PanelAtaqueEspecial panelAtaque = new PanelAtaqueEspecial();
                        juego.getPantallaJuego().setPanelControles(panelAtaque);
                        while (panelAtaque.getCoordX() == -1 && panelAtaque.getCoordY() == -1) {
                            try {
                                // Espera 100 milisegundos antes de volver a comprobar
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int x = panelAtaque.getCoordX();
                        int y = panelAtaque.getCoordY();
                        Coordenada coordAtacar = new Coordenada(x, y);
                        Casilla objetivoAtacar = tablero.getCasilla(coordAtacar);
                        int dx = Math.abs(this.getCasilla().getCoordenada().getX() - objetivoAtacar.getCoordenada().getX());
                        int dy = Math.abs(this.getCasilla().getCoordenada().getY() - objetivoAtacar.getCoordenada().getY());

                        if ((dx + dy) <= ataqueEspecial.getAlcance()) {
                            ataqueEspecial.realizarAtaque(this, objetivoAtacar, juego);
                        } else {
                            juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " ha malgastado una accion ya que no se puede alcanzar con el ataque la posicion " + coordAtacar.toString());
                        }
                        numAcciones++;
                        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));
                        break;
                    case "Moverse":

                        PanelMoverse panelMoverse = new PanelMoverse();
                        juego.getPantallaJuego().setPanelControles(panelMoverse);
                        while (panelMoverse.getDireccion() == null) {
                            try {
                                // Espera 100 milisegundos antes de volver a comprobar
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        switch (panelMoverse.getDireccion()) {
                            case "Arriba":
                                if (!(this.getCasilla().getCoordenada().getX() == 0)) {
                                    juego.getPantallaJuego().agregarEvento(getNombre() + " elige Moverse hacia Arriba:");
                                    Coordenada coordMoverse = new Coordenada(this.getCasilla().getCoordenada().getX() - 1, this.getCasilla().getCoordenada().getY());
                                    Casilla objetivoMoverse = tablero.getCasilla(coordMoverse);
                                    moverse(tablero, objetivoMoverse, juego);
                                }
                                break;
                            case "Abajo":
                                if (!(this.getCasilla().getCoordenada().getX() == tablero.getFilas() - 1)) {
                                    juego.getPantallaJuego().agregarEvento(getNombre() + " elige Moverse hacia Abajo:");
                                    Coordenada coordMoverse2 = new Coordenada(this.getCasilla().getCoordenada().getX() + 1, this.getCasilla().getCoordenada().getY());
                                    Casilla objetivoMoverse2 = tablero.getCasilla(coordMoverse2);
                                    moverse(tablero, objetivoMoverse2, juego);
                                }
                                break;
                            case "Izquierda":
                                if (!(this.getCasilla().getCoordenada().getY() == 0)) {
                                    juego.getPantallaJuego().agregarEvento(getNombre() + " elige Moverse hacia la Izquierda:");
                                    Coordenada coordMoverse3 = new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() - 1);
                                    Casilla objetivoMoverse3 = tablero.getCasilla(coordMoverse3);
                                    moverse(tablero, objetivoMoverse3, juego);
                                }
                                break;
                            case "Derecha":
                                if (!(this.getCasilla().getCoordenada().getY() == tablero.getColumnas() - 1)) {
                                    juego.getPantallaJuego().agregarEvento(getNombre() + " elige Moverse hacia la Derecha:");
                                    Coordenada coordMoverse4 = new Coordenada(this.getCasilla().getCoordenada().getX(), this.getCasilla().getCoordenada().getY() + 1);
                                    Casilla objetivoMoverse4 = tablero.getCasilla(coordMoverse4);
                                    moverse(tablero, objetivoMoverse4, juego);
                                }
                                break;

                        }
                        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));//PROVISIONAL
                        break;
                    case "Buscar Comida":
                        juego.getPantallaJuego().agregarEvento(getNombre() + " elige Buscar Comida:");
                        buscarComida(tablero, juego);
                        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));//PROVISIONAL
                        break;
                    case "No Hacer Nada":
                        juego.getPantallaJuego().agregarEvento(getNombre() + " elige No Hacer Nada:");
                        noHacerNada(juego);
                        SwingUtilities.invokeLater(() -> juego.getPantallaJuego().actualizarTablero(juego));//PROVISIONAL
                        break;
                }
                if (this.getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1)) && haDevoradoHuidizo()) {
                    this.setNumAcciones(3);
                }
            }
            if (this.getHambre() < 5) {
                this.setHambre(this.getHambre() + 1);
            } else {
                this.setNumHeridas(this.getNumHeridas() + 1);
                this.agregarHerida("Hambre");
                juego.getPantallaJuego().agregarEvento("El zombi " + this.getNombre() + " ha sufrido una herida por tener demasiado hambre.");
            }
            if (this.getNumHeridas() >= 5) {
                this.setEstado("ELIMINADO");
                Casilla casillaTablero = tablero.getCasilla(this.getCasilla().getCoordenada());
                casillaTablero.getNumZombie().remove(this);

                juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " se ha muerto de hambre.");
            }
            if (!(this.getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1)) && haDevoradoHuidizo())) {
                this.setNumAcciones(0);

            }

        }
        if (this.getCasilla().getCoordenada().equals(new Coordenada(tablero.getFilas() - 1, tablero.getColumnas() - 1))) {
            juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " ha llegado a la casilla objetivo.");
        }
    }

    @Override
    public void atacar(Tablero tablero, Juego juego) {//ESTO EN PRINCIPIO SE VA A REALIZAR DIRECTAMENTE EN ACTIVARSE
        juego.getPantallaJuego().agregarEvento("Selecciona el ataque que deseas realizar:" + "\n" + "Devorar(1)[Alcance 0]//Ataque especial(2)[Alcance " + this.ataqueEspecial.getAlcance() + "]");
        Scanner op = new Scanner(System.in);
        int opcion = op.nextInt();
        if (opcion == 1) {
            Casilla casillaTablero = tablero.getCasilla(this.getCasilla().getCoordenada());
            devorar.realizarAtaque(this, casillaTablero, juego);
        } else if (opcion == 2) {
            juego.getPantallaJuego().agregarEvento("Ingrese la coordenada que desea atacar X:");
            int x = op.nextInt();
            juego.getPantallaJuego().agregarEvento(" Y:");
            int y = op.nextInt();
            Coordenada coordAtacar = new Coordenada(x, y);
            Casilla objetivoAtacar = tablero.getCasilla(coordAtacar);
            int dx = Math.abs(this.getCasilla().getCoordenada().getX() - objetivoAtacar.getCoordenada().getX());
            int dy = Math.abs(this.getCasilla().getCoordenada().getY() - objetivoAtacar.getCoordenada().getY());

            if ((dx + dy) <= ataqueEspecial.getAlcance()) {
                ataqueEspecial.realizarAtaque(this, objetivoAtacar, juego);
            } else {
                juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " ha malgastado una accion ya que no se puede alcanzar con el ataque esta posicion " + this.getCasilla().getCoordenada().toString());
            }
        }
        numAcciones++;

        //BUSCAMOS ESA CASILLA EN EL TABLERO
        /*Casilla casillaTablero = tablero.getCasilla(posicion.getCoordenada());
        Scanner ent = new Scanner(System.in);
        System.out.println("Alcance de ataqueespecial: " + this.ataqueEspecial.getAlcance());
        System.out.println("Que ataque desea ejercer (Devorar(1)/AtaqueEspecial(2)): ");
        int opcion = ent.nextInt();
        if (opcion == 1) {
            devorar.realizarAtaque(this, casillaTablero);
        } else if (opcion == 2) {
            int dx = Math.abs(this.getCasilla().getCoordenada().getX() - posicion.getCoordenada().getX());
            int dy = Math.abs(this.getCasilla().getCoordenada().getY() - posicion.getCoordenada().getY());

            if ((dx + dy) <= ataqueEspecial.getAlcance()) {
                ataqueEspecial.realizarAtaque(this, casillaTablero);
            } else {
                System.out.println("El zombie " + this.getNombre() + " ha malgastado una accion ya que no se puede alcanzar con el ataque esta posicion " + this.getCasilla().getCoordenada().toString());
            }
        }
        numAcciones++;
         */
    }

    /*
    @Override
    public Coordenada getCoordenada() {
        return casilla.getCoordenada();
    }
     */
    public void buscarComida(Tablero tablero, Juego jue) {
        Random random = new Random();
        int resultado = random.nextInt(100); // Genera un número entre 0 y 99

        if (resultado < 30) {
            // 30% de probabilidad de aparecer un humano huidizo

            //GENERAMOS UNA COORDENADA ALEATORIA DONDE NO HAYA MAS DE 3 HUMANOS EN ESA CASILLA
            Random random1 = new Random();
            int x, y;
            Coordenada coord = null;
            do {
                x = random1.nextInt(tablero.getColumnas()); // Genera un número entre 0 y el ancho del tablero(columnas)
                y = random1.nextInt(tablero.getFilas()); // Genera un número entre 0 y (alto-1)
                coord = new Coordenada(x, y);
            } while (tablero.getCasilla(coord).getNumHumano().size() > 3);
            Casilla casillaHumano = tablero.getCasilla(coord);
            //CONSTRUCTOR DE HUMANO HUIDIZO
            Huidizo humano1 = new Huidizo(casillaHumano);
            //AGREGAMOS EL HUMANO HUIDIZO A ESA CASILLA
            ArrayList<Humano> humanosEnCasilla = tablero.getCasilla(humano1.getCasilla().getCoordenada()).getNumHumano();
            humanosEnCasilla.add(humano1);
            jue.getListaHumanos().add(humano1);
            tablero.getCasilla(humano1.getCasilla().getCoordenada()).setNumHumano(humanosEnCasilla);

            jue.getPantallaJuego().agregarEvento("Ha aparecido un Humano Huidizo en la coordenada " + humano1.getCasilla().getCoordenada().toString());
        } else if (resultado < 80) {
            // 50% de probabilidad de aparecer un conejo (30+50=80)
            Random random1 = new Random();
            int x = random1.nextInt(tablero.getColumnas());
            int y = random1.nextInt(tablero.getFilas());
            Coordenada coord = new Coordenada(x, y);
            Casilla casillaConejo = tablero.getCasilla(coord);
            Conejo nuevoConejo = new Conejo(  casillaConejo);
            ArrayList<Conejo> conejosEnCasilla = tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).getNumConejos();
            conejosEnCasilla.add(nuevoConejo);
            tablero.getCasilla(nuevoConejo.getCasilla().getCoordenada()).setNumConejos(conejosEnCasilla);
            jue.getListaConejos().add(nuevoConejo);
            jue.getPantallaJuego().agregarEvento("Ha aparecido un Conejo en la coordenada " + nuevoConejo.getCasilla().getCoordenada().toString());
        } else {
            // 20% de probabilidad de no aparecer nada
            jue.getPantallaJuego().agregarEvento("No ha aparecido ningún comestible.");
        }
        numAcciones++;
    }

    public void noHacerNada(Juego juego) {
        juego.getPantallaJuego().agregarEvento("El zombie " + this.getNombre() + " no ha hecho nada");
        numAcciones++;
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Número de Acciones: ").append(numAcciones).append("\n");
        sb.append("Numero de Comestibles Devorados: ").append(comestiblesDevorados.size()).append("\n");
        sb.append("Comestibles Devorados:\n");
        for (Comestible comestible : comestiblesDevorados) {
            if (comestible instanceof Humano humano) {
                sb.append("Humano:\n");
                sb.append(humano.toText()).append("\n");
            } else if (comestible instanceof Conejo conejo) {
                sb.append("Conejo:\n");
                sb.append(conejo.toText()).append("\n");
            }
        }
        sb.append("Numero de Comestibles Eliminados: ").append(comestiblesEliminados.size()).append("\n");
        sb.append("Comestibles Eliminados:\n");
        for (Comestible comestible : comestiblesEliminados) {
            if (comestible instanceof Humano humano) {
                sb.append("Humano:\n");
                sb.append(humano.toText()).append("\n");
            } else if (comestible instanceof Conejo conejo) {
                sb.append("Conejo:\n");
                sb.append(conejo.toText()).append("\n");
            }
        }
        sb.append("Número de Heridas: ").append(numHeridas).append("\n");
        sb.append("Heridas Recibidas:\n");
        for (String herida : heridasRecibidas) {
            sb.append(herida).append("\n");
        }
        sb.append("Hambre: ").append(hambre).append("\n");
        sb.append("Ataque Especial:\n").append(ataqueEspecial.toText());
        sb.append("Casilla: ").append(casilla.getCoordenada().toText());
        return sb.toString();
    }
}
//PARA EL ATAQUE HACER EQUIPO=ATAQUE, VIVERES=ATAQUE ESPECIAL Y DEVORAR ES LO MISMO QUE UN ATAQUE ESPECIAL EN CONCRETO.
