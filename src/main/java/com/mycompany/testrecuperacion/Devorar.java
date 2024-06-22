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
public class Devorar extends Ataque {

    public Devorar() {
        super("Devorar", 1, 4, 0);
    }

    @Override
    public void realizarAtaque(Zombie zombie, Casilla objetivo, Juego juego) {

        ArrayList<Conejo> conejosEnCasilla = objetivo.getNumConejos();
        ArrayList<Humano> humanosEnCasilla = objetivo.getNumHumano();

        // Obtener todos los objetivos (conejos y humanos) en la casilla objetivo del tablero
        ArrayList<Comestible> comestiblesEnCasilla = new ArrayList<>();
        // Agregar los comestibles por orden de prioridad
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

        // Realizar el ataque
        if (!comestiblesEnCasilla.isEmpty()) {
            int dados = this.getPotencia() + zombie.getHambre(); //SI EN LA CASILLA HAY COMESTIBLES ENTONCES EMPEZAMOS
            int impactos = 0;
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
                if (impactos > 0) {
                    if (comestible instanceof Humano humano) {
                        if (humano.getAguante() <= impactos) {
                            humano.calmarHambreZombie(zombie); // Devorar al humano
                            humanosEnCasilla.remove(humano);
                            objetivo.setNumHumano(humanosEnCasilla); // Eliminar el humano de la casilla
                            ArrayList<Comestible> elementosDevorados = zombie.getComestiblesDevorados();
                            elementosDevorados.add(humano);
                            zombie.setComestiblesDevorados(elementosDevorados);
                            ArrayList<Humano> humanosJuego = juego.getListaHumanos();
                            humanosJuego.remove(humano);
                            juego.setListaHumanos(humanosJuego);
                            juego.getPantallaJuego().agregarEvento(this.getNombre() + " ha matado a un " + humano.getClass().getSimpleName() + " que tenia aguante " + humano.getAguante());
                            break; // Salir del bucle FOR COMESTIBLE después de devorar un objetivo
                        }else{
                            juego.getPantallaJuego().agregarEvento("No puedes matar al humano "+humano.getClass().getSimpleName()+" de aguante "+humano.getAguante()+ " porque no has obtenido suficientes impactos");
                        }
                    } else if (comestible instanceof Conejo conejo) {
                        conejo.calmarHambreZombie(zombie); // Devorar al conejo y calmar el hambre del zombie
                        //AQUI DEBERIAMOS METER EL SYSTEM.OUT.PRINTLN("TAL HUMANO HA MUERTO O HA SIDO DEVORADO POR TAL ZOMBIE")
                        conejosEnCasilla.remove(conejo);
                        objetivo.setNumConejos(conejosEnCasilla); // Eliminar el conejo de la casilla
                        ArrayList<Comestible> elementosDevorados = zombie.getComestiblesDevorados();
                        elementosDevorados.add(conejo);
                        zombie.setComestiblesDevorados(elementosDevorados);
                        juego.getPantallaJuego().agregarEvento(this.getNombre() + " ha matado al conejo " + conejo.getNombre());
                        ArrayList<Conejo> conejosJuego = juego.getListaConejos();
                        conejosJuego.remove(conejo);
                        juego.setListaConejos(conejosJuego);
                        break; // Salir del bucle después de devorar un objetivo
                    }
                } else {
                    juego.getPantallaJuego().agregarEvento("NO has tenido exito en la accion devorar");
                    break; // Salir del bucle FOR COMESTIBLE si no quedan impactos disponibles
                }
            }

        } else {
            juego.getPantallaJuego().agregarEvento("Has malgastado la accion porque en esta casilla no habia ningun humano o conejo");
        }

    }

}
