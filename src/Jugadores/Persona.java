package Jugadores;

import Logic.Tablero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Persona {
    String nombre;
    String simbolo;
    boolean turnoHumano;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean isTurnoHumano() {
        return turnoHumano;
    }

    public void setTurnoHumano(boolean turnoHumano) {
        this.turnoHumano = turnoHumano;
    }

    public boolean PersonWins(Tablero tablero){
        LinkedHashSet<String[]> listaFilas = tablero.getListaFilas();
        LinkedHashSet<String[]> listaColumnas = tablero.getListaColumnas();
        ArrayList<String[]> listaDiagonales = tablero.getDiagonales();
        boolean win = false;
        String[] aux = {this.simbolo, this.simbolo, this.simbolo};
        //Gana por filas
        for(String[] arrayFilas: listaFilas){
            if(arreglosIguales(arrayFilas, aux)){
                win = true;
                break;
            }
        }
        //Gana por columnas
        for(String[] arrayColumnas: listaColumnas){
            if(arreglosIguales(arrayColumnas, aux)){
                win = true;
                break;
            }
        }
        //Gana por diagonales
        for(String[] arrayDiagonales: listaDiagonales){
            if(arreglosIguales(arrayDiagonales, aux)){
                win = true;
                break;
            }
        }
        return win;
    }

    //funciona solo para arreglos de igual dimension
    private boolean arreglosIguales(String[] array1, String[] array2){
        return Arrays.equals(array1,array2);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return simbolo.equals(persona.simbolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simbolo);
    }

}
