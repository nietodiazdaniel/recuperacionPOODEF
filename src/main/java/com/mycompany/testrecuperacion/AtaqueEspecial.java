/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testrecuperacion;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nieto
 */
public class AtaqueEspecial extends Ataque {

    private static final String[] NOMBRES_ATAQUES = {
        "Tsunami de Sangre",
        "Lluvia de Huesos",
        "Golpe de Terror",
        "Vendaval de Garras",
        "Explosión de Putrefacción",
        "Grito Mortal",
        "Sombra de la Muerte"
    };

    public AtaqueEspecial() {
        this(NOMBRES_ATAQUES[new Random().nextInt(NOMBRES_ATAQUES.length)]);
    }

    public AtaqueEspecial(String nombre) {
        super(nombre, asignarPotencia(nombre), asignarValorExito(nombre), asignarAlcance(nombre));
    }

    public AtaqueEspecial(String nombre, int potencia, int valorExito, int alcance) {
        super(nombre, potencia, valorExito, alcance);
    }
    

    private static int asignarPotencia(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 3;
            case "Lluvia de Huesos":
                return 4;
            case "Golpe de Terror":
                return 5;
            case "Vendaval de Garras":
                return 2;
            case "Explosión de Putrefacción":
                return 4;
            case "Grito Mortal":
                return 3;
            case "Sombra de la Muerte":
                return 3;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    private static int asignarValorExito(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 3;
            case "Lluvia de Huesos":
                return 4;
            case "Golpe de Terror":
                return 3;
            case "Vendaval de Garras":
                return 2;
            case "Explosión de Putrefacción":
                return 3;
            case "Grito Mortal":
                return 5;
            case "Sombra de la Muerte":
                return 3;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    private static int asignarAlcance(String nombre) {
        switch (nombre) {
            case "Tsunami de Sangre":
                return 2;
            case "Lluvia de Huesos":
                return 3;
            case "Golpe de Terror":
                return 1;
            case "Vendaval de Garras":
                return 4;
            case "Explosión de Putrefacción":
                return 2;
            case "Grito Mortal":
                return 3;
            case "Sombra de la Muerte":
                return 1;
            default:
                throw new IllegalArgumentException("Nombre de ataque desconocido: " + nombre);
        }
    }

    @Override
    public void realizarAtaque(Zombie zombie, Casilla objetivo, Juego juego) {
        ArrayList<Conejo> conejosEnCasilla = objetivo.getNumConejos();
        ArrayList<Humano> humanosEnCasilla = objetivo.getNumHumano();

        // Obtener todos los objetivos (conejos y humanos) en la casilla
        ArrayList<Comestible> comestiblesEnCasilla = new ArrayList<>();
        // Agregar los ingenieros informáticos
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Informatico) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Soldado) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Blindado) {
                comestiblesEnCasilla.add(humano);
            }
        }
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Especialista) {
                comestiblesEnCasilla.add(humano);
            }
        }
        comestiblesEnCasilla.addAll(conejosEnCasilla);
        for (Humano humano : humanosEnCasilla) {
            if (humano instanceof Huidizo) {
                comestiblesEnCasilla.add(humano);
            }
        }
        if (!comestiblesEnCasilla.isEmpty()) {
            int dados = this.getPotencia() + zombie.getHambre(); //SI EN LA CASILLA HAY COMESTIBLES ENTONCES EMPEZAMOS
            int impactos = 0;
            //System.out.print("Valor Exito: " + this.getValorExito() + ". Has obtenido los siguentes numeros en el dado: ");
            juego.getPantallaJuego().agregarEvento("Valor Exito: " + this.getValorExito() + ". Has obtenido los siguentes numeros en el dado: ");
            for (int i = 0; i < dados; i++) {
                int resultado = Dado.tirarDado();
                juego.getPantallaJuego().agregarEvento(resultado + " ");
                if (resultado >= this.getValorExito()) {
                    impactos++;
                }
            }
            juego.getPantallaJuego().agregarEvento("Por lo que tienes " + impactos + " impactos.");
            for (Comestible comestible : comestiblesEnCasilla) {
                if (impactos <= 0) {
                    break; // No quedan impactos
                }
                if (comestible instanceof Humano humano) {
                    if (humano.getAguante() <= impactos) {

                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        humanosEnCasilla.remove(humano);
                        objetivo.setNumHumano(humanosEnCasilla); // Eliminar el humano de la casilla
                        ArrayList<Comestible> elementosEliminados = zombie.getComestiblesEliminados();
                        elementosEliminados.add(humano);
                        zombie.setComestiblesEliminados(elementosEliminados);
                        impactos -= humano.getAguante(); // Decrementar la cantidad de impactos restantes
                        ArrayList<Humano> humanosJuego = juego.getListaHumanos();
                        humanosJuego.remove(humano);
                        juego.setListaHumanos(humanosJuego);
                        juego.getPantallaJuego().agregarEvento(this.getNombre() + " ha matado a un " + humano.getClass().getSimpleName()+" que tenia aguante "+humano.getAguante());
                    }else{
                        juego.getPantallaJuego().agregarEvento("No puedes matar al humano "+humano.getClass().getSimpleName()+" de aguante "+humano.getAguante()+ " porque no has obtenido suficientes impactos");
                    }
                } else if (comestible instanceof Conejo conejo) {
                    if (impactos > 0) {

                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        conejosEnCasilla.remove(conejo);
                        objetivo.setNumConejos(conejosEnCasilla); // Eliminar el conejo de la casilla
                        ArrayList<Comestible> elementosEliminados = zombie.getComestiblesEliminados();
                        elementosEliminados.add(conejo);
                        zombie.setComestiblesEliminados(elementosEliminados);
                        impactos--; // Decrementar la cantidad de impactos restantes
                        ArrayList<Conejo> conejosJuego = juego.getListaConejos();
                        conejosJuego.remove(conejo);
                        juego.setListaConejos(conejosJuego);
                        juego.getPantallaJuego().agregarEvento(this.getNombre() + " ha matado al conejo " + conejo.getNombre());
                    }
                }
            }

        } else {
            juego.getPantallaJuego().agregarEvento("Has malgastado la accion porque en esta casilla no habia ningun humano o conejo");
        }
    }

}
