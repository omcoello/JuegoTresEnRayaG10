package Jugadores;

import Logic.Tablero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class ComputadoraIA extends Player {

    public ComputadoraIA() {
    }

    public ComputadoraIA(String name, String symbol, boolean turn) {
        super(name, symbol, turn);
    }

    public boolean computerWins(Tablero tablero) {
        LinkedHashSet<String[]> listaFilas = tablero.getListaFilas();
        LinkedHashSet<String[]> listaColumnas = tablero.getListaColumnas();
        ArrayList<String[]> listaDiagonales = tablero.getDiagonales();
        boolean win = false;
        String[] aux = {getSymbol(), getSymbol(), getSymbol()};
        //Gana por filas
        for (String[] arrayFilas : listaFilas) {
            if (arreglosIguales(arrayFilas, aux)) {
                win = true;
                break;
            }
        }
        //Gana por columnas
        for (String[] arrayColumnas : listaColumnas) {
            if (arreglosIguales(arrayColumnas, aux)) {
                win = true;
                break;
            }
        }
        //Gana por diagonales
        for (String[] arrayDiagonales : listaDiagonales) {
            if (arreglosIguales(arrayDiagonales, aux)) {
                win = true;
                break;
            }
        }

        return win;
    }

    private boolean arreglosIguales(String[] array1, String[] array2) {
        return Arrays.equals(array1, array2);
    }

    public int numEspaciosVacios(Tablero tablero) {
        int espacios = 0;
        for (int i = 0; i < tablero.getTablero().length; i++) {
            for (int j = 0; j < tablero.getTablero()[i].length; j++) {
                if (tablero.getTablero()[i][j].equals("-1")) {
                    espacios++;
                }
            }
        }
        return espacios;
    }

}
