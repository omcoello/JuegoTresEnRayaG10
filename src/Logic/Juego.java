package Logic;

import Jugadores.ComputadoraIA;
import Jugadores.Persona;
import Tree.Tree;
import java.util.Scanner;

/**
 *
 * @author omarc
 */
public class Juego {

    private Tree<Tablero> generalTree;

    
    Persona humanPlayer = new Persona();
    ComputadoraIA computadoraIA = new ComputadoraIA();
    Tablero tablero = new Tablero();
    

    public Juego(Persona humanPlayer, ComputadoraIA computadoraIA, Tablero tablero) {
        this.humanPlayer = humanPlayer;
        this.computadoraIA = computadoraIA;
        this.tablero = tablero;
        generalTree = generateTree(new Tablero(), /*seleccionar turno*/turn, new Tree());
    }

    public void startGame(){
        asignarSimbolos();
        escogerTurno();
        tablero.verTablero();
        ingresarSimbolos();
    }

    private void asignarSimbolos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escoge tu simbolo (X || O): ");
        String simbolo = sc.nextLine();
        if(simbolo.toUpperCase().equals("X")){
            humanPlayer.setSimbolo("X");
            computadoraIA.setSimbolo("O");
        } else {
            humanPlayer.setSimbolo("O");
            computadoraIA.setSimbolo("X");
        }
    }

    private void ingresarSimbolos(){
        while(!tablero.isFullTab()){

            if(humanPlayer.isTurnoHumano()){
                ingresoHumano();
                humanPlayer.setTurnoHumano(false);
                computadoraIA.setPcTurno(true);
            } else if(computadoraIA.isPcTurno()){
                ingresoPC();
                computadoraIA.setPcTurno(false);
                humanPlayer.setTurnoHumano(true);
            }

            if(humanPlayer.PersonWins(this.tablero)){
                System.out.println("GANADOR: HUMANO");
                break;
            }
            if(computadoraIA.computerWins(this.tablero)){
                System.out.println("GANADOR: CPU");
                break;
            }
        }
    }

    private void ingresoHumano(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno humano: "+humanPlayer.getSimbolo());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = humanPlayer.getSimbolo();
        tablero.verTablero();
    }

    //SOLO DE PRUEBA, DEBERIA SER EL ALGORITMO QUE ELIGA EL INGRESO
    private void ingresoPC(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Turno cpu: "+computadoraIA.getSimbolo());
        System.out.println("Posiciones: ");
        int pos_i = sc.nextInt();
        int pos_j = sc.nextInt();
        tablero.getTablero()[pos_i][pos_j] = computadoraIA.getSimbolo();
        tablero.verTablero();
    }

    private void escogerTurno(){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quien empieza? 1 = humano, 2 = computadora: ");
        String lector = sc.nextLine();
        if(lector.equals("1")){
            humanPlayer.setTurnoHumano(true);
        } else{
            humanPlayer.setTurnoHumano(false);
            computadoraIA.setPcTurno(true);
        }
    }

    public Tree<Tablero> generateTree(Tablero tab, String turn, Tree<Tablero> tree) {

        if (tab.isEmptyTab() || !tab.isFullTab()) {
            String casillas[][] = tab.getTablero();
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (casillas[i][j].isEmpty()) {
                        casillas[i][j] = turn;
                        tree.getRoot().getChildren().add(new Tree(tab));
                        turn = alternateTurn(turn);
                        tree.getRoot().getChildren().add(generateTree(tab, turn, tree.getRoot().getChildren().get(tree.getRoot().getChildren().size() - 1)));
                    }
                }
            }
        }

        return tree;
    }
    
    public String alternateTurn(String turn){
        if(turn.equals("X")){
            return "O";
        }else{
            return "X";
        }        
    }

}
