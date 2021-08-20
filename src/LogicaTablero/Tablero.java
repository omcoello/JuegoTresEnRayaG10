package LogicaTablero;

import java.util.*;

public class Tablero {

    private final String [][] tablero;
    private int utilidad;

    public Tablero () {
        tablero = new String[3][3];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "-1";
            }
        }
    }

    //getter de la matriz
    public String[][] getTablero() {
        return tablero;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    //imprime la matriz
    public void verTablero () {
        for (String[] String : tablero) {
            System.out.print("|");
            for (int j = 0; j < String.length; j++) {
                System.out.print(String[j]);
                if (j != String.length - 1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    //contadores para calcular la funcion de utilidad
    public int contarFilasDisponibles (String jugador) {
        int count = 0;
        for(String[] chars: tablero){
            if(jugador.equalsIgnoreCase("X")){
                if(!Arrays.asList(chars).contains("O")){
                    count++;
                }
            }
            if(jugador.equalsIgnoreCase("O")){
                if(!Arrays.asList(chars).contains("X")){
                    count++;
                }
            }
        }
        return count;
    }

    public int contarColumnasDisponibles (String jugador) {
        LinkedHashSet<String[]> llc = getListaColumnas();
        int count = 0;
        for(String[] columas: llc){
            if(jugador.equalsIgnoreCase("X")){
                if(!Arrays.asList(columas).contains("O")){
                    count++;
                }
            }
            if(jugador.equalsIgnoreCase("O")){
                if(!Arrays.asList(columas).contains("X")){
                    count++;
                }
            }
        }
        return count;
    }

    public int contarDiagonalesDisponibles (String jugador) {
        ArrayList<String[]> aux = getDiagonales();
        int count = 0;
        for(String[] strs: aux){
            if(jugador.equalsIgnoreCase("X")){
                if(!Arrays.asList(strs).contains("O")){
                    count++;
                }
            }
            if(jugador.equalsIgnoreCase("O")){
                if(!Arrays.asList(strs).contains("X")){
                    count++;
                }
            }
        }
        return count;
    }

    //si la matriz esta vacia
    public boolean isEmptyTab(){
        boolean empty = true;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(!tablero[i][j].equals("-1")){
                    empty = false;
                }
            }
        }
        return empty;
    }

    //si la matriz esta llena
    public boolean isFullTab(){
        boolean full = true;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j].equals("-1")){
                    full = false;
                }
            }
        }
        return full;
    }

    //lista con dos arreglos, la diagonal principal y la secundaria
    public ArrayList<String[]> getDiagonales(){
        ArrayList<String[]> listaDiagonales = new ArrayList<>();
        String[] diagonalPrincipal = new String[tablero.length];
        String[] diagonalSecundaria = new String[tablero.length];
        for(int i = 0; i<tablero.length; i++){
            for(int j = 0; j<tablero[i].length; j++){
                if(i==j){
                    diagonalPrincipal[i] = tablero[i][j];
                }
                if(i+j == tablero.length-1){
                    diagonalSecundaria[i] = tablero[i][j];
                }
            }
        }
        listaDiagonales.add(diagonalPrincipal);
        listaDiagonales.add(diagonalSecundaria);
        return listaDiagonales;
    }

    //conjunto con 3 arreglos, las 3 columnas
    public LinkedHashSet<String[]> getListaColumnas(){
        LinkedHashSet<String[]> resultante = new LinkedHashSet<>();
        for(int i = 0; i<tablero.length; i++){
            for(int j = 0; j<tablero[i].length; j++){
                resultante.add(tablero[j]);
            }
        }
        return resultante;
    }

    //conjunto con 3 arreglos, las 3 filas
    public LinkedHashSet<String[]> getListaFilas(){
        LinkedHashSet<String[]> resultante = new LinkedHashSet<>();
        Collections.addAll(resultante, tablero);
        return resultante;
    }

}
