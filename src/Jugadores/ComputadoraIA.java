package Jugadores;

import LogicaTablero.Tablero;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class ComputadoraIA {
    String simbolo;
    boolean pcTurno;

    private void generateTree(Tablero tablero, Persona human){}

    public boolean computerWins(Tablero tablero){
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

    public String getSimbolo() {
        return simbolo;
    }

    public boolean isPcTurno() {
        return pcTurno;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setPcTurno(boolean pcTurno) {
        this.pcTurno = pcTurno;
    }

    private boolean arreglosIguales(String[] array1, String[] array2){
        return Arrays.equals(array1, array2);
    }

    public int numEspaciosVacios(Tablero tablero){
        int espacios = 0;
        for(int i = 0; i<tablero.getTablero().length; i++){
            for(int j = 0; j<tablero.getTablero()[i].length; j++){
                if(tablero.getTablero()[i][j].equals("-1")){
                    espacios++;
                }
            }
        }
        return espacios;
    }

}
